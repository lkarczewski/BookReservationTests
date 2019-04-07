import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BookTest {

    static Book validBook;
    static Book validBookFromFile;
    static Book invalidBookFromFile;
    static String validName = "Hamlet";
    static String validAuthor = "William Shakespeare";
    static String validGenre = "Drama";
    static String validDescription = "One of Shakespeare's finest.";

    @BeforeAll
    public static void setUp() {
        validBook = new Book(validName, validAuthor, validGenre, validDescription);
    }

    //hamcrest
    @Test
    @DisplayName("validateBook() should return true with valid arguments")
    void bookValidationTest_TrueHamcrest() {
        assertThat(true, is(validBook.validateBook(validBook.getName(), validBook.getAuthor(),
                validBook.getGenre(), validBook.getDescription())));
    }

    @ParameterizedTest
    @DisplayName("validateBook() should return true with valid arguments")
    @CsvFileSource(resources = "/validBookData.csv", numLinesToSkip = 1)
    void bookValidationFromFileTest_TrueHamcrest(String name, String author, String genre, String description) {
        validBookFromFile = new Book(author, name, genre, description);
        assertThat(true, is(validBookFromFile.validateBook(validBookFromFile.getName(),
                validBookFromFile.getAuthor(), validBookFromFile.getGenre(), validBookFromFile.getDescription())));
    }

    @ParameterizedTest
    @DisplayName("validateBook() should return false with invalid arguments")
    @CsvFileSource(resources = "/invalidBookData.csv", numLinesToSkip = 1)
    void bookValidationTest_FalseHamcrest(String name, String author, String genre, String description) {
        invalidBookFromFile = new Book(name, author, genre, description);
        assertThat(false, is(invalidBookFromFile.validateBook(invalidBookFromFile.getName(),
                invalidBookFromFile.getAuthor(), invalidBookFromFile.getGenre(), invalidBookFromFile.getDescription())));
    }

    //assertJ
    @Test
    @DisplayName("validateBook() should return true with valid arguments")
    void bookValidationTest_TrueAssertJ() {
        assertThat(validBook.validateBook(validBook.getName(), validBook.getAuthor(), validBook.getGenre(),
                validBook.getDescription())).isTrue();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/validBookData.csv", numLinesToSkip = 1)
    void bookValidationFromFileTest_TrueAssertJ(String name, String author, String genre, String description) {
        validBookFromFile = new Book(author, name, genre, description);
        assertThat(validBookFromFile.validateBook(validBookFromFile.getName(), validBookFromFile.getAuthor(),
                validBookFromFile.getGenre(), validBookFromFile.getDescription())).isTrue();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/invalidBookData.csv", numLinesToSkip = 1)
    void bookValidationFromFileTest_FalseAssertJ(String name, String author, String genre, String description) {
        invalidBookFromFile = new Book(author, name, genre, description);
        assertThat(invalidBookFromFile.validateBook(invalidBookFromFile.getName(), invalidBookFromFile.getAuthor(),
                invalidBookFromFile.getGenre(), invalidBookFromFile.getDescription())).isFalse();
    }

    @AfterAll
    public static void tearDown() {
        validBook = null;
        validBookFromFile = null;
        invalidBookFromFile = null;
        validName = null;
        validAuthor = null;
        validGenre = null;
        validDescription = null;
    }
}
