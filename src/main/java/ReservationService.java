import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReservationService {

    public String dbPath = "src/main/resources/db.txt";
    private ArrayList<User> users;
    private ArrayList<Book> books;
    private String dateFormat = "dd.MM.yyyy";

    public ReservationService() {
        users = new ArrayList<User>();
        books = new ArrayList<Book>();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void loadBooks(ArrayList<Book> books) {
        if(books == null || books.isEmpty()) {
            throw new NullPointerException("Array list with books is empty or null!");
        }
        this.books = books;
    }

    public int loadBooksFromFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(dbPath));
        ArrayList<Book> booksFromFile = new ArrayList<Book>();
        String line[], name, author, genre, description;
        int errors = 0;

        while(scanner.hasNextLine()) {
            try {
                line = scanner.nextLine().split(";");
                name = line[0];
                author = line[1];
                genre = line[2];
                description = line[3];

                if(!Book.validateBook(name,author,genre,description)) {
                    continue;
                }
                booksFromFile.add(new Book(author,name,genre,description));
            } catch (Exception e) {
                errors++;
                continue;
            }
        }
        scanner.close();
        loadBooks(booksFromFile);
        return errors;
    }

    public boolean addUser(String login, String password) {
        if(!User.validateUser(login, password))
            throw new IllegalArgumentException("User data is not valid!");
        User user = new User(login, password);
        if(users.contains(user))
            return false;
        else {
            users.add(user);
            return true;
        }
    }

    public User logIn(String login, String password) {
        return users.stream().filter(user -> user.getLogin().equals(login) && user.getPassword().equals(password)).findFirst().orElse(null);
    }

    private User getRegisteredUser(User user) {
        if(user == null || !users.contains(user))
            return null;
        User registered = users.get(users.indexOf(user));
        if(!registered.getPassword().equals(user.getPassword()))
            return null;
        return registered;
    }
}
