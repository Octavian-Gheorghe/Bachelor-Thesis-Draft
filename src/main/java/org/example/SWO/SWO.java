package org.example.SWO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Entity.Activity.Activity;
import org.example.Entity.Constraint.Constraint;
import org.example.Entity.Schedule;
import org.example.Entity.TemporalInterval;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SWO
{
    private Schedule schedule;
    private List<Constraint> allConstraints;

    public void begin()
    {
        schedule.removeUnusableIntervals();
        schedule.sortActivitiesByDifficulty();
        if(schedule.getActivities().isEmpty())
            return;
        schedule.setActivitiesAsNonTroublemakers();
    }
}
