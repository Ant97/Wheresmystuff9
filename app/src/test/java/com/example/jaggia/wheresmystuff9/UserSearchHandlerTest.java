package com.example.jaggia.wheresmystuff9;
import com.example.jaggia.wheresmystuff9.model.user_system.User;
import com.example.jaggia.wheresmystuff9.model.user_system.UserSearchHandler;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.util.LinkedList;
import java.util.List;
/**
 * Created by Yuli
 * This class tests all of methods in UserSearchHandler class
 */

public class UserSearchHandlerTest {
    private List<User> users;
    private User Yuli = new User("Yuli", "yuli97", "yuli=best", false, "yuliyliu.1997@gmail.com");
    private User An = new User("An", "anT97", "yuli=best", false, "ant1128@gmail.com");
    private User James = new User("James", "JamesG", "yuli=best", true, "jamesG@gmail.com");
    private User Anadi = new User("Anadi", "AnadiJ", "yuli=best", false, "anadiJ@gmail.com");
    private User Annette = new User("Annette", "AnnetteC", "yuli=best", false, "annetteC@gmail.com");

    //----------------------helper methods--------------------------------

    /**
     * This method populate a list of users
     */
    public void populateUsers() {
        users = new LinkedList<>();
        users.add(Yuli);
        users.add(An);
        users.add(James);
        users.add(Anadi);
        users.add(Annette);
    }

    /**
     * This method check if actual user matches the expected user
     *
     * @param expect expect user
     * @param actual actual user
     */
    public void assertUserEqual(User expect, User actual) {
        assertEquals("User name does not match", expect.getName(), actual.getName());
        assertEquals("User username does not match", expect.getUsername(), actual.getUsername());
        assertEquals("User password does not match", expect.getPassword(), actual.getPassword());
        assertEquals("User email does not match", expect.getEmail(), actual.getEmail());
        assertEquals("User id does not match", expect.getId(), actual.getId());
        assertSame("User does not match", expect, actual);
    }

    //-----------------findUserByUsername Tests--------------------------
    @Test(expected = IllegalArgumentException.class)
    public void findUserByUsernameTestNullCheck() {
        try {
            UserSearchHandler.findUserByUsername(null, "yuliyliu.1997@gmail.com");
        } catch (Exception e) {
            assertEquals("Fail to throw IllegalArgumentException when user list is empty",
                    IllegalArgumentException.class, e.getClass());
            populateUsers();
            assertNull("fail to return null when the name passed in is null",
                    UserSearchHandler.findUserByUsername(users, null));
            throw e;

        }
    }

    @Test
    public void findUserByUsernameListEmpty() {
        users = new LinkedList<>();
        assertNull("Fail to return null when list is empty",
                UserSearchHandler.findUserByUsername(users, "Yuli"));
        assertNull("Fail to return null when list is empty",
                UserSearchHandler.findUserByUsername(users, "An"));
        assertNull("Fail to return null when list is empty",
                UserSearchHandler.findUserByUsername(users, "James"));
    }

    @Test
    public void findUserByUsernameStringEmpty() {
        populateUsers();
        assertNull("Fail to return null when the string is empty",
                UserSearchHandler.findUserByUsername(users, ""));
    }

    @Test
    public void findUserByUsernameTestThere() {
        populateUsers();
        assertUserEqual(Yuli, UserSearchHandler.findUserByUsername(users, Yuli.getUsername()));
        assertUserEqual(An, UserSearchHandler.findUserByUsername(users, An.getUsername()));
        assertUserEqual(James, UserSearchHandler.findUserByUsername(users, James.getUsername()));
        assertUserEqual(Anadi, UserSearchHandler.findUserByUsername(users, Anadi.getUsername()));
        assertUserEqual(Annette, UserSearchHandler.findUserByUsername(users, Annette.getUsername()));
    }

