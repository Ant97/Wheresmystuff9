package com.example.jaggia.wheresmystuff9.model.error_coding;

/**
 * Created by James on 7/18/2017.
 */

public class DuplicateUsernameException extends Exception {
    private final String message;
    public DuplicateUsernameException(){
        super();
        message = "The username has already been taken";
    }
    public DuplicateUsernameException(String message){
        super(message);
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
}
