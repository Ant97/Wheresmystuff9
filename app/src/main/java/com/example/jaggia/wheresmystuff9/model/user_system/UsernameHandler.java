package com.example.jaggia.wheresmystuff9.model.user_system;

/**
 * This is a username handler class that handles username and name checks
 */
public class UsernameHandler {
    private static final int VALIDUSERNAMELENGTH = 4;
    private static final int VALIDPERSONNAMELENGTH = 1;

    /**
     * This method validate if the username passed in is legal
     * @param username the username to validate
     * @return true if is a valid username false otherwise
     */
    public static boolean validateLegalUsername(String username) {
            return (null != username && !(username.length() < VALIDUSERNAMELENGTH));

    }

    /**
     * this method validate if the name passed is legal
     * @param name name to validate
     * @return true if is a valid name false otherwise
     */
    public static boolean validatePersonName(String name)  {
            return (null != name && !(name.length() < VALIDPERSONNAMELENGTH));

    }
}
