package part01;

/*
 * Represents a book in the library.
 * This class defines the attributes and validation rules for a book.
 */
public class Book {
    private String title;    // Book title
    private String author;   // Author of the book
    private String isbn;     // 10-digit ISBN number
    private BookType type;   // Category/type of the book
    private int edition;     // Edition number (must be ≥ 1)
    private String summary;  // Short description of the book
    private double price;    // Price of the book (must be > 0)

    /*
     * Constructs a new book
     * and validates input values
     */
    public Book(String title, String author, String isbn, BookType type, int edition, String summary, double price) {
        if (title.length() < 5 || title.length() > 40) {
            throw new IllegalArgumentException("Title must be between 5 and 40 characters.");
        }
        if (author.length() < 5 || author.length() > 40) {
            throw new IllegalArgumentException("Author must be between 5 and 40 characters.");
        }
        if (!isbn.matches("\\d{10}")) {
            throw new IllegalArgumentException("ISBN must be exactly 10 digits.");
        }
        if (edition < 1) {
            throw new IllegalArgumentException("Edition must be at least 1.");
        }
        if (summary.length() < 20 || summary.length() > 150) {
            throw new IllegalArgumentException("Summary must be between 20 and 150 characters.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price must be greater than £0.00.");
        }
        
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.type = type;
        this.edition = edition;
        this.summary = summary;
        this.price = price;
    }

    /* 
     * Returns the title of the book.
     */
    public String getTitle() {
        return title;
    }

    /* 
     * Returns the author of the book.
     */
    public String getAuthor() {
        return author;
    }

    /* 
     * Returns the ISBN of the book.
     */
    public String getIsbn() {
        return isbn;
    }

    /* 
     * Returns a formatted string representation of the book's details.
     */
    public String toString() {
        return String.format("Title: %s | Author: %s | ISBN: %s | Type: %s | Edition: %d | Summary: %s | Price: £%.2f",
                title, author, isbn, type, edition, summary, price);
    }
}
