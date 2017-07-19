package com.example.jaggia.wheresmystuff9.model.error_coding;

/**
 * Created by James on 7/18/2017.
 */

public class InvalidUsernameException extends RuntimeException {
    private final String message;
    /**
     * Sets the message to default: The username is invalid
     */
    public InvalidUsernameException(){
        super();
        message = "The username is invalid";
    }
    /**
     * Allows the writer to specify a message to be used when an error is thrown
     * @param message the message to be given
     */
    public InvalidUsernameException(String message){
        super(message);
        this.message = message;
    }
    /**
     * gets the message for the exception
     * @return the message associated with the exception
     */
    public String getMessage(){
        return message;
    }
}
