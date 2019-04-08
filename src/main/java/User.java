import java.util.ArrayList;
import java.util.Hashtable;

public class User {

    private String login;
    private String password;
    private Hashtable<String, ReservedBook> reservedBooks;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        reservedBooks = new Hashtable<String, ReservedBook>();
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Hashtable<String, ReservedBook> getReservedBooks() {
        return reservedBooks;
    }

    public ArrayList<ReservedBook> getListOfReservedBooks() {
        return new ArrayList<ReservedBook>(reservedBooks.values());
    }

    public static boolean validateUser(String login, String password) {
        if(login == null || login.length() == 0 || login.length() > 20 || password == null || password.length() == 0 || password.length() < 4) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "'" + login + "'";
    }
}
