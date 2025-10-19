package part02;

import part01.*;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import console.Console;
import javax.swing.ImageIcon;
import java.awt.Image;

/*
  menu system using the Console class.
 */
public class QUBLibraryUpdated {
    private static Library library = new Library(); // creates library instance
    public static Console console = new Console(true);
    
    public static void main(String[] args) {
        int choice;

        // Set up console properties
        console.setSize(600, 600);
        console.setVisible(true);
        console.setFont(new Font("Consolas", Font.BOLD, 20));
        console.setColour(Color.RED);

        do {
            console.clear();
            displayImage("images/QUB-logo.png"); // Displays the library logo
            console.println("QUB Library System");
            console.println("1️. List all books");
            console.println("2️. List books by status");
            console.println("3️. Add a book");
            console.println("4️. Remove a book");
            console.println("5️. Borrow a book");
            console.println("6️. Return a book");
            console.println("7️. Display most popular books");
            console.println("8️. Exit");

            console.println("Enter your choice: ");
            choice = readInt(); // Reads user choice

            switch (choice) {
                case 1 -> listAllBooks();
                case 2 -> listBooksByStatus();
                case 3 -> addBook();
                case 4 -> removeBook();
                case 5 -> borrowBook();
                case 6 -> returnBook();
                case 7 -> displayMostBorrowed();
                case 8 -> System.exit(0); // Exits the program
                default -> console.println("Invalid choice. Enter a number between 1 and 8.");
            }
            pauseConsole();
        } while (choice != 8);
    }

    /*
     * Displays all books in the library.
     */
    private static void listAllBooks() {
        console.clear();
        console.println("All Books in Library:");
        ArrayList<LibraryBook> books = library.list();
        if (books.isEmpty()) {
            console.println("No books available.");
        } 
        else {
            for (LibraryBook book : books) {
                console.println(book.toString());
                displayImage(book.getImage());
                console.println();
            }
        }
    }

    /*
     * Displays books based on their status.
     */
    private static void listBooksByStatus() {
        console.clear();
        console.println("Select Status: 1️. AVAILABLE  2️. ON_LOAN  3️. WITHDRAWN");
        console.println("Enter status: ");
        int statusChoice = readInt();

        BookStatus status = switch (statusChoice) {
            case 1 -> BookStatus.AVAILABLE;
            case 2 -> BookStatus.ON_LOAN;
            case 3 -> BookStatus.WITHDRAWN;
            default -> null;
        };

        if (status != null) {
            console.println("Books with status: " + status);
            ArrayList<LibraryBook> books = library.list(status);
            if (books.isEmpty()) {
                console.println("No books found.");
            } 
            else {
                for (LibraryBook book : books) {
                	console.println(book.toString());
                	displayImage(book.getImage());
                }
            }
        } else {
            console.println("Invalid choice.");
        }
    }

    /*
     * Allows the user to add a new book by entering details.
     * Validates input before adding.
     */
    private static void addBook() {
        console.clear();
        console.println("Add a New Book");

        console.println("Title: ");
        String title = console.readLn();

        console.println("Author: ");
        String author = console.readLn();

        console.println("ISBN (10 digits): ");
        String isbn = console.readLn();

        console.println("Edition (≥ 1): ");
        int edition = readInt();

        console.println("Summary (20-150 chars): ");
        String summary = console.readLn();

        console.println("Price (£): ");
        double price = readDouble();

        console.println("Select Book Type: 1️. FICTION  2️. NON_FICTION  3️. REFERENCE");
        console.println("Enter type: ");
        int typeChoice = readInt();

        BookType type = switch (typeChoice) {
            case 1 -> BookType.FICTION;
            case 2 -> BookType.NON_FICTION;
            case 3 -> BookType.REFERENCE;
            default -> null;
        };

        if (type == null) {
            console.println("Invalid book type.");
            return;
        }

        console.println("Image filename: ");
        String image = console.readLn();
        
        // Display success or error message
        console.println(library.add(title, author, isbn, type, edition, summary, price, image));
    }

    /*
     * Removes a book from the library.
     */
    private static void removeBook() {
        console.clear();
        console.println("Enter Book ID to remove: ");
        int id = readInt();

        if (library.remove(id)) {
            console.println("Book successfully removed (marked as withdrawn).");
        } 
        else {
            console.println("Unable to remove book.");
      }
    }

    /*
     * Allows a user to borrow a book if it is available.
     */
    private static void borrowBook() {
        console.clear();
        console.println("Enter Book ID to borrow: ");
        int id = readInt();

        if (library.borrowBook(id)) {
            console.println("Book borrowed successfully!");
        } 
        else {
            console.println("Book is not available.");
       }
    }

    /*
     * Allows a user to return a borrowed book.
     */
    private static void returnBook() {
        console.clear();
        console.println("Enter Book ID to return: ");
        int id = readInt();

        if (library.returnBook(id)) {
            console.println("Book returned successfully!");
        } 
        else {
            console.println("Unable to return book.");
        }
    }

    /*
     * Displays the top 3 most borrowed books in the library.
     */
    private static void displayMostBorrowed() {
        console.clear();
        console.println("Most Borrowed Books:");
        ArrayList<LibraryBook> books = library.mostPopular();
        int count = 0;
        for (LibraryBook book : books) {
            if (count >= 3) {
                break;
            }
            console.println("Title: " + book.getTitle() + " | Author: " + book.getAuthor() + " | Borrowed: " + book.getLoanCount() + " times");
            count++;
        }
    }

    /*
     * Reads an integer input.
     */
    private static int readInt() {
        while (true) {
            try {
                return Integer.parseInt(console.readLn());
            } catch (NumberFormatException e) {
                console.println("Invalid input. Please enter a number.");
           }
        }
    }

    /*
     * Reads a double input.
     */
    private static double readDouble() {
        while (true) {
            try {
                return Double.parseDouble(console.readLn());
            } catch (NumberFormatException e) {
                console.println("Invalid input. Please enter a valid number.");
          }
       }
    }

    /*
     * Pauses the console and waits for the user to press ENTER.
     */
    private static void pauseConsole() {
        console.println("\nPress ENTER to continue...");
        console.readLn();
    }

    /**
     * Displays an image in the console and
     * resizes the image before displaying it.
     */
    private static void displayImage(String imagePath) {
        try {
            ImageIcon originalIcon = new ImageIcon(imagePath);
            Image scaledImage = originalIcon.getImage().getScaledInstance(500, 200, Image.SCALE_DEFAULT);
            ImageIcon resizedIcon = new ImageIcon(scaledImage);
            console.println(resizedIcon);
        } catch (Exception e) {
            console.println("Error loading image: " + imagePath);
       }
    }
}
