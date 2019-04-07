import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReservationServiceTest {

    static String login, password, name, author, genre, description, bookId;
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
    
}
