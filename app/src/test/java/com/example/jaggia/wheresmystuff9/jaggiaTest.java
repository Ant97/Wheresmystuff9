package com.example.jaggia.wheresmystuff9;

import com.example.jaggia.wheresmystuff9.model.user_system.User;
import com.example.jaggia.wheresmystuff9.model.user_system.UserSearchHandler;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class jaggiaTest {

    private List<User> users;
    @Before
    public void setUp() {
        users = new LinkedList<>();
        users.add(0, new User("Jaggia", "user1", "anadi2231", true, "anadi.jaggia@gmail.com"));
        users.add(1, new User("An", "user2", "pass1234", false, "an.young@gmail.com"));
        users.add(2, new User("Yuli", "user3", "word5678", false, "yuli917@gmail.com"));
    }

    @Test
    public void testUserSearchHandler() {
        assertEquals(users.get(0), UserSearchHandler.findUserByUsername(users, "user1"));
        assertNull(UserSearchHandler.findUserByUsername(users, "alskdfjlskdjf"));
        //assertEquals(users.get(1), UserSearchHandler.findUserByEmail(users, "an.young@gmail.com"));
        //assertNull(UserSearchHandler.findUserByEmail(users, "alskdfjlskdjf"));
    }
}