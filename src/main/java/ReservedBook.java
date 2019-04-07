import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReservedBook {

    private String id;
    private User user;
    private Book book;
    private Date dateOfReservation;
    private String dateFormat = "dd.MM.yyyy";

    public ReservedBook(String id, User user, Book book, Date dateOfReservation) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.dateOfReservation = dateOfReservation;
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public Date getDateOfReservation() {
        return dateOfReservation;
    }


    public boolean validateReservation(String id, User user, Book book, Date dateOfReservation) {
        if(id.isEmpty() || user == null || book == null || dateOfReservation == null) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

        return "[" + id + "]" + book.getName() + " reserved by: " + user.getLogin() + " at " +
                sdf.format(dateOfReservation);
    }

    public void saveToFile(String path) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(path + "/reservation" + id + ".txt");
        pw.println(this.toString());
        pw.close();
    }
}
