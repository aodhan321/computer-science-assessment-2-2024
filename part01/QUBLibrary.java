package part01;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * Console-based menu system.
 */
public class QUBLibrary {
    private static Library library = new Library(); // creating library instance.
    private static Scanner scanner = new Scanner(System.in);

    /*
     * Main menu loop for the library system.
     * Runs until the user selects 8.
     */
    public static void main(String[] args) {
        int choice;
        do {
            System.out.println(" QUB Library System ");
            System.out.println("1️ List All Books");
            System.out.println("2️ List Books by Status");
            System.out.println("3️ Add a Book");
            System.out.println("4️ Remove a Book");
            System.out.println("5️ Borrow a Book");
            System.out.println("6️ Return a Book");
            System.out.println("7️ Display Most Borrowed books");
            System.out.println("8️ Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> listAllBooks();
                case 2 -> listBooksByStatus();
                case 3 -> addBook();
                case 4 -> removeBook();
                case 5 -> borrowBook();
                case 6 -> returnBook();
                case 7 -> displayMostBorrowed();
                case 8 -> System.out.println("Exiting");
                default -> System.out.println("Invalid choice. Enter a number between 1 and 8.");
            }
        } while (choice != 8);
    }

    /*
     * Displays all books in the library.
     */
    private static void listAllBooks() {
        System.out.println("All Books in Library:");
        ArrayList<LibraryBook> books = library.list();
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            for (LibraryBook book : books) {
                System.out.println(book.toString());
                System.out.println();
          }
        }
    }

    /* 
     * Displays books filtered by their status.
     */
    private static void listBooksByStatus() {
        System.out.println("\nBook status options: 1. AVAILABLE  2. ON_LOAN  3. WITHDRAWN");
        System.out.print("Enter status number: ");
        int statusChoice = scanner.nextInt(); 

        BookStatus status = switch (statusChoice) {
            case 1 -> BookStatus.AVAILABLE;
            case 2 -> BookStatus.ON_LOAN;
            case 3 -> BookStatus.WITHDRAWN;
            default -> null;
        };

        if (status != null) {
            System.out.println("\nBooks with status: " + status);
            for (LibraryBook book : library.list(status)) {
                System.out.println(book);
        }
        } 
        else {
            System.out.println("Invalid status choice.");
        }
    }

    /* 
     * Allows the user to add a new book to the library
     * and validates input before adding.
     */
    private static void addBook() {
        scanner.nextLine();

        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Author: ");
        String author = scanner.nextLine();

        System.out.print("ISBN (10 digits): ");
        String isbn = scanner.nextLine();

        System.out.print("Edition (must be ≥ 1): ");
        int edition = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Summary (20-150 chars): ");
        String summary = scanner.nextLine();

        System.out.print("Price (£): ");
        double price = scanner.nextDouble();

        System.out.println("Select Book Type: 1. FICTION  2. NON_FICTION  3. REFERENCE");
        int typeChoice = scanner.nextInt();

        BookType type = switch (typeChoice) {
            case 1 -> BookType.FICTION;
            case 2 -> BookType.NON_FICTION;
            case 3 -> BookType.REFERENCE;
            default -> null;
        };

        if (type == null) {
            System.out.println("Invalid book type.");
            return;
        }


        System.out.print("Image filename: ");
        String image = scanner.nextLine();

        // Display success or error message
        System.out.println(library.add(title, author, isbn, type, edition, summary, price, image));
    }

    /* 
     * Removes a book from the library.
     */
    private static void removeBook() {
        System.out.print("\nEnter the book ID to remove: ");
        int id = scanner.nextInt();

        if (library.remove(id)) {
            System.out.println("Book successfully removed (marked as withdrawn).");
        } 
        else {
            System.out.println("Unable to remove book. It may not exist or is currently on loan.");
       }
    }

    /**
     * Allows a user to borrow a book if it is available.
     */
    private static void borrowBook() {
        System.out.print("\nEnter the Book ID to borrow: ");
        int id = scanner.nextInt();

        if (library.borrowBook(id)) {
            System.out.println("Book borrowed.");
        } 
        else {
            System.out.println("Book is not available for borrowing.");
       }
    }

    /* 
     * Allows a user to return a borrowed book.
     */
    private static void returnBook() {
        System.out.print("\nEnter the Book ID to return: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (library.returnBook(id)) {
            System.out.println("Book returned.");
        } 
        else {
            System.out.println("Unable to return book. Ensure it was borrowed first.");
        }
    }

    /* 
     * Displays the top 3 most borrowed books in the library.
     */
    private static void displayMostBorrowed() {
        System.out.println("\nMost Borrowed Books:");
        int count = 0;
        for (LibraryBook book : library.mostPopular()) {
            if (count >= 3) {
            	break;
            }
            System.out.println("Title: " + book.getTitle() + "  Author: " + book.getAuthor() + "  Borrowed: " + book.getLoanCount() + " times");
            count++;
      }
    }
}
