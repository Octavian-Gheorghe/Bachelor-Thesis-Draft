package org.example.Entity;

import lombok.Getter;
import lombok.Setter;
import org.example.Entity.Activity.Activity;

import java.util.ArrayList;
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

    public void removeUnusableIntervals()
    {
        for(Activity activityToBeCleaned : activities)
        {
            activityToBeCleaned.removeUselessIntervals();
        }
    }

    public List<Double> determineDifficultiesOfActivities()
    {
        List<Double> difficulties = new ArrayList<>();
        for(Activity activity : activities)
        {
            Double difficulty = activity.determineDifficultyOfActivityPlacement();
            if(difficulty > 1)
                return null;
            difficulties.add(difficulty);
        }
        return difficulties;
    }

    public void sortActivitiesByDifficulty() {
        for (Activity activity : activities) {
            if (activity.determineDifficultyOfActivityPlacement() > 1) {
                activities.clear();
                return;
            }
        }

        activities.sort((a1, a2) -> {
            Double diff1 = a1.determineDifficultyOfActivityPlacement();
            Double diff2 = a2.determineDifficultyOfActivityPlacement();
            return Double.compare(diff1, diff2);
        });
    }

}
