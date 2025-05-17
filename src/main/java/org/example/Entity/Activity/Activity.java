package org.example.Entity.Activity;

import lombok.Getter;
import lombok.Setter;
import org.example.Entity.Location;
import org.example.Entity.TemporalInterval;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Activity
{
    private Integer id;
    private String name;

    //the duration range
    private Integer minimumDuration;
    private Integer maximumDuration;

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

    public Activity(Integer id, String name, Integer minimumDuration, Integer maximumDuration, Integer numberOfMaxPartsInWhichCanBeSplit, Integer minimumAllowedPartDuration, Integer maximumAllowedPartDuration, Integer minimumTemporalDistanceBetweenParts, Integer maximumTemporalDistanceBetweenParts, List<TemporalInterval> listOfTemporalIntervals, List<Location> possibleLocations)
    {
        this.id = id;
        this.name = name;
        this.minimumDuration = minimumDuration;
        this.maximumDuration = maximumDuration;
        this.numberOfMaxPartsInWhichCanBeSplit = numberOfMaxPartsInWhichCanBeSplit;
        partsOfTheActivity = new ArrayList<>();
        interpretNrOfInterruptibleParts(numberOfMaxPartsInWhichCanBeSplit, minimumAllowedPartDuration, maximumAllowedPartDuration, minimumTemporalDistanceBetweenParts, maximumTemporalDistanceBetweenParts);
        this.listOfTemporalIntervals = listOfTemporalIntervals;
        this.possibleLocations = possibleLocations;
        this.utilizationValue = 0;
    }

    private void interpretNrOfInterruptibleParts(Integer numberOfMaxPartsInWhichCanBeSplit, Integer minimumAllowedPartDuration, Integer maximumAllowedPartDuration, Integer minimumTemporalDistanceBetweenParts, Integer maximumTemporalDistanceBetweenParts)
    {
        if(this.numberOfMaxPartsInWhichCanBeSplit < 1)
            this.numberOfMaxPartsInWhichCanBeSplit = 1;
        if(this.numberOfMaxPartsInWhichCanBeSplit == 1)
        {
            ActivityPart activityPart = new ActivityPart();
            partsOfTheActivity.add(activityPart);
            this.minimumAllowedPartDuration = this.minimumDuration;
            this.maximumAllowedPartDuration = this.maximumDuration;
            this.minimumTemporalDistanceBetweenParts = 0;
            this.maximumTemporalDistanceBetweenParts = 0;
        }
        else
        {
            this.minimumAllowedPartDuration = minimumAllowedPartDuration;
            this.maximumAllowedPartDuration = maximumAllowedPartDuration;
            this.minimumTemporalDistanceBetweenParts = minimumTemporalDistanceBetweenParts;
            this.maximumTemporalDistanceBetweenParts = maximumTemporalDistanceBetweenParts;
            for(int i = 0; i < numberOfMaxPartsInWhichCanBeSplit; i++)
            {
                ActivityPart activityPart = new ActivityPart();
                partsOfTheActivity.add(activityPart);
            }
        }
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
