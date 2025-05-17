//package org.example.Entity.Constraint;
//
//import org.example.Entity.Activity.Activity;
//import org.example.Entity.Activity.ActivityPart;
//
//public class AllowedPartDurationConstraint implements Constraint
//{
//    Activity activity;
//
//    @Override
//    public boolean isSatisfied()
//    {
//        for(ActivityPart activityPart : activity.getPartsOfTheActivity())
//        {
//            if(activityPart.getDuration() < activity.getMinimumAllowedPartDuration() || activityPart.getDuration() > activity.getMaximumAllowedPartDuration())
//                return false;
//        }
//        return true;
//    }
//}
