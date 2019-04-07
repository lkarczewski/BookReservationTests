import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ReservedBookTest {

    static User user;
    static Book book;
    static ReservedBook reservedBook;
    static String id, login, password;
    static Date dateOfReservation;
    static int hour;
    static String dateFormat = "dd.MM.yyyy";

    @BeforeAll
    public static void setUp() {
        login = "lkarczewski";
        password = "haslo123";
        user = new User(login, password);
        book = new Book("Drach", "Szczepan Twardoch", "novel", "Some description.");
        id = "0001";
        dateOfReservation = new Date();
        hour = 13;
        reservedBook = new ReservedBook(id, user, book, dateOfReservation, hour);
    }

    //hamcrest
    @Test
    @DisplayName("validateReservation() should return true with right parameters")
    void reservationValidationTest_TrueHamcrest() {
        assertThat(true, is(reservedBook.validateReservation(id, user, book, dateOfReservation)));
    }

    @Test
    @DisplayName("saveToFile() saves reservation data to file with toString() method")
    void reservationSaveToFileTest_Hamcrest() throws IOException {

        reservedBook.saveToFile("src/test/resources");
        String actual = new BufferedReader(new FileReader("src/test/resources/reservation" + id + ".txt")).readLine();
        assertThat(reservedBook.toString(), is(actual));
    }

    //assertJ
    @Test
    @DisplayName("validateReservation() should return true with right parameters")
    void reservationValidationTest_TrueAssertJ() {
        assertThat(reservedBook.validateReservation(id, user, book, dateOfReservation)).isTrue();
    }
}
