import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    static User validUser;
    static User invalidUser;
    static String validLogin = "lkarczewski";
    static String validPassword = "haslo123";
    static String invalidLogin = "";
    static String invalidPassword = "ha";

    @BeforeAll
    public static void setUp() {
        validUser = new User(validLogin, validPassword);
        invalidUser = new User(invalidLogin, invalidPassword);
    }

    //hamcrest
    @Test
    @DisplayName("validateUser() should return true with valid arguments")
    void userValidationTestTrueHamcrest() {
        assertThat(true, is(validUser.validateUser(validUser.getLogin(), validUser.getPassword())));
    }

    @Test
    @DisplayName("validateUser() should return false with invalid arguments")
    void userValidationTestFalseHamcrest() {
        assertThat(false, is(invalidUser.validateUser(invalidUser.getLogin(), invalidUser.getPassword())));
    }

    @Test
    @DisplayName("toString() should return login in string format")
    void userToStringTestHamcrest() {
        assertThat("'lkarczewski'", is(validUser.toString()));
    }

    //assertJ
    @Test
    @DisplayName("validateUser() should return true with valid arguments")
    void userValidationTestTrueAssertJ() {
        assertThat(validUser.validateUser(validUser.getLogin(), validUser.getPassword())).isTrue();
    }

    @Test
    @DisplayName("validateUser() should return false with invalid arguments")
    void userValidationTestFalseAssertJ() {
        assertThat(invalidUser.validateUser(invalidUser.getLogin(), invalidUser.getPassword())).isFalse();
    }

    @Test
    @DisplayName("toString() should return login in string format")
    void userToStringTestAssertJ() {
        assertThat(validUser.toString()).isEqualTo("'lkarczewski'");
    }
}
