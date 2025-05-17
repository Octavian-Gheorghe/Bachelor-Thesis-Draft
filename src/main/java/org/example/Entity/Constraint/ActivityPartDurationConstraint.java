//package org.example.Entity.Constraint;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.Setter;
//import org.example.Entity.Activity.Activity;
//import org.example.Entity.Activity.ActivityPart;
//
//import java.util.List;
//
//@Getter
//@Setter
//@AllArgsConstructor
//public class ActivityPartDurationConstraint implements Constraint
//{
//    Activity activity;
//
//    @Override
//    public boolean eval()
//    {
//        Integer totalDurationOfActivityParts = 0;
//        for(ActivityPart activityPart : activity.getPartsOfTheActivity())
//        {
//            totalDurationOfActivityParts += activityPart.getDuration();
//        }
//        return totalDurationOfActivityParts >= activity.getMinimumDuration() && totalDurationOfActivityParts <= activity.getMaximumDuration();
//    }
//
//    public List<ActivityPart> propagate()
//    {
//        Integer totalDurationOfActivityParts = 0;
//        for(ActivityPart activityPart : activity.getPartsOfTheActivity())
//        {
//            totalDurationOfActivityParts += activityPart.getDuration();
//        }
//        if(totalDurationOfActivityParts < activity.getMinimumDuration())
//        {
//            Integer rest = activity.getMinimumDuration() - totalDurationOfActivityParts;
//            activity.getPartsOfTheActivity().get(activity.getPartsOfTheActivity().size()-1).setDuration(activity.getPartsOfTheActivity().get(activity.getPartsOfTheActivity().size()-1).getDuration() + rest);
//        }
//        if (totalDurationOfActivityParts > activity.getMaximumDuration())
//        {
//            Integer rest = totalDurationOfActivityParts - activity.getMaximumDuration();
//            int index = activity.getPartsOfTheActivity().size() - 1;
//            while(rest != 0 && index != 0)
//            {
//                activity.getPartsOfTheActivity().get(index).setDuration();
//                index--;
//            }
//        }
//    }
//}
