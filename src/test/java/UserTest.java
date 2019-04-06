import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;

public class UserTest {

    static User validUser;
    static User invalidUser1;
    static User invalidUser2;
    static User invalidUser3;
    static String validLogin = "lkarczewski";
    static String validPassword = "haslo123";
    static String invalidLogin1 = null;
    static String invalidPassword1 = null;
    static String invalidLogin2 = "";
    static String invalidPassword2 = "";
    static String invalidLogin3 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
    static String invalidPassword3 = "ha";

    @BeforeAll
    public static void setUp() {
        validUser = new User(validLogin, validPassword);
        invalidUser1 = new User(invalidLogin1, invalidPassword1);
        invalidUser2 = new User(invalidLogin2, invalidPassword2);
        invalidUser3 = new User(invalidLogin3, invalidPassword3);
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
        assertAll("Invalid users tests", () -> {
            assertThat(false, is(invalidUser1.validateUser(invalidUser1.getLogin(), invalidUser1.getPassword())));
            assertThat(false, is(invalidUser2.validateUser(invalidUser2.getLogin(), invalidUser2.getPassword())));
            assertThat(false, is(invalidUser3.validateUser(invalidUser3.getLogin(), invalidUser3.getPassword())));
        });
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
        assertAll("Invalid users tests:", () -> {
            assertThat(invalidUser1.validateUser(invalidUser1.getLogin(), invalidUser1.getPassword())).isFalse();
            assertThat(invalidUser2.validateUser(invalidUser2.getLogin(), invalidUser2.getPassword())).isFalse();
            assertThat(invalidUser3.validateUser(invalidUser3.getLogin(), invalidUser3.getPassword())).isFalse();
        });
    }

    @Test
    @DisplayName("toString() should return login in string format")
    void userToStringTestAssertJ() {
        assertThat(validUser.toString()).isEqualTo("'lkarczewski'");
    }
}
