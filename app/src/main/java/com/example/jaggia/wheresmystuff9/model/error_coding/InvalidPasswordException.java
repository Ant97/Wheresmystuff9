package com.example.jaggia.wheresmystuff9.model.error_coding;

/**
 * Created by James on 7/18/2017.
 */

public class InvalidPasswordException extends Exception {
    private final String message;
    public InvalidPasswordException(){
        super();
        message = "The password is not valid";
    }
    public InvalidPasswordException(String message){
        super(message);
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
}
