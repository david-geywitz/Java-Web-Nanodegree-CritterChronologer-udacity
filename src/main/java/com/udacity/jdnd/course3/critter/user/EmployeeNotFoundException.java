package com.udacity.jdnd.course3.critter.user;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException() {
        super("Sorry, there isn't any Employee");
    }

    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
