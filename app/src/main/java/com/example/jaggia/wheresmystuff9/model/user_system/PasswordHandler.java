package com.example.jaggia.wheresmystuff9.model.user_system;

/**
 * This is a password handler class that validates password
 */
public class PasswordHandler {
    private static final int VALIDPASSWORDLENGTH = 6;

    /**
     * This method check if two password matches;
     * @param password1 first password to check
     * @param password2 second password to check
     * @return true if passwords match false otherwise
     */
    public static boolean validatePasswordMatch(String password1, String password2){

        return password1 != null && password2 != null && validatePassword(password1)
                && validatePassword(password2) && password1.equals(password2);

    }

    /**
     * This method validate the password passed in
     * @param password password to validate
     * @return true if password is legal false otherwise
     */
    public static boolean validatePassword(String password) {
        return password != null && ((password.length() > VALIDPASSWORDLENGTH));

    }
}
