package com.example.jaggia.wheresmystuff9.model.user_system;

/**
 * Created by James on 7/11/2017.
 */
public class EmailHandler {
    private static final int VALIDEMAILLENGTH = 4;
    public static boolean validateEmailFormat(String email){
        if(null == email){
            return false;
        }
        if(email.length() < VALIDEMAILLENGTH){
            return false;
        }
        if(!email.contains("@")){
            return false;
        }
        if(!email.contains(".")){
            return false;
        }
        return true;
    }
}
