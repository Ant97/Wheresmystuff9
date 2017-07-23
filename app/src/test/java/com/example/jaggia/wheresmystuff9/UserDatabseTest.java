package com.example.jaggia.wheresmystuff9;
import com.example.jaggia.wheresmystuff9.model.user_system.User;
import com.example.jaggia.wheresmystuff9.model.user_system.UserDatabase;
import java.util.List;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import org.junit.Test;
import java.lang.reflect.Field;
import java.util.LinkedList;
/**
 * Created by Yuli
 */

public class UserDatabseTest {
    private UserDatabase database;
    private List<User> list;


    //--------------------------helper methods-------------------------------------

    /**
     * this method populate a list of users
     */
    private void populateUser() {
        database = new UserDatabase();
        list = new LinkedList<>();
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
    }

    /**
     * this method sets userdatabase's users list to the user list passed in parameter
     * @throws RuntimeException if fields are not set successfully
     * @param database database to set
     * @param users the user list used to set database's list
     */
    private void setUserList(UserDatabase database, List<User> users) {
        try {
            Field field = UserDatabase.class.getDeclaredField("_users");
            field.setAccessible(true);
            field.set(database, users);

            Field field1 = UserDatabase.class.getDeclaredField("_numUsers");
            field1.setAccessible(true);
            field1.set(database, users.size());

        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method check if the actual list of users match the expected user list
     * @param expect expected list of users
     * @param actual actual list of users
     */
    private void assertUserListEqual(List<User> expect, List<User> actual) {
        assertEquals("incorrect length of user list", expect.size(), actual.size());
        for (int i = 0; i < expect.size(); i++) {
            assertEquals(expect.get(i), actual.get(i));
        }
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


    //----------------------validateUserTests--------------------------------------

    @Test
    public void validateUserTestNull() {
        populateUser();
        setUserList(database, list);
        assertFalse(database.validateUser(list.get(0).getUsername(), null));
        assertFalse(database.validateUser(null, list.get(0).getPassword()));
    }

    @Test
    public void validateUserTestEmptyString() {
        populateUser();
        setUserList(database, list);
        assertFalse(database.validateUser(list.get(0).getUsername(), ""));
        assertFalse(database.validateUser("", list.get(0).getPassword()));
    }

    @Test
    public void validateUserTestTrue() {
        populateUser();
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
        populateUser();
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



    //----------------------registerUserTests--------------------------------------

    @Test
    public void registerNewUserNullCheck() {
        populateUser();
        setUserList(database, list);
        assertFalse(database.registerNewUser(null));
    }

    @Test
    public void registerNewUserEmptyList() {
        database = new UserDatabase();
        list = new LinkedList<>();
        setUserList(database, list);
        User newUser1 = new User("new", "newuser1", "password");
        assertTrue("does not return the correct boolean", database.registerNewUser(newUser1));
        list.add(newUser1);
        assertUserListEqual(list, database.getUsers());
    }


    @Test
    public void testRegisterNewUserTrue() {
        populateUser();
        setUserList(database, list);
        User newUser1 = new User("new", "newuser1", "password1");
        User newUser2 = new User("new", "newuser2", "password2");
        assertTrue("does not return the correct boolean", database.registerNewUser(newUser1));
        list.add(newUser1);
        assertTrue("does not return the correct boolean", database.registerNewUser(newUser2));
        list.add(newUser2);
        assertUserListEqual(list, database.getUsers());

        User newUser3 = new User("new", "newuser3", "password3");
        assertTrue("does not return the correct boolean", database.registerNewUser(newUser3));
        list.add(newUser3);
        assertUserListEqual(list, database.getUsers());

        User newUser4 = new User("new", "newuser4", "password4");
        assertTrue("does not return the correct boolean", database.registerNewUser(newUser4));
        list.add(newUser4);
        assertUserListEqual(list, database.getUsers());

        User newUser5 = new User("new", "newuser5", "password5");
        User newUser6 = new User("new", "newuser6", "password");
        assertTrue("does not return the correct boolean", database.registerNewUser(newUser5));
        assertTrue("does not return the correct boolean", database.registerNewUser(newUser6));
        list.add(newUser5);
        list.add(newUser6);
        assertUserListEqual(list, database.getUsers());

    }

    @Test
    public void testRegisterNewUserFalse() {
        populateUser();
        setUserList(database, list);
        assertUserListEqual(list, database.getUsers());
        assertFalse("does not return false when user already exist in the databse",
                database.registerNewUser(list.get(0)));
        assertFalse("does not return false when user already exist in the databse",
                database.registerNewUser(list.get(1)));
        assertFalse("does not return false when user already exist in the databse",
                database.registerNewUser(list.get(2)));
        assertFalse("does not return false when user already exist in the databse",
                database.registerNewUser(list.get(3)));
        assertFalse("does not return false when user already exist in the databse",
                database.registerNewUser(list.get(4)));
        assertFalse("does not return false when user already exist in the databse",
                database.registerNewUser(list.get(5)));
        assertUserListEqual(list, database.getUsers());

    }
}
