package org.example.Entity.Constraint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.Entity.Activity.Activity;
import org.example.Entity.Activity.ActivityPart;

@Getter
@Setter
@AllArgsConstructor
public class OrderingConstraint implements Constraint
{
    private Activity activity1;
    private Activity activity2;

    @Override
    public boolean isSatisfied()
    {
        //going strictly by definition
        for (ActivityPart activityPart2 : activity2.getPartsOfTheActivity())
        {
            for (ActivityPart activityPart1 : activity1.getPartsOfTheActivity())
            {
                if(activityPart2.getStartTime().isBefore(activityPart1.calculateEndTime()))
                    return false;
            }
        }
        return true;
    }
}
