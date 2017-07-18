package com.example.jaggia.wheresmystuff9.model.error_coding;

/**
 * Created by James on 7/18/2017.
 */

public class DuplicateEmailException extends Exception {
    public String message;
    public DuplicateEmailException(){
        super();
        message = "The email is tied to another account";
    }
    public DuplicateEmailException(String message){
        super(message);
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
}
