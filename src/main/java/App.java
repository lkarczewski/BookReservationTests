import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws FileNotFoundException {

        User user = null;
        ReservationService rs = new ReservationService();
        rs.dbPath = "src/main/db.txt";

        int errors = rs.loadBooksFromFile();
        System.out.println("Database loaded with" + errors + "errors.");

        String option, login, password;
        boolean quit = false;
        Scanner scanner = new Scanner(System.in);

        while(!quit) {
            System.out.println("\n=|LIBRARY SERVICE|=\n");
            System.out.println("1.Log in\n2.Register\n3.Quit\n");
            System.out.print("INPUT: ");
            login = scanner.next();
            switch(login.toUpperCase()) {
                case "1":
                    System.out.print("Enter your nickname: ");
                    login = scanner.next();
                    System.out.print("Enter your password: "); //Console.readPassword(); -> doesn't work in IDE
                    password = scanner.next();
                    user = rs.logIn(login, password);
                    if(user != null)
                        System.out.println("MESSAGE: Successfully logged in as '" + user.getLogin() + "'!");
                    else
                        System.out.println("MESSAGE: Failed to log in! Please make " +
                                "sure you've entered valid information!");
                    break;

                case "2":
                    System.out.print("Enter your nickname: ");
                    login = scanner.next();
                    System.out.print("Enter your password: ");
                    password = scanner.next(); //Console.readPassword(); -> doesn't work in IDE
                    try {
                        if(rs.addUser(login, password))
                            System.out.println("MESSAGE: Successfuly registered new user '" + login +"'!");
                        else
                            System.out.println("MESSAGE: Failed to register new user! Username with that " +
                                    "nickname already exists!");
                    } catch (Exception e) { System.out.println("ERROR: Something went terribly wrong! " +
                            "Please try again and beware of your input!"); }
                    break;

                case "3":
                    quit = true;
                    break;
                default:
                    System.out.println("ERROR: Unknown option!");
                    break;
            }
            if(user != null) {
                System.out.println("\n=|LIBRARY SERVICE|=" + "\nLOGGED AS: '" + user.getLogin() + "'\n");
                System.out.println("1.Books\n2.Your books\n3.Reserve a book\n4.Log out\n5.Quit\n");
                System.out.print("INPUT: ");
                option = scanner.next();
                switch(option.toUpperCase()) {
                    case "1":
                        System.out.println("Books:\n" + rs.booksToString());
                        break;

                    case "2":
                        System.out.println("YOUR BOOKED RESTAURANTS:");
                        ArrayList<ReservedBook> rb = user.getListOfReservedBooks();
                        if(rb.size() == 0)
                            System.out.println("-");
                        else
                            for(int i = 0; i < rb.size(); i++)
                                System.out.println(rb.get(i));
                        break;

                    case "3":
                        System.out.println("BOOKS:\n" + rs.booksToString());
                        try {
                            System.out.print("Enter book name(check list above): ");
                            String bookName = scanner.next();
                            System.out.print("Enter date(dd.MM.yyyy): ");
                            String date = scanner.next();
                            if(rs.reserveBook(user, bookName, date))
                                System.out.println("MESSAGE: Book succesfully reserved " +
                                        rs.getBooks().get(id).getName() +
                                        " on: " + date);
                            else
                                System.out.println("MESSAGE: Failed to reserve a book! Please make sure it is available."
                                        + "If it's not on the list then it is reserved by other user.");
                        } catch(Exception e) { System.out.println("ERROR: Something went terribly wrong! Please try " +
                                "again and beware of your input!"); }
                        break;

                    case "4":
                        System.out.println("MESSAGE: User '" + user.getLogin() + "' logged out.");
                        user = null;
                        break;

                    case "Q":
                        quit = true;
                        break;

                    default:
                        System.out.println("ERROR: Unknown option!");
                        break;
                }
            }
        }
        scanner.close();
        System.out.println("Goodbye!");
    }
}
