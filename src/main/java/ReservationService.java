import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    public boolean reserveBook(User user, int bookId, String date) {
        user = getRegisteredUser(user);
        if(user == null)
            throw new SecurityException("User with that data is not registered!");
        if(bookId >= books.size())
            throw new ArrayIndexOutOfBoundsException("Book with ID: " + bookId + " does not exist!");
        Date realDate = parseDate(date);
        if(realDate == null)
            throw new IllegalArgumentException("Date format " + dateFormat + " is required!");

        Book book = books.get(bookId);

        String reservationInfo = getInfo(users.indexOf(user), bookId, date);
        ReservedBook rb = new ReservedBook();

        if(bookAlreadyReserved(rb))
            return false;
        else {
            user.getReservedBooks().put(reservationInfo, rb);
            try {
                rb.saveToFile("src/main/resources/");
            } catch (FileNotFoundException f) { }
            }
            return true;
        }

    private String getInfo(int userId, int bookId, String date) {
    }

    private boolean bookAlreadyReserved(ReservedBook rb) {
    }

    private Date parseDate(String date){
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date dateObj;
        try {
            dateObj = sdf.parse(date);
        } catch(ParseException e) {
            dateObj = null;
        }
        return dateObj;
    }

    public String booksToString() {
        String tmp = "";
        for(int i = 0; i < books.size(); i++) {
            tmp += "[ID: " + i + "] " + books.get(i).toString() + "\n";
        }
        return tmp;
    }
}
