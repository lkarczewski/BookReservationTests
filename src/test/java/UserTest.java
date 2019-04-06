import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    static User validUser;
    static User badUser;
    static String validLogin = "lkarczewski";
    static String validPassword = "haslo123";
    static String badLogin = "";
    static String badPassword = "ha";

    @BeforeAll
    public static void setUp() {
        validUser = new User(validLogin, validPassword);
        badUser = new User(badLogin, badPassword);
    }

    //hamcrest
    @Test
    @DisplayName("validateUser() should return true with valid arguments")
    void userValidationTestHamcrest() {
        assertThat(true, is(validUser.validateUser(validUser.getLogin(), validUser.getPassword())));
    }

    //assertJ
    @Test
    @DisplayName("validateUser() should return true with valid arguments")
    void userValidationTestAssertJ() {
        assertThat(validUser.validateUser(validUser.getLogin(), validUser.getPassword())).isTrue();
    }
}
