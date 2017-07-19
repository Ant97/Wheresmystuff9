package com.example.jaggia.wheresmystuff9.model.error_coding;


public class NoNameException extends RuntimeException {
    private final String message;
    /**
     * Sets the message to default: "No name given"
     */
    public NoNameException(){
        super();
        message = "No name given";
    }
    /**
     * Allows the writer to specify a message to be used when an error is thrown
     * @param message the message to be given
     */
    public NoNameException(String message){
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
