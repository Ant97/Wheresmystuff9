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
        if (password1 != null && password2 != null &&
                validatePassword(password1) && validatePassword(password2)) {
            return password1.equals(password2);
        }
        return false;
    }

    /**
     * This method validate the password passed in
     * @param password password to validate
     * @return true if password is legal false otherwise
     */
    public static boolean validatePassword(String password) {
        if (password != null) {
            return ((password.length() > VALIDPASSWORDLENGTH));
        }
        return false;
    }
}
