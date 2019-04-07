import java.util.Date;

public class ReservedBook {

    private String id;
    private User user;
    private Book book;
    private Date dateOfReservation;
    private int hour;
    private String dateFormat = "dd.MM.yyyy";

    public ReservedBook(String id, User user, Book book, Date dateOfReservation, int hour) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.dateOfReservation = dateOfReservation;
        this.hour = hour;
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

    public int getHour() {
        return hour;
    }

    public boolean validateReservation(String id, User user, Book book, Date dateOfReservation, int hour) {
        return true;
    }
}
