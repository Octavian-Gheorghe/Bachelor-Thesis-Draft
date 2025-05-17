package org.example.Entity.Activity;

import lombok.Getter;
import lombok.Setter;
import org.example.Entity.Location;
import org.example.Entity.TemporalInterval;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.lang.Math.max;

@Getter
@Setter
public class Activity {
    private Integer id;
    private String name;

    private Integer duration; // total duration

    // Splitting (interruptibility)
    private Integer maxSplits;
    private List<ActivityPart> parts;
    private Integer minPartDuration;
    private Integer maxPartDuration;
    private Integer minGapBetweenParts;
    private Integer maxGapBetweenParts;

    // Temporal domain
    private List<TemporalInterval> intervals;

    // Location alternatives
    private List<Location> locations;

    // Utilization
    private double utilization;

    public Activity(Integer id, String name, Integer duration,
                    Integer minPartDuration, Integer maxPartDuration,
                    Integer minGapBetweenParts, Integer maxGapBetweenParts,
                    List<TemporalInterval> intervals, List<Location> locations) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.parts = new ArrayList<>();
        configureSplitProperties(minPartDuration, maxPartDuration, minGapBetweenParts, maxGapBetweenParts);
        this.intervals = intervals;
        this.locations = locations;
        this.utilization = 0;
    }

    private void configureSplitProperties(Integer minPartDuration, Integer maxPartDuration, Integer minGap, Integer maxGap) {
        this.maxSplits = minRequiredSplits();

        if (maxSplits == 1)
        {
            this.minPartDuration = this.duration;
            this.maxPartDuration = this.duration;
            this.minGapBetweenParts = 0;
            this.maxGapBetweenParts = 0;
            this.parts.add(new ActivityPart());
        }
        else
        {
            this.minPartDuration = minPartDuration;
            this.maxPartDuration = maxPartDuration;
            this.minGapBetweenParts = minGap;
            this.maxGapBetweenParts = maxGap;
            for (int i = 0; i < maxSplits; i++)
            {
                this.parts.add(new ActivityPart());
            }
        }
    }

    public double calculateDifficulty() {
        return max(metricDurationOverNetAvailability(), metricMinMakespanOverDomainWidth());
    }

    public void cleanUselessIntervals() {
        Iterator<TemporalInterval> it = intervals.iterator();
        while (it.hasNext()) {
            if (it.next().getWeight() < minPartDuration) {
                it.remove();
            }
        }
    }

    private double netAvailableTime() {
        return intervals.stream()
                .mapToDouble(TemporalInterval::getWeight)
                .sum();
    }

    private double metricDurationOverNetAvailability() {
        return duration / netAvailableTime();
    }

    private double metricMinMakespanOverDomainWidth() {
        return (double) minMakespanEstimate() / domainWidth();
    }

    private int domainWidth() {
        if (intervals == null || intervals.size() < 2) return 0;
        return Math.toIntExact(Duration.between(
                intervals.get(0).getStartTime(),
                intervals.get(intervals.size() - 1).getEndTime()
        ).toMinutes());
    }

    private int minMakespanEstimate() {
        return duration + minGapBetweenParts * (minRequiredSplits() - 1);
    }

    private int minRequiredSplits() {
        return (int) Math.ceil((double) duration / maxPartDuration);
    }

    @Override
    public String toString() {
        return "Activity{" +
                "name='" + name + '\'' +
                ", parts=" + parts +
                ", utilization=" + utilization +
                '}';
    }
}