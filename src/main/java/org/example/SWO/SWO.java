package org.example.SWO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Entity.Activity.Activity;
import org.example.Entity.Schedule;
import org.example.Entity.TemporalInterval;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SWO
{
    private Schedule schedule;

    public void begin()
    {
        schedule.removeUnusableIntervals();
        schedule.sortActivitiesByDifficulty();
        if(schedule.getActivities().isEmpty())
            return;

    }
}
