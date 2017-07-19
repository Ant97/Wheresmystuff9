package com.example.jaggia.wheresmystuff9.model.error_coding;

/**
 * Created by James on 7/18/2017.
 */

public class InvalidPasswordException extends RuntimeException {
    private final String message;
    /**
     * Sets the message to default: The password is not valid
     */
    public InvalidPasswordException(){
        super();
        message = "The password is not valid";
    }
    /**
     * Allows the writer to specify a message to be used when an error is thrown
     * @param message the message to be given
     */
    public InvalidPasswordException(String message){
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
