package org.example.Entity.Constraint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.Entity.Activity.Activity;
import org.example.Entity.Activity.ActivityPart;

@Getter
@Setter
@AllArgsConstructor
public class ActivityPartDurationConstraint implements Constraint
{
    Activity activity;

    @Override
    public boolean isSatisfied()
    {
        Integer totalDurationOfActivityParts = 0;
        for(ActivityPart activityPart : activity.getPartsOfTheActivity())
        {
            totalDurationOfActivityParts += activityPart.getDuration();
        }
        return totalDurationOfActivityParts >= activity.getMinimumDuration() && totalDurationOfActivityParts <= activity.getMaximumDuration();
    }
}
