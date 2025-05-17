package org.example.Entity;

import lombok.Getter;
import lombok.Setter;
import org.example.Entity.Activity.Activity;
import java.util.List;
import java.util.Objects;

/*Pseudo Repository*/
@Getter
@Setter
public class Schedule
{
    private List<Activity> activities;

    public void addAnotherActivity(Activity activity)
    {
        activities.add(activity);
    }

    public void removeActivity(Integer id)
    {
        activities.removeIf(activityThatHasId -> Objects.equals(activityThatHasId.getId(), id));
    }

    public boolean searchForActivity(Integer id)
    {
        for(Activity activityThatHasId : activities)
        {
            if(activityThatHasId.getId().equals(id))
                return true;
        }
        return false;
    }
}