    @Test
    public void findUserByUsernameTestNotThere() {
        populateUsers();
        User noneUser1 = new User("Annette", "AnnetteCN", "yuli=best", false, "annetteC@gmail.com");
        User noneUser2 = new User("Yuli", "yuli97N", "yuli=best", false, "yuliyliu.1997@gmail.com");
        User noneUser3 = new User("An", "anTN", "yuli=best", false, "ant1128@gmail.com");
        User noneUser4 = new User("James", "JamesGN", "yuli=best", true, "jamesG@gmail.com");
        User noneUser5 = new User("Anadi", "AnadiJN", "yuli=best", false, "anadiJ@gmail.com");
        assertNull("does not return null when user is no in the list",
                UserSearchHandler.findUserByUsername(users, noneUser1.getUsername()));
        assertNull("does not return null when user is no in the list",
                UserSearchHandler.findUserByUsername(users, noneUser2.getUsername()));
        assertNull("does not return null when user is no in the list",
                UserSearchHandler.findUserByUsername(users, noneUser3.getUsername()));
        assertNull("does not return null when user is no in the list",
                UserSearchHandler.findUserByUsername(users, noneUser4.getUsername()));
        assertNull("does not return null when user is no in the list",
                UserSearchHandler.findUserByUsername(users, noneUser5.getUsername()));
    }


    //-----------------findUserByEmail Tests--------------------------

    @Test(expected = IllegalArgumentException.class)
    public void findUserByEmailTestNullCheck() {
        try {
            UserSearchHandler.findUserByEmail(null, "yuliyliu.1997@gmail.com");
        } catch (Exception e) {
            assertEquals("Fail to throw IllegalArgumentException when user list is empty",
                    IllegalArgumentException.class, e.getClass());
            populateUsers();
            assertNull("fail to return null when the email passed in is null",
                    UserSearchHandler.findUserByEmail(users, null));
            throw e;

        }
    }

    @Test
    public void findUserByEmailListEmpty() {
        users = new LinkedList<>();
        assertNull("Fail to return null when list is empty",
                UserSearchHandler.findUserByEmail(users, Yuli.getEmail()));
        assertNull("Fail to return null when list is empty",
                UserSearchHandler.findUserByEmail(users, An.getEmail()));
        assertNull("Fail to return null when list is empty",
                UserSearchHandler.findUserByEmail(users, James.getEmail()));
    }

    @Test
    public void findUserByEmailEmailEmpty() {
        populateUsers();
        assertNull("Fail to return null when the string is empty",
                UserSearchHandler.findUserByEmail(users, ""));
    }

    @Test
    public void findUserByEmailTestThere() {
        populateUsers();
        assertUserEqual(Yuli, UserSearchHandler.findUserByEmail(users, Yuli.getEmail()));
        assertUserEqual(An, UserSearchHandler.findUserByEmail(users, An.getEmail()));
        assertUserEqual(James, UserSearchHandler.findUserByEmail(users, James.getEmail()));
        assertUserEqual(Anadi, UserSearchHandler.findUserByEmail(users, Anadi.getEmail()));
        assertUserEqual(Annette, UserSearchHandler.findUserByEmail(users, Annette.getEmail()));
    }

    @Test
    public void findUserByEmailTestNotThere() {
        populateUsers();
        User noneUser1 = new User("Annette", "AnnetteCN", "yuli=best", false, "NannetteC@gmail.com");
        User noneUser2 = new User("Yuli", "yuli97N", "yuli=best", false, "Nyuliyliu.1997@gmail.com");
        User noneUser3 = new User("An", "anTN", "yuli=best", false, "Nant1128@gmail.com");
        User noneUser4 = new User("James", "JamesGN", "yuli=best", true, "NjamesG@gmail.com");
        User noneUser5 = new User("Anadi", "AnadiJN", "yuli=best", false, "NanadiJ@gmail.com");
        assertNull("does not return null when user is no in the list",
                UserSearchHandler.findUserByEmail(users, noneUser1.getEmail()));
        assertNull("does not return null when user is no in the list",
                UserSearchHandler.findUserByEmail(users, noneUser2.getEmail()));
        assertNull("does not return null when user is no in the list",
                UserSearchHandler.findUserByEmail(users, noneUser3.getEmail()));
        assertNull("does not return null when user is no in the list",
                UserSearchHandler.findUserByEmail(users, noneUser4.getEmail()));
        assertNull("does not return null when user is no in the list",
                UserSearchHandler.findUserByEmail(users, noneUser5.getEmail()));


    }
}
