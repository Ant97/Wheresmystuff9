package com.example.jaggia.wheresmystuff9;
import com.example.jaggia.wheresmystuff9.model.user_system.User;
import com.example.jaggia.wheresmystuff9.model.user_system.UserDatabase;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import com.example.jaggia.wheresmystuff9.model.user_system.UserSearchHandler;
import org.junit.Test;
import java.lang.reflect.Field;
import java.util.LinkedList;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@SuppressWarnings("ALL")
public class ExampleUnitTest {

    private List<User> users;
    private UserDatabase database;
    private List<User> list;
    @Before
    public void setUp() {
        database = new UserDatabase();
        list = new ArrayList<>();
        User user1 = new User("Yuli", "Yuli1", "yuli=best1");
        User user2 = new User("Yuli", "Yuli2", "yuli=best2");
        User user3 = new User("Yuli", "Yuli3", "yuli=best3");
        User user4 = new User("Yuli", "Yuli4", "yuli=best4");
        User user5 = new User("Yuli", "Yuli5", "yuli=best5");
        User user6 = new User("Yuli", "Yuli6", "yuli=best6");
        User user7 = new User("Yuli", "Yuli7", "yuli=best7");
        User user8 = new User("Yuli", "Yuli8", "yuli=best8");
        User user9 = new User("Yuli", "Yuli9", "yuli=best9");
        User user10 = new User("Yuli", "Yuli10", "yuli=best10");
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        list.add(user5);
        list.add(user6);
        list.add(user7);
        list.add(user8);
        list.add(user9);
        list.add(user10);
        setUserList(database, list);
    }

    /**
     * this method sets userdatabase's users list to the user list passed in parameter
     * @param database
     * @param users
     * @param <T>
     */
    private <T> void setUserList(UserDatabase database, List<User> users) {
        try {
            Field field = UserDatabase.class.getDeclaredField("_users");
            field.setAccessible(true);
            field.set(database, users);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void validateUserTestTrue() {
        setUserList(database, list);
        assertTrue(database.validateUser(list.get(0).getUsername(), list.get(0).getPassword()));
        assertTrue(database.validateUser(list.get(1).getUsername(), list.get(1).getPassword()));
        assertTrue(database.validateUser(list.get(2).getUsername(), list.get(2).getPassword()));
        assertTrue(database.validateUser(list.get(3).getUsername(), list.get(3).getPassword()));
        assertTrue(database.validateUser(list.get(4).getUsername(), list.get(4).getPassword()));
        assertTrue(database.validateUser(list.get(5).getUsername(), list.get(5).getPassword()));
        assertTrue(database.validateUser(list.get(6).getUsername(), list.get(6).getPassword()));
        assertTrue(database.validateUser(list.get(7).getUsername(), list.get(7).getPassword()));
        assertTrue(database.validateUser(list.get(8).getUsername(), list.get(8).getPassword()));
        assertTrue(database.validateUser(list.get(9).getUsername(), list.get(9).getPassword()));
    }
    @Test
    public void validateUserTestFalse() {
        setUserList(database, list);
        assertFalse(database.validateUser(list.get(0).getUsername(), list.get(1).getPassword()));
        assertFalse(database.validateUser(list.get(1).getUsername(), list.get(2).getPassword()));
        assertFalse(database.validateUser(list.get(2).getUsername(), list.get(3).getPassword()));
        assertFalse(database.validateUser(list.get(4).getUsername(), list.get(5).getPassword()));
        assertFalse(database.validateUser(list.get(6).getUsername(), list.get(7).getPassword()));
        assertFalse(database.validateUser(list.get(7).getUsername(), list.get(8).getPassword()));
        assertFalse(database.validateUser(list.get(8).getUsername(), list.get(9).getPassword()));
        assertFalse(database.validateUser(list.get(9).getUsername(), list.get(0).getPassword()));
        assertFalse(database.validateUser("Yuli", "Yuli=best!"));
        assertFalse(database.validateUser("An", "An=worst!"));
        assertFalse(database.validateUser("James", "James=;>"));
        assertFalse(database.validateUser("Anadi", "Anadi=;)"));
        assertFalse(database.validateUser("Annette", "Annette=^-^"));
    }
    @Test
    public void validateUserTestEmptyString() {
        setUserList(database, list);
        assertFalse(database.validateUser("", ""));
    }
    @Test
    public void testUserSearchHandler() {
        users = new LinkedList<>();
        users.add(0, new User("Jaggia", "user1", "anadi2231", true, "anadi.jaggia@gmail.com"));
        users.add(1, new User("An", "user2", "pass1234", false, "an.young@gmail.com"));
        users.add(2, new User("Yuli", "user3", "word5678", false, "yuli917@gmail.com"));
        assertEquals(users.get(0), UserSearchHandler.findUserByUsername(users, "user1"));
        assertEquals(users.get(1), UserSearchHandler.findUserByEmail(users, "an.young@gmail.com"));
        assertNull(UserSearchHandler.findUserByUsername(users, "alskdfjlskdjf"));
        assertNull(UserSearchHandler.findUserByEmail(users, "alskdfjlskdjf"));
    }


}