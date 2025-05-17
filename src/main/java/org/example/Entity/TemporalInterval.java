package org.example.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;

@AllArgsConstructor
@Getter
public class TemporalInterval
{
    private LocalTime startTime;
    private LocalTime endTime;
}
