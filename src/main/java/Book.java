public class Book {

    private String name;
    private String author;
    private String genre;
    private String description;

    public Book(String name, String author, String genre, String description) {
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getDescription() {
        return description;
    }

    public boolean validateBook(String name, String author, String genre, String description) {
        return true;
    }
}
