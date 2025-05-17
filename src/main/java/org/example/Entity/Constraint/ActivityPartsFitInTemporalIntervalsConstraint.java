package org.example.Entity.Constraint;

import org.example.Entity.Activity.Activity;
import org.example.Entity.Activity.ActivityPart;
import org.example.Entity.TemporalInterval;

import java.time.LocalTime;

public class ActivityPartsFitInTemporalIntervalsConstraint implements Constraint
{
    Activity activity;

    @Override
    public boolean isSatisfied()
    {
        for(ActivityPart activityPart : activity.getPartsOfTheActivity())
        {
            LocalTime startTime = activityPart.getStartTime();
            LocalTime endTime = activityPart.calculateEndTime();
            boolean itFits = false;

            for(TemporalInterval temporalInterval : activity.getListOfTemporalIntervals())
            {
                if(temporalInterval.getStartTime().isBefore(startTime) && temporalInterval.getEndTime().isAfter(endTime))
                    itFits = true;
            }

            if(!itFits)
                return false;
        }
        return true;
    }
}
