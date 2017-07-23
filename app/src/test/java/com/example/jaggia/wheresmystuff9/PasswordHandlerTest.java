package com.example.jaggia.wheresmystuff9;
import com.example.jaggia.wheresmystuff9.model.user_system.PasswordHandler;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Yuli
 */

public class PasswordHandlerTest {

    @Test
    public void validatePasswordMatchNull() {
        assertFalse("incorrect boolean when password1 is null",
                PasswordHandler.validatePasswordMatch(null, "123456"));
        assertFalse("incorrect boolean when password2 is null",
                PasswordHandler.validatePasswordMatch("123456", null));
        assertFalse("incorrect boolean when both password are null",
                PasswordHandler.validatePasswordMatch(null, null));
    }

    @Test
    public void validatePasswordMatchTrue() {
        assertTrue("return incorrect boolean when password match",
                PasswordHandler.validatePasswordMatch("yuli=best", "yuli=best"));
        assertTrue("return incorrect boolean when password match",
                PasswordHandler.validatePasswordMatch("        ", "        "));
        assertTrue("return incorrect boolean when password match",
                PasswordHandler.validatePasswordMatch("1234567", "1234567"));
        assertTrue("return incorrect boolean when password match",
                PasswordHandler.validatePasswordMatch("password", "password"));
        assertTrue("return incorrect boolean when password match",
                PasswordHandler.validatePasswordMatch("runningoutofideas", "runningoutofideas"));
    }

    @Test
    public void validatePasswordMatchFasle() {
        assertFalse("return incorrect boolean when password1 is not legal",
                PasswordHandler.validatePasswordMatch("", "1234567"));
        assertFalse("return incorrect boolean when password1 is not legal",
                PasswordHandler.validatePasswordMatch(" ", "1234567"));
        assertFalse("return incorrect boolean when password1 is not legal",
                PasswordHandler.validatePasswordMatch("  ", "1234567"));
        assertFalse("return incorrect boolean when password1 is not legal",
                PasswordHandler.validatePasswordMatch("   ", "1234567"));
        assertFalse("return incorrect boolean when password1 is not legal",
                PasswordHandler.validatePasswordMatch("      ", "1234567"));
        assertFalse("return incorrect boolean when password2 is not legal",
                PasswordHandler.validatePasswordMatch("1234567", " "));
        assertFalse("return incorrect boolean when password2 is not legal",
                PasswordHandler.validatePasswordMatch("1234567", "  "));
        assertFalse("return incorrect boolean when password2 is not legal",
                PasswordHandler.validatePasswordMatch("1234567", "   "));
        assertFalse("return incorrect boolean when password2 is not legal",
                PasswordHandler.validatePasswordMatch("1234567", "    "));
        assertFalse("return incorrect boolean when password2 is not legal",
                PasswordHandler.validatePasswordMatch("1234567", "      "));
        assertFalse("return incorrect boolean when both password is are legal",
                PasswordHandler.validatePasswordMatch("123", "123"));
        assertFalse("return incorrect boolean when password do not match",
                PasswordHandler.validatePasswordMatch("1234567", "12345678"));
        assertFalse("return incorrect boolean when password do not match",
                PasswordHandler.validatePasswordMatch("12345678", "1234567"));
        assertFalse("return incorrect boolean when password do not match",
                PasswordHandler.validatePasswordMatch("yuli=best", "yuli=bestest"));
        assertFalse("return incorrect boolean when password do not match",
                PasswordHandler.validatePasswordMatch("yuli===", "best of best"));
    }

    @Test
    public void validatePasswordNull() {
        assertFalse("return incorrect boolean when password is null",
                PasswordHandler.validatePassword(null));
    }

    @Test
    public void validatePasswordTrue() {
        assertTrue("return incorrect boolean when password is legal",
                PasswordHandler.validatePassword("yuli=best"));
        assertTrue("return incorrect boolean when password is legal",
                PasswordHandler.validatePassword("       "));
        assertTrue("return incorrect boolean when password is legal",
                PasswordHandler.validatePassword("yuli!!!!"));
        assertTrue("return incorrect boolean when password is legal",
                PasswordHandler.validatePassword("issssssss"));
        assertTrue("return incorrect boolean when password is legal",
                PasswordHandler.validatePassword("bessssstttt"));
    }

    @Test
    public void validatePasswordFalse() {
        assertFalse("return incorrect boolean when password is not legal",
                PasswordHandler.validatePassword(" "));
        assertFalse("return incorrect boolean when password is not legal",
                PasswordHandler.validatePassword("  "));
        assertFalse("return incorrect boolean when password is not legal",
                PasswordHandler.validatePassword("   "));
        assertFalse("return incorrect boolean when password is not legal",
                PasswordHandler.validatePassword("    "));
        assertFalse("return incorrect boolean when password is not legal",
                PasswordHandler.validatePassword("      "));
        assertFalse("return incorrect boolean when password is not legal",
                PasswordHandler.validatePassword("      "));
    }

}
