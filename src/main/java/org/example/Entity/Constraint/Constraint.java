package org.example.Entity.Constraint;

import org.example.Entity.Activity.ActivityPart;
import org.example.Entity.TemporalInterval;

import java.util.List;

public interface Constraint
{
    boolean eval();
    List<ActivityPart> propagate();
}
