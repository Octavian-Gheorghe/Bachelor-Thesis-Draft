package org.example.Entity.Activity;

import lombok.Getter;
import lombok.Setter;
import org.example.Entity.Location;
import org.example.Entity.TemporalInterval;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;

@Getter
@Setter
public class Activity {
    private Integer id;
    private String name;

    //the duration range
    private Integer duration;

    //interruptibility
    private Integer numberOfMaxPartsInWhichCanBeSplit; //p
    private List<ActivityPart> partsOfTheActivity;

    private Integer minimumAllowedPartDuration;
    private Integer maximumAllowedPartDuration;
    private Integer minimumTemporalDistanceBetweenParts;
    private Integer maximumTemporalDistanceBetweenParts;

    //temporal domains
    private List<TemporalInterval> listOfTemporalIntervals;

    //set of alternative locations
    private List<Location> possibleLocations;

    //utilization
    private double utilizationValue;

    public Activity(Integer id, String name, Integer duration, Integer numberOfMaxPartsInWhichCanBeSplit, Integer minimumAllowedPartDuration, Integer maximumAllowedPartDuration, Integer minimumTemporalDistanceBetweenParts, Integer maximumTemporalDistanceBetweenParts, List<TemporalInterval> listOfTemporalIntervals, List<Location> possibleLocations) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.numberOfMaxPartsInWhichCanBeSplit = numberOfMaxPartsInWhichCanBeSplit;
        partsOfTheActivity = new ArrayList<>();
        interpretNrOfInterruptibleParts(numberOfMaxPartsInWhichCanBeSplit, minimumAllowedPartDuration, maximumAllowedPartDuration, minimumTemporalDistanceBetweenParts, maximumTemporalDistanceBetweenParts);
        this.listOfTemporalIntervals = listOfTemporalIntervals;
        this.possibleLocations = possibleLocations;
        this.utilizationValue = 0;
    }

    private void interpretNrOfInterruptibleParts(Integer numberOfMaxPartsInWhichCanBeSplit, Integer minimumAllowedPartDuration, Integer maximumAllowedPartDuration, Integer minimumTemporalDistanceBetweenParts, Integer maximumTemporalDistanceBetweenParts) {
        if (this.numberOfMaxPartsInWhichCanBeSplit < 1)
            this.numberOfMaxPartsInWhichCanBeSplit = 1;
        if (this.numberOfMaxPartsInWhichCanBeSplit == 1) {
            ActivityPart activityPart = new ActivityPart();
            partsOfTheActivity.add(activityPart);
            this.minimumAllowedPartDuration = this.duration;
            this.maximumAllowedPartDuration = this.duration;
            this.minimumTemporalDistanceBetweenParts = 0;
            this.maximumTemporalDistanceBetweenParts = 0;
        } else {
            this.minimumAllowedPartDuration = minimumAllowedPartDuration;
            this.maximumAllowedPartDuration = maximumAllowedPartDuration;
            this.minimumTemporalDistanceBetweenParts = minimumTemporalDistanceBetweenParts;
            this.maximumTemporalDistanceBetweenParts = maximumTemporalDistanceBetweenParts;
            for (int i = 0; i < numberOfMaxPartsInWhichCanBeSplit; i++) {
                ActivityPart activityPart = new ActivityPart();
                partsOfTheActivity.add(activityPart);
            }
        }
    }

    public double determineDifficultyOfActivityPlacement()
    {
        return max(findMetric1ForActivity(), findMetric2ForActivity());
    }

    public void removeUselessIntervals()
    {
        for(TemporalInterval temporalInterval : listOfTemporalIntervals)
        {
            Integer temporalIntervalWeight = temporalInterval.getWeight();
            if(temporalIntervalWeight < minimumAllowedPartDuration)
            {
                listOfTemporalIntervals.remove(temporalInterval);
            }
        }
    }

    private double determineNetSizeForActivity()
    {
        double fullWeight;
        fullWeight = 0;
        for(TemporalInterval temporalInterval : this.getListOfTemporalIntervals())
        {
            fullWeight += temporalInterval.getWeight();
        }
        return fullWeight;
    }

    public double findMetric1ForActivity()
    {
        return this.getDuration().doubleValue() / determineNetSizeForActivity();
    }

    private int determineMakespanOfActivity() //?
    {
        if(this.getPartsOfTheActivity() != null)
            return Math.toIntExact(Math.abs(Duration.between(
                this.getPartsOfTheActivity().get(this.getPartsOfTheActivity().size()-1).calculateEndTime(), this.getPartsOfTheActivity().get(0).getStartTime()
                ).toMinutes()));
        return determineLowerBoundForMakespan();
    }

    private int determineWidthOfDomain()
    {
        if(this.getListOfTemporalIntervals() != null)
            return Math.toIntExact(Math.abs(Duration.between(
                    this.getListOfTemporalIntervals().get(this.getListOfTemporalIntervals().size()-1).getEndTime(), this.getListOfTemporalIntervals().get(0).getStartTime()
            ).toMinutes()));
        return 0;
    }

    public double findMetric2ForActivity()
    {
        return this.determineLowerBoundForMakespan().doubleValue() / determineWidthOfDomain();
    }

    private Integer determineLowerBoundForMakespan()
    {
        return duration + minimumTemporalDistanceBetweenParts * (determineMinimumNumberOfPartsNeeded()-1);
    }

    private int determineMinimumNumberOfPartsNeeded()
    {
        return (int) Math.ceil((double) this.duration / this.maximumAllowedPartDuration);
    }

    @Override
    public String toString() {
        return "Activity{" +
                "name='" + name + '\'' +
                ", parts=" + partsOfTheActivity +
                ", utilizationValue=" + utilizationValue +
                '}';
    }
}
