package com.example.jaggia.wheresmystuff9.model.error_coding;

/**
 * Created by James on 7/18/2017.
 */

public class InvalidEmailException extends RuntimeException {
    private final String message;
    public InvalidEmailException(){
        super();
        message = "The email is invalid";
    }
    public InvalidEmailException(String message){
        super(message);
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
}
