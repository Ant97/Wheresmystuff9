package com.example.jaggia.wheresmystuff9;

import com.example.jaggia.wheresmystuff9.model.user_system.EmailHandler;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by James on 7/19/2017.
 */

public class JavaEmailHandlerTest {
    private List<String> testStringsFail = new ArrayList<>();
    private List<String> testStringsTrue = new ArrayList<>();
    @Before
    public void setUp(){
        testStringsFail.add("");
        testStringsFail.add("notAnEmail");
        testStringsFail.add("stillnotAnEmail@next");
        testStringsFail.add(".not");
        testStringsFail.add("@not");
        testStringsFail.add("@.");
        testStringsFail.add(".@");

        testStringsTrue.add("jamesg166@comcast.net");
        testStringsTrue.add("thiscountsasanemail@email.com");
    }
    @Test
    public void failTests(){
        try {
            for (String s : testStringsFail) {
                assertFalse(EmailHandler.validateEmailFormat(s));
            }
            System.out.println("ValidateEmailFormat failTests: Success!");
        } catch(Exception e){
            System.out.println("ValidateEmailFormat failTests: FAILED.");
        }
    }
    @Test
    public void trueTests(){
        try {
            for (String s : testStringsTrue) {
                assertTrue(EmailHandler.validateEmailFormat(s));
            }
            System.out.println("ValidateEmailFormat trueTests: Success!");
        } catch(Exception e){
            System.out.println("ValidateEmailFormat trueTests: FAILED.");
        }
    }
}
