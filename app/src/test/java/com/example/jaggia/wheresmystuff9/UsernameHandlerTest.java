package com.example.jaggia.wheresmystuff9;
import com.example.jaggia.wheresmystuff9.model.user_system.UsernameHandler;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Yuli
 */

public class UsernameHandlerTest {

    @Test
    public void validateLegalUsernameNull() {
        assertFalse("incorrect boolean when username is null",
                UsernameHandler.validateLegalUsername(null));
    }

    @Test
    public void validateLegalUsernameTrue() {
        assertTrue("return incorrect boolean when username is legal",
                UsernameHandler.validateLegalUsername("    "));
        assertTrue("return incorrect boolean when username is legal",
                UsernameHandler.validateLegalUsername("yuli"));
        assertTrue("return incorrect boolean when username is legal",
                UsernameHandler.validateLegalUsername("AnnT"));
        assertTrue("return incorrect boolean when username is legal",
                UsernameHandler.validateLegalUsername("James"));
        assertTrue("return incorrect boolean when username is legal",
                UsernameHandler.validateLegalUsername("Anadi"));
        assertTrue("return incorrect boolean when username is legal",
                UsernameHandler.validateLegalUsername("Annette"));
        assertTrue("return incorrect boolean when username is legal",
                UsernameHandler.validateLegalUsername("...."));
    }

    @Test
    public void validateLegalUsernameFasle() {
        assertFalse("return incorrect boolean when username is not legal",
                UsernameHandler.validateLegalUsername(""));
        assertFalse("return incorrect boolean when username is not legal",
                UsernameHandler.validateLegalUsername(" "));
        assertFalse("return incorrect boolean when username is not legal",
                UsernameHandler.validateLegalUsername("  "));
        assertFalse("return incorrect boolean when username is not legal",
                UsernameHandler.validateLegalUsername("   "));
        assertFalse("return incorrect boolean when username is not legal",
                UsernameHandler.validateLegalUsername("y"));
        assertFalse("return incorrect boolean when username is not legal",
                UsernameHandler.validateLegalUsername("yu"));
        assertFalse("return incorrect boolean when username is not legal",
                UsernameHandler.validateLegalUsername("yul"));

    }

    @Test
    public void validatePersonNameNull() {
        assertFalse("return incorrect boolean when name is null",
                UsernameHandler.validatePersonName(null));
    }

    @Test
    public void validatePersonNameTrue() {
        assertTrue("return incorrect boolean when name is legal",
                UsernameHandler.validatePersonName(" "));
        assertTrue("return incorrect boolean when name is legal",
                UsernameHandler.validatePersonName("  "));
        assertTrue("return incorrect boolean when name is legal",
                UsernameHandler.validatePersonName("   "));
        assertTrue("return incorrect boolean when name is legal",
                UsernameHandler.validatePersonName("y"));
        assertTrue("return incorrect boolean when name is legal",
                UsernameHandler.validatePersonName("yu"));
        assertTrue("return incorrect boolean when name is legal",
                UsernameHandler.validatePersonName("yul"));
        assertTrue("return incorrect boolean when name is legal",
                UsernameHandler.validatePersonName("yuli"));
    }

    @Test
    public void validatePersonNameFalse() {
        assertFalse("return incorrect boolean when name is not legal",
                UsernameHandler.validatePersonName(""));

    }

}
