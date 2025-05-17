//package org.example.Entity.Constraint;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.Setter;
//import org.example.Entity.Activity.Activity;
//import org.example.Entity.Activity.ActivityPart;
//import org.example.Entity.TemporalInterval;
//
//import java.time.LocalTime;
//import java.util.List;
//
//@Getter
//@Setter
//@AllArgsConstructor
//public class OrderingConstraint implements Constraint
//{
//    private Activity activityThatHappensBefore; //before
//    private Activity activityThatHappensAfter; //after
//
//    @Override
//    public boolean eval()
//    {
//        //going strictly by definition
//        for (ActivityPart partOfActivityThatHappensAfter : activityThatHappensAfter.getPartsOfTheActivity())
//        {
//            for (ActivityPart partOfActivityThatHappensBefore : activityThatHappensBefore.getPartsOfTheActivity())
//            {
//                if(partOfActivityThatHappensAfter.getStartTime().isBefore(partOfActivityThatHappensBefore.calculateEndTime()))
//                    return false;
//            }
//        }
//        return true;
//    }
//
//    @Override
//    public List<ActivityPart> propagate()
//    {
//        for (ActivityPart activityPart1 : activityThatHappensBefore.getPartsOfTheActivity())
//        {
//            for (ActivityPart activityPart2 : activityThatHappensAfter.getPartsOfTheActivity())
//            {
//                if(activityPart2.getStartTime().isBefore(activityPart1.calculateEndTime()))
//                {
//                    LocalTime endTimeOfActivityThatHappensAfter = activityPart2.calculateEndTime();
//                    activityPart2.setStartTime(activityPart1.calculateEndTime());
//                    if(activityPart2.getStartTime().isAfter(endTimeOfActivityThatHappensAfter))
//                    {
//                        activityThatHappensAfter.getPartsOfTheActivity().remove(activityPart2);
//                    }
//                }
//            }
//        }
//        return activityThatHappensAfter.getPartsOfTheActivity();
//    }
//}
