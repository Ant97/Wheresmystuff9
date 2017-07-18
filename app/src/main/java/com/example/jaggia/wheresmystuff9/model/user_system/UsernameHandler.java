package com.example.jaggia.wheresmystuff9.model.user_system;

import com.example.jaggia.wheresmystuff9.model.error_coding.InvalidUsernameException;
import com.example.jaggia.wheresmystuff9.model.error_coding.NoNameException;

public class UsernameHandler {
    private static final int VALIDUSERNAMELENGTH = 4;
    private static final int VALIDPERSONNAMELENGTH = 1;
    public static boolean validateLegalUsername(String username) {
        return (null != username && !(username.length() < VALIDUSERNAMELENGTH));
    }
    public static boolean validatePersonName(String name) throws NoNameException {
        if(null != name && !(name.length() < VALIDPERSONNAMELENGTH)) {
            return true;
        }else {
            throw new NoNameException();
        }
    }
}
