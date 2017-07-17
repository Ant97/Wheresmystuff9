package com.example.jaggia.wheresmystuff9.model.user_system;

public class PasswordHandler {
    private static final int VALIDPASSWORDLENGTH = 6;
    public static boolean validatePasswordMatch(String password1, String password2){
        return (password1.equals(password2));
    }

    public static boolean validatePassword(String password){
        return !(password.length() < VALIDPASSWORDLENGTH);
    }
}
