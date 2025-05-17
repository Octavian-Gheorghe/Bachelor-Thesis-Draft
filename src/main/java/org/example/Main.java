package org.example;

import org.example.Entity.Activity.Activity;
import org.example.Entity.Location;
import org.example.Entity.Schedule;
import org.example.Entity.TemporalInterval;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        // Step 1: Create some Locations
        Location home = new Location("Home");
        Location office = new Location("Office");
        Location gym = new Location("Gym");

        // Step 2: Create TemporalIntervals (time windows)
        TemporalInterval workHours = new TemporalInterval(
                LocalTime.of(9, 0),
                LocalTime.of(17, 0)
        );

        TemporalInterval gymHours = new TemporalInterval(
                LocalTime.of(18, 0),
                LocalTime.of(22, 0)
        );

        // Step 3: Create Activities
        List<Location> workLocations = List.of(home, office);
        List<Location> gymLocations = List.of(gym);

        List<TemporalInterval> workIntervals = List.of(workHours);
        List<TemporalInterval> gymIntervals = List.of(gymHours);

        Activity writeReport = new Activity(
                1,                          // id
                "Write Report",              // name
                180,                         // duration (minutes)
                1,                           // max number of parts (non-interruptible)
                null,                        // no min part duration needed
                null,                        // no max part duration needed
                null,                        // no min temporal distance needed
                null,                        // no max temporal distance needed
                workIntervals,               // available time intervals
                workLocations                // possible locations
        );

        Activity gymSession = new Activity(
                2,
                "Gym Session",
                60,
                1,
                null,
                null,
                null,
                null,
                gymIntervals,
                gymLocations
        );

        // Step 4: Manually assign example start times and durations (just for demonstration)
        writeReport.getPartsOfTheActivity().get(0).setStartTime(LocalTime.of(9, 0));
        writeReport.getPartsOfTheActivity().get(0).setDuration(240); // 4 hours

        gymSession.getPartsOfTheActivity().get(0).setStartTime(LocalTime.of(19, 0));
        gymSession.getPartsOfTheActivity().get(0).setDuration(90); // 1.5 hours

        // Step 5: Create Schedule and add activities
        List<Activity> activityList = new ArrayList<>();
        Schedule schedule = new Schedule();
        schedule.setActivities(activityList);

        schedule.addAnotherActivity(writeReport);
        schedule.addAnotherActivity(gymSession);

        // Step 6: Print out the activities in the schedule
        System.out.println("Schedule Overview:");
        for (Activity activity : schedule.getActivities()) {
            System.out.println(activity);
        }
    }
}

