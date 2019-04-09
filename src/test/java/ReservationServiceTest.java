import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;

import static junit.framework.Assert.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;



public class ReservationServiceTest {

    static String login, password, name, author, genre, description, bookId;
    static Date dateOfReservation;
    static String dateFormat = "dd.MM.yyyy";
    static User user;
    static Book book;
    static ReservationService reservationService;
    static ArrayList<Book> database;

    @BeforeAll
    public static void setUp() {
        login = "lkarczewski";
        password = "haslo123";
        user = new User(login, password);

        name = "The Little Prince";
        author = "Antoine de Saint-Exupery";
        genre = "novel";
        description = "One of most famous novels of all time";
        bookId = "0001";
        book = new Book(name, author, genre, description);
        dateOfReservation = new Date();

        reservationService = new ReservationService();
        database = new ArrayList<Book>();
        database.add(book);
    }

    @ParameterizedTest
    @DisplayName("loadBooksFromFile() should add book to the list")
    @CsvFileSource(resources = "/validBookData.csv", numLinesToSkip = 1)
    void loadBooks_FromFile_WithCorrectData(String name, String author, String genre, String description) {
        ArrayList<Book> books = new ArrayList<Book>();
        Book b = new Book(name, author, genre, description);
        books.add(b);
        reservationService.loadBooks(books);
        assertThat(reservationService.getBooks().contains(b));
    }

    @Test
    void loadBooks_nullList_throwsNullPointer() {
        assertThrows(NullPointerException.class, () -> {
            reservationService.loadBooks(null);
        });
    }

    @Test
    void addUser_invalidData_False() {
        assertThrows(IllegalArgumentException.class, () -> {
            String invalidLogin = "asdffdsgjjhsghfahdssgdgs";
            String invalidPassword = "df";
            reservationService.addUser(invalidLogin, invalidPassword);
        });
    }

    @Test
    void logIn_UserWithSameNicknameAndPasswordExistsOnTheList_ProperUser() {
        reservationService.addUser(login, password);
        User user = reservationService.logIn(login, password);
        Assertions.assertThat(user).hasFieldOrPropertyWithValue("login", login)
                .hasFieldOrPropertyWithValue("password", password);
    }

    @Test
    void logIn_UserExistsButPasswordIsNotValid_Null() {
        reservationService.addUser(login, password);
        User u = reservationService.logIn(login, password + "different");
        assertNull(u);
    }

    @Test
    void loadBooksFromFile_SomeDataIsWrong_ReturnCountOfErrors() throws FileNotFoundException {
        reservationService.dbPath = "src/test/resources/dbTest.txt";
        int errors = 4;

        assertEquals(reservationService.loadBooksFromFile(), errors);
    }

    @Test
    void reservedBooksToString_Correct() {
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(book);
        reservationService.loadBooks(books);

        assertThat(reservationService.booksToString()).contains(name).contains(author).
                contains(genre).contains(description);
    }

    @Test
    void reservedBookToString_Wrong() {
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(book);
        reservationService.loadBooks(books);
        String wrong_string = "'Odyssey'\n'Homer'\n'Ancient'\n'Story about long return.'";

        assertFalse(reservationService.booksToString().contains(wrong_string));
    }

    @Test
    public void getInfo_GivenProperData_SetsUpCorrectString(){
        String date = "02.02.2020".replaceAll("\\.", "");;
        int userId = 2;
        int bookId = 34;
        String information = reservationService.getInfo(userId, bookId, date);
        Assertions.assertThat(information).startsWith(userId + "").endsWith(date).contains("" + bookId);
    }
}
