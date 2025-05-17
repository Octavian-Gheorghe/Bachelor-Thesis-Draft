package org.example.Entity.Activity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ActivityPart
{
    private LocalTime startTime;
    private Integer duration;

    public LocalTime calculateEndTime()
    {
        return startTime.plusMinutes(duration);
    }

    @Override
    public String toString() {
        return "ActivityPart{" +
                "startTime=" + startTime +
                ", duration=" + duration +
                ", endTime=" + calculateEndTime() +
                '}';
    }
}
