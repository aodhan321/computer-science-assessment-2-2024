package part01;

public class LibraryBook extends Book implements Lendable {
    private static int nextId = 1; // Static counter for unique book IDs
    private int id = nextId++; // Assigns a unique ID to each book
    private BookStatus status = BookStatus.AVAILABLE; // Default status is available
    private String image; // Stores the image filename
    private int loanCount = 0; // Number of times the book has been borrowed

    /*
     * Constructor for LibraryBook, initialising all book attributes.
     * Calls the superclass (Book) constructor and sets the image.
     */
    public LibraryBook(String title, String author, String isbn, BookType type, int edition, String summary, double price, String image) {
        super(title, author, isbn, type, edition, summary, price);
        this.image = image;
    }

    /*
     * Returns the current status of the book (AVAILABLE, ON_LOAN, WITHDRAWN).
     */
    public BookStatus getStatus() {
        return status;
    }
    
    public String getImage() {
    	return image;
    }

    /* 
     * Updates the status of the book.
     */
    public void setStatus(BookStatus status) {
        this.status = status;
    }

    /* 
     * Returns the total number of times the book has been borrowed.
     */
    public int getLoanCount() {
        return loanCount;
    }

    /* 
     * Returns the unique ID of the book.
     */
    public int getId() {
        return id;
    }

    /* 
     * Marks the book as borrowed if it's available.
     * Increases the loan count each time the book is borrowed.
     */
    public void checkOut() {
        if (status == BookStatus.AVAILABLE) {
            status = BookStatus.ON_LOAN;
            loanCount++;
        } else {
            throw new IllegalStateException("Book is not available to borrow.");
        }
    }

    /* 
     * Marks the book as returned if it was previously borrowed.
     */
    public void checkIn() {
        if (status == BookStatus.ON_LOAN) {
            status = BookStatus.AVAILABLE;
        } else {
            throw new IllegalStateException("Book is not currently on loan.");
        }
    }

    /*
     * Returns a formatted string representing the book's details.
     */
    public String toString() {
        return super.toString() + String.format(" | ID: %d | Status: %s | Loan count: %d", id, status, loanCount);
    }
}
