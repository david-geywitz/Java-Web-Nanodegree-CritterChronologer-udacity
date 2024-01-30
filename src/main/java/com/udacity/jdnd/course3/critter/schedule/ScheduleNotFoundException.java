package com.udacity.jdnd.course3.critter.schedule;

public class ScheduleNotFoundException extends RuntimeException {
    public ScheduleNotFoundException() {
        super("Sorry, there isn't any Schedule available");
    }

    public ScheduleNotFoundException(String message) {
        super(message);
    }
}
