package com.example.testSGCIB.exception;

public class MowerInstructionException extends Exception {

    public MowerInstructionException() {
        super("The mower Instruction is not recognized");
    }

    public MowerInstructionException(String message) {
        super(message);
    }

}
