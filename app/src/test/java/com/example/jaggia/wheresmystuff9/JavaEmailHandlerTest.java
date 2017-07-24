package com.example.jaggia.wheresmystuff9;

import com.example.jaggia.wheresmystuff9.model.user_system.EmailHandler;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;


public class JavaEmailHandlerTest {
    private final List<String> testStringsFail = new ArrayList<>();
    private final List<String> testStringsTrue = new ArrayList<>();
    @Before
    public void setUp(){

        //branch tests

        //null test
        testStringsFail.add(null);

        //test for spaces
        testStringsFail.add(" ");
        testStringsFail.add("notan email@no.com");

        //test for no @
        testStringsFail.add("notAnEmail");

        //test for more than one @ (splitEmail.size() > 2 if more than one @)
        testStringsFail.add("not@an@email");
        testStringsFail.add("not@an@email.com");

        //test for no . in domain
        testStringsFail.add("stillnotAnEmail@next");

        //testing for empty local
        testStringsFail.add("@not.com");
        //testing for all empty parts
        testStringsFail.add("@.");
        //testing for various empty domain elements
        testStringsFail.add("j@.");
        testStringsFail.add("j@j.");
        testStringsFail.add("j@.k");
        testStringsFail.add(".@");

        //test for double dots
        testStringsFail.add("almostanemail@notquite..com");
        testStringsFail.add("almost..anemail@notquite.com");

        //these all work as emails
        testStringsTrue.add("jamesg166@comcast.net");
        testStringsTrue.add("this.counts.as.an.email@email.com");
        testStringsTrue.add("8@j.k");
    }
    @Test
    public void failTests(){
        for (String s : testStringsFail) {
            assertFalse(EmailHandler.validateEmailFormat(s));
        }
        System.out.println("ValidateEmailFormat failTests: Success!");

    }
    @Test
    public void trueTests(){
        for (String s : testStringsTrue) {
            assertTrue(EmailHandler.validateEmailFormat(s));
        }
        System.out.println("ValidateEmailFormat trueTests: Success!");
    }
}
