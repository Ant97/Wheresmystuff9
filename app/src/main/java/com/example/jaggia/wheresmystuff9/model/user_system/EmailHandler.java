package com.example.jaggia.wheresmystuff9.model.user_system;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * This is a email handler class that validate if the email is legal
 */
public class EmailHandler {
    private static final int VALIDEMAILLENGTH = 4;


    /**
     * This method validate if the email format passed in is legal
     * @param email email to check
     * @return true if email is legal false otherwise
     */
    public static boolean validateEmailFormat(String email) {
        if(email == null){
            return false;
        }
        if(email.contains(" ")){
            return false;
        }
        if(email.contains("@")) {
            ArrayList<String> splitEmail = new ArrayList<>(Arrays.asList(email.split("@", -1)));
            if(splitEmail.size() == 2){
                String local = splitEmail.get(0);
                String server = splitEmail.get(1);
                if(local.length() > 0 && !local.contains(("..")) && server.length() > 2 && server.contains(".") && !server.contains("..")){
                    ArrayList<String> splitServer = new ArrayList<>(Arrays.asList(server.split("\\.")));
                    if(splitServer.get(0).length()>0 && splitServer.get(splitServer.size()-1).length() > 0) {
                        return true;
                    }
                }
            }
        }
        return false;
        //return null != email && email.length() >= VALIDEMAILLENGTH && email.contains("@") && email.contains(".");
    }
}
