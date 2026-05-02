package org.example.practice.part5.model;

import java.util.List;

public interface SchedulingStrategy {
    Elevator assign(List<Elevator> elevatorList, Request request);
}
