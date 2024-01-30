package com.udacity.jdnd.course3.critter.user;

public class CustomerNotFoundException extends RuntimeException {
    public  CustomerNotFoundException() {
        super("Sorry, there are no Customers");
    }

    public CustomerNotFoundException(String message) {
        super(message);
    }
}
