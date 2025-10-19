package part01;

import java.util.ArrayList;

public class Library {
    private ArrayList<LibraryBook> books; // List of books in the library

    /* 
     * Constructor initialises the library with a predefined set of books.
     */
    public Library() {
        books = new ArrayList<>();
        books.add(new LibraryBook("Java Basics", "John Doe", "1234567890", BookType.NON_FICTION, 1, "Intro to Java programming", 10.99, "java.png"));
        books.add(new LibraryBook("Python 101", "Jane Smith", "0987654321", BookType.NON_FICTION, 2, "Learn Python from scratch", 12.99, "python.png"));
        books.add(new LibraryBook("History of AI", "Alan Turing", "1122334455", BookType.NON_FICTION, 1, "A journey through AI history", 15.49, "ai.png"));
        books.add(new LibraryBook("Data Science", "Andrew Ng", "2233445566", BookType.NON_FICTION, 1, "Data science and machine learning", 20.00, "data.png"));
        books.add(new LibraryBook("Moby-Dick", "Herman Melville", "3344556677", BookType.FICTION, 1, "1851 epic novel centered around a sailor", 18.75, "moby-dick.png"));
    }

    /* 
     * Allows a user to borrow a book by ID if it's available.
     * Returns true if the book was successfully borrowed, false otherwise.
     */
    public boolean borrowBook(int id) {
        if (books.get(id - 1).getStatus() == BookStatus.AVAILABLE) {
            books.get(id - 1).checkOut();
            return true;
        }
        return false;
    }

    /* 
     * Allows a user to return a book by ID if it was previously borrowed.
     * Returns true if the book was successfully returned, false otherwise.
     */
    public boolean returnBook(int id) {
        if (books.get(id - 1).getStatus() == BookStatus.ON_LOAN) {
            books.get(id - 1).checkIn();
            return true;
        }
        return false;
    }

    /* 
     * Returns a list of all books in the library.
     */
    public ArrayList<LibraryBook> list() {
        return books;
    }

    /* 
     * Returns a list of books that match a specific status.
     */
    public ArrayList<LibraryBook> list(BookStatus status) {
        ArrayList<LibraryBook> matchingBooks = new ArrayList<>();
        for (LibraryBook book : books) {
            if (book.getStatus() == status) {
                matchingBooks.add(book);
            }
        }
        return matchingBooks;
    }

    /*
     * Returns a list of books sorted by popularity (most borrowed first).
     * Implements a simple bubble sort algorithm for ordering.
     */
    public ArrayList<LibraryBook> mostPopular() {
        ArrayList<LibraryBook> sortedBooks = new ArrayList<>(books); // Copy of the book list
        int swaps;
        do {
            swaps = 0;
            for (int i = 0; i < books.size() - 1; i++) {
                if (sortedBooks.get(i).getLoanCount() < sortedBooks.get(i + 1).getLoanCount()) {
                    // Swap books if the next one is more borrowed
                    LibraryBook temp = sortedBooks.get(i);
                    sortedBooks.set(i, sortedBooks.get(i + 1));
                    sortedBooks.set(i + 1, temp);
                    swaps++;
                }
            }
        } while (swaps > 0);
        return sortedBooks;
    }

    /* 
     * Searches for a book by ID and returns it if found, otherwise returns null.
     */
    public LibraryBook search(int id) {
        for (LibraryBook book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    /* 
     * Removes a book from the library by marking it as "Withdrawn".
     * Returns true if the book was successfully removed, false otherwise.
     */
    public Boolean remove(int id) {
        LibraryBook book = search(id);
        if (book != null) {
            book.setStatus(BookStatus.WITHDRAWN);
            books.remove(book);
            return true;
        }
        return false;
    }

    /* 
     * Adds a new book to the library if it meets the validation requirements.
     * Returns a success message or an error message if the book cannot be added.
     */
    public String add(String title, String author, String isbn, BookType type, int edition, String summary, double price, String image) {
        try {
            LibraryBook newBook = new LibraryBook(title, author, isbn, type, edition, summary, price, image);
            books.add(newBook);
            return "Book added successfully!";
        } catch (IllegalArgumentException e) {
            return "Error adding book: " + e.getMessage();
        }
    }
}
