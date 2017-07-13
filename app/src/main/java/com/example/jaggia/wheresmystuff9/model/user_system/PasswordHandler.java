package com.example.jaggia.wheresmystuff9.model.user_system;

import com.example.jaggia.wheresmystuff9.model.error_coding.ErrorCode;

/**
 * Created by James on 7/11/2017.
 */

public class PasswordHandler {
    private static final int VALIDPASSWORDLENGTH = 6;
    public static boolean validatePasswordMatch(String password1, String password2){
        return (password1.equals(password2));
    }

    public static boolean validatePassword(String password){
        return !(password.length() < VALIDPASSWORDLENGTH);
    }
}
