package com.example.jaggia.wheresmystuff9.model.error_coding;

/**
 * Created by James on 7/18/2017.
 */

public class NoNameException extends Exception {
    public String message;
    public NoNameException(){
        super();
        message = "No name given";
    }
    public NoNameException(String message){
        super(message);
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
}
