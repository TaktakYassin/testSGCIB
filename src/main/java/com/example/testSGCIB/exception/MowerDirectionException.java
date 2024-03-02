package com.example.testSGCIB.exception;

public class MowerDirectionException extends Exception {

    public MowerDirectionException() {
        super("The mower Direction is not recognized");
    }

    public MowerDirectionException(String message) {
        super(message);
    }

}
