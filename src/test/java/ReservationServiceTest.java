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
        assertTrue(reservationService.getBooks().contains(b));
    }

    @Test
    void bookRestaurant_SameUserAlreadyReservedThatBook_False() {
        User user = reservationService.logIn(name, password);

        ReservedBook rb = new ReservedBook("001", user, book, new Date());
        assertFalse(reservationService.bookAlreadyReserved(rb));
    }

    @Test
    public void bookRestaurant_SameUserAlreadyBookedThatRestaurant_NothingChanges() {
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(book);
        reservationService.loadBooks(books);
        reservationService.addUser(name, password);
        User user = reservationService.logIn(name, password);
        String date = "20.02.2019";
        int bookId = books.size()-1;

        reservationService.reserveBook(user, bookId, date);
        reservationService.reserveBook(user, bookId, date);
        assertEquals(user.getReservedBooks().size(), 1);
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
        User user = reservationService.logIn(login, "pass");
        assertEquals(user, null);
    }

    @Test
    void loadBooksFromFile_SomeDataIsWrong_ReturnCountOfErrors() throws FileNotFoundException {
        reservationService.dbPath = "src/test/resources/dbTest.txt";
        int errors = 4;

        assertEquals(reservationService.loadBooksFromFile(), errors);
    }

    @Test
    void reserveBook_CorrectlyReservedBooksWithAllValidData_AddsReservedBooksToUser(){
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(book);
        reservationService.loadBooks(books);
        reservationService.addUser(name, password);
        User user = reservationService.logIn(name, password);
        String date = "20.02.2019";
        int bookId = books.size()-1;

        reservationService.reserveBook(user, bookId, date);
        assertEquals(user.getReservedBooks().size(), 1);
    }

    @Test
    void reservedBook_SecurityException_UserNotLogged() {
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(book);
        reservationService.loadBooks(books);
        String date = "20.02.2019";
        int bookId = books.size()-1;

        assertThrows(SecurityException.class, () -> {
            reservationService.reserveBook(user, bookId, date);
        });
    }

    @Test
    void reservedBook_ArrayIndexOutOfBoundsException_BookDoesNotExist() {
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(book);
        reservationService.loadBooks(books);
        reservationService.addUser(name, password);
        User user = reservationService.logIn(name, password);
        String date = "20.02.2019";
        int bookId = 2;

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            reservationService.reserveBook(user, bookId, date);
        });
    }

    @Test
    void reservedBook_IllegalArgumentException_DateIsWrong() {
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(book);
        reservationService.loadBooks(books);
        reservationService.addUser(name, password);
        User user = reservationService.logIn(name, password);
        String date = "02/02/2002";
        int bookId = books.size()-1;

        assertThrows(IllegalArgumentException.class, () -> {
            reservationService.reserveBook(user, bookId, date);
        });
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
    void getInfo_GivenProperData_SetsUpCorrectString(){
        String date = "02.02.2020".replaceAll("\\.", "");;
        int userId = 2;
        int bookId = 34;
        String information = reservationService.getInfo(userId, bookId, date);
        Assertions.assertThat(information).startsWith(userId + "").endsWith(date).contains("" + bookId);
    }

    @Test
    void parseDate_Test_True() {
        assertThat(reservationService.parseDate("20.01.2019")).isNotNull();
    }

    @Test
    void parseDate_Test_False() {
        assertThat(reservationService.parseDate("asdgdfhghfsgd")).isNull();
    }
}