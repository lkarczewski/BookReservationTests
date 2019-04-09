import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class ReservedBookTest {

    static User user;
    static Book book;
    static ReservedBook reservedBook;
    static String id, login, password;
    static Date dateOfReservation;

    @BeforeAll
    public static void setUp() throws ParseException {
        login = "lkarczewski";
        password = "haslo123";
        user = new User(login, password);
        book = new Book("Drach", "Szczepan Twardoch", "novel", "Some description.");
        id = "0001";
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        dateOfReservation = sdf.parse("22.01.2019");
        reservedBook = new ReservedBook(id, user, book, dateOfReservation);
    }

    //hamcrest
    @Test
    @DisplayName("validateReservation() should return true with right parameters")
    void reservationValidationTest_TrueHamcrest() {
        assertThat(true, is(reservedBook.validateReservation(id, user, book, dateOfReservation)));
    }

    @Test
    @DisplayName("validateReservation() should return false with wrong parameters")
    void reservationValidationTest_FalseHamcrest() {
        assertThat(false, is(reservedBook.validateReservation("",null,null,null)));
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

    @Test
    void reservedBook_IdTest_True() {
        assertThat(id, is(reservedBook.getId()));
    }

    @Test
    void reservedBook_IdTest_False() {
        String wrong_id = "0022";
        assertThat(wrong_id, not(reservedBook.getId()));
    }

    @Test
    void reservedBook_UserTest_True() {
        assertThat(user, is(reservedBook.getUser()));
    }

    @Test
    void reservedBook_UserTest_False() {
        User wrong_user = new User("login", "password");
        assertThat(wrong_user, not(reservedBook.getUser()));
    }

    @Test
    void reservedBook_BookTest_True() {
        assertThat(book, is(reservedBook.getBook()));
    }

    @Test
    void reservedBook_BookTest_False() {
        Book wrong_book = new Book("title", "author", "genre", "desc");
        assertThat(wrong_book, not(reservedBook.getBook()));
    }

    @Test
    void reservedBook_DateTest_True() {
        assertThat(dateOfReservation, is(reservedBook.getDateOfReservation()));
    }

    @Test
    void reservedBook_DateTest_False() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date wrongDateOfReservation = sdf.parse("06.05.2019");
        assertThat(wrongDateOfReservation, not(reservedBook.getDateOfReservation()));
    }
}
