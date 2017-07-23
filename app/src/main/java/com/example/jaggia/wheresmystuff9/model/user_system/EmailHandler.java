package com.example.jaggia.wheresmystuff9.model.user_system;

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
        return null != email && email.length() >= VALIDEMAILLENGTH && email.contains("@") && email.contains(".");
    }
}
