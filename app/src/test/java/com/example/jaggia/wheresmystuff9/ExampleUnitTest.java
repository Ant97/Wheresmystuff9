package com.example.jaggia.wheresmystuff9;

import com.example.jaggia.wheresmystuff9.model.user_system.User;
import com.example.jaggia.wheresmystuff9.model.user_system.UserSearchHandler;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@SuppressWarnings("ALL")
public class ExampleUnitTest {

    private List<User> users;
    @Before
    public void setUp() {
        users = new LinkedList<>();
        users.add(0, new User("Jaggia", "user1", "anadi2231", true, "anadi.jaggia@gmail.com"));
        users.add(1, new User("An", "user2", "pass1234", false, "an.young@gmail.com"));
        users.add(2, new User("Yuli", "user3", "word5678", false, "yuli917@gmail.com"));
    }

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testUserSearchHandler() {
        assertEquals(users.get(0), UserSearchHandler.findUserByUsername(users, "user1"));
        assertEquals(users.get(1), UserSearchHandler.findUserByEmail(users, "an.young@gmail.com"));
        assertNull(UserSearchHandler.findUserByUsername(users, "alskdfjlskdjf"));
        assertNull(UserSearchHandler.findUserByEmail(users, "alskdfjlskdjf"));
    }
}