package com.udacity.jdnd.course3.critter.pet;

public class PetNotFoundException extends RuntimeException {
    public PetNotFoundException() {
        super("Sorry, there isn't any Pet");
    }

    public PetNotFoundException(String message) {
        super(message);
    }
}
