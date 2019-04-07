import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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

    @Test
    void bookValidationTest_TrueHamcrest() {
        assertThat(true, is(validBook.validateBook(validBook.getName(), validBook.getAuthor(), validBook.getGenre(), validDescription)));
    }

    @AfterAll
    public static void tearDown() {
        validBook = null;
        validBookFromFile = null;
        validName = null;
        validAuthor = null;
        validGenre = null;
        validDescription = null;
    }
}
