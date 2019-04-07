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

    public static boolean validateBook(String name, String author, String genre, String description) {
        if(name == null || name == "" || name.length() > 50 || author == null || author == "" || author.length() > 30
        || genre == null || genre == "" || genre.length() > 20 || description == null || description == ""
        || description.length() > 100) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "----------\n" + "'" + name + "'\n" + "'" + author + "'\n" + "'" + genre + "'\n" +
                "'" + description + "'\n" + "----------\n";
    }
}
