package com.example.jaggia.wheresmystuff9.model.error_coding;

/**
 * Created by James on 7/18/2017.
 */

public class PasswordMismatchException extends RuntimeException {
    private final String message;
    public PasswordMismatchException(){
        super();
        message = "The passwords do not match";
    }
    public PasswordMismatchException(String message){
        super(message);
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
}

