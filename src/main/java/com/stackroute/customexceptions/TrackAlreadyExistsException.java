package com.stackroute.customexceptions;

public class TrackAlreadyExistsException extends Exception {
    String message;
    public TrackAlreadyExistsException()
    {

    }

    public TrackAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }


}
