package com.example.jaggia.wheresmystuff9.model.user_system;

import com.example.jaggia.wheresmystuff9.model.error_coding.InvalidPasswordException;
import com.example.jaggia.wheresmystuff9.model.error_coding.PasswordMismatchException;

public class PasswordHandler {
    private static final int VALIDPASSWORDLENGTH = 6;
    public static boolean validatePasswordMatch(String password1, String password2) throws PasswordMismatchException{
        if(password1.equals(password2)) {
            return true;
        } else {
            throw new PasswordMismatchException();
        }
    }

    public static boolean validatePassword(String password) throws InvalidPasswordException {
        if(!(password.length() < VALIDPASSWORDLENGTH)){
            return true;
        }
            throw new InvalidPasswordException();
    }
}
