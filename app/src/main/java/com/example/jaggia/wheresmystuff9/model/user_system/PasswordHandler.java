package com.example.jaggia.wheresmystuff9.model.user_system;

import com.example.jaggia.wheresmystuff9.model.error_coding.ErrorCode;

/**
 * Created by James on 7/11/2017.
 */

public class PasswordHandler {
    public ErrorCode validatePasswordMatch(String password1, String password2){
        if(password1.equals(password2)){
            return ErrorCode.SUCCESS;
        }
        return ErrorCode.PASSWORDMISMATCH;
    }

    public ErrorCode validatePassword(String password){
        if(password.length() > 6){
            return ErrorCode.SUCCESS;
        } else {
            return ErrorCode.ILLEGALPASSWORD;
        }
    }
}
