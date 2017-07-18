package com.example.jaggia.wheresmystuff9.model.error_coding;

/**
 * Created by James on 7/18/2017.
 */

public class InvalidUsernameException extends Exception {
    private final String message;
    public InvalidUsernameException(){
        super();
        message = "The username is invalid";
    }
    public InvalidUsernameException(String message){
        super(message);
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
}
