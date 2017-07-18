package com.example.jaggia.wheresmystuff9;

import com.example.jaggia.wheresmystuff9.model.user_system.User;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.example.jaggia.wheresmystuff9.model.user_system.UserSearchHandler.findUserByEmail;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@SuppressWarnings("ALL")


public class UserEmailTest {
    private List<User> userList = new ArrayList<>();
    private User annette = new User("Annette", "AUser", "APass", false, "a@a.com");
    private User james = new User("James", "JUser", "JPass", false, "j@j.com");

    @Test
    public void testUserEmail() {

        userList.add(annette);
        userList.add(james);
        assertEquals((User) annette, findUserByEmail(userList, "a@a.com"));
        assertEquals((User) james, findUserByEmail(userList, "j@j.com"));
        assertEquals(null, findUserByEmail(userList, "none"));
    }
}