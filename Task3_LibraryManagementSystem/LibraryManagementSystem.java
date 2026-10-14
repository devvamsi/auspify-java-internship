import java.util.ArrayList;
import java.util.Scanner;

/**
 * LibraryManagementSystem.java
 * Console-based application to manage library books.
 *
 * Features:
 *   1. Add Book
 *   2. Remove Book
 *   3. View All Books
 *   4. View Only Available Books
 *   5. Search Book by ID
 *   6. Search Book by Title
 *   7. Issue Book
 *   8. Return Book
 *   9. Exit
 */
public class LibraryManagementSystem {

    private static final ArrayList<Book> books = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("=================================================");
        System.out.println("         LIBRARY MANAGEMENT SYSTEM              ");
        System.out.println("=================================================");

        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1: addBook(); break;
                case 2: removeBook(); break;
                case 3: viewAllBooks(); break;
                case 4: viewAvailableBooks(); break;
                case 5: searchById(); break;
                case 6: searchByTitle(); break;
                case 7: issueBook(); break;
                case 8: returnBook(); break;
                case 9:
                    System.out.println("\nExiting... Thank you for using Library Management System!");
                    running = false;
                    break;
                default:
                    System.out.println("[ERROR] Invalid choice! Please enter 1-9.");
            }
        }

        scanner.close();
    }

    // ---------- Menu ----------
    private static void printMenu() {
        System.out.println("\n----------------- MAIN MENU -----------------");
        System.out.println("1. Add Book");
        System.out.println("2. Remove Book");
        System.out.println("3. View All Books");
        System.out.println("4. View Available Books");
        System.out.println("5. Search Book by ID");
        System.out.println("6. Search Book by Title");
        System.out.println("7. Issue Book");
        System.out.println("8. Return Book");
        System.out.println("9. Exit");
        System.out.println("---------------------------------------------");
    }

    // ---------- Add Book ----------
    private static void addBook() {
        System.out.println("\n--- Add New Book ---");

        int id = readInt("Enter Book ID     : ");

        if (findBookById(id) != null) {
            System.out.println("[ERROR] A book with ID " + id + " already exists!");
            return;
        }

        System.out.print("Enter Book Title  : ");
        String title = scanner.nextLine().trim();

        if (title.isEmpty()) {
            System.out.println("[ERROR] Title cannot be empty.");
            return;
        }

        System.out.print("Enter Book Author : ");
        String author = scanner.nextLine().trim();

        if (author.isEmpty()) {
            System.out.println("[ERROR] Author cannot be empty.");
            return;
        }

        books.add(new Book(id, title, author));
        System.out.println("[OK] Book added successfully!");
    }

    // ---------- Remove Book ----------
    private static void removeBook() {
        System.out.println("\n--- Remove Book ---");

        if (books.isEmpty()) {
            System.out.println("[INFO] No books in library.");
            return;
        }

        int id = readInt("Enter Book ID to remove: ");
        Book b = findBookById(id);

        if (b == null) {
            System.out.println("[ERROR] Book with ID " + id + " not found.");
            return;
        }

        if (b.isIssued()) {
            System.out.println("[ERROR] Cannot remove an issued book. Return it first.");
            return;
        }

        books.remove(b);
        System.out.println("[OK] Book removed successfully!");
    }

    // ---------- View All Books ----------
    private static void viewAllBooks() {
        System.out.println("\n--- All Books ---");

        if (books.isEmpty()) {
            System.out.println("[INFO] No books in library.");
            return;
        }

        System.out.println("---------------------------------------------");
        for (int i = 0; i < books.size(); i++) {
            System.out.println((i + 1) + ". " + books.get(i));
        }
        System.out.println("---------------------------------------------");
        System.out.println("Total Books: " + books.size());
    }

    // ---------- View Available Books ----------
    private static void viewAvailableBooks() {
        System.out.println("\n--- Available Books ---");

        int count = 0;
        for (Book b : books) {
            if (!b.isIssued()) {
                System.out.println(b);
                count++;
            }
        }

        if (count == 0) {
            System.out.println("[INFO] No available books right now.");
        } else {
            System.out.println("---------------------------------------------");
            System.out.println("Available Count: " + count);
        }
    }

    // ---------- Search by ID ----------
    private static void searchById() {
        System.out.println("\n--- Search Book by ID ---");

        if (books.isEmpty()) {
            System.out.println("[INFO] No books to search.");
            return;
        }

        int id = readInt("Enter Book ID: ");
        Book b = findBookById(id);

        if (b != null) {
            System.out.println("[OK] Book Found:");
            System.out.println(b);
        } else {
            System.out.println("[ERROR] Book with ID " + id + " not found.");
        }
    }

    // ---------- Search by Title ----------
    private static void searchByTitle() {
        System.out.println("\n--- Search Book by Title ---");

        if (books.isEmpty()) {
            System.out.println("[INFO] No books to search.");
            return;
        }

        System.out.print("Enter Title (or part of it): ");
        String keyword = scanner.nextLine().trim().toLowerCase();

        if (keyword.isEmpty()) {
            System.out.println("[ERROR] Search keyword cannot be empty.");
            return;
        }

        int count = 0;
        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(keyword)) {
                System.out.println(b);
                count++;
            }
        }

        if (count == 0) {
            System.out.println("[ERROR] No books matched \"" + keyword + "\".");
        } else {
            System.out.println("---------------------------------------------");
            System.out.println("Matches Found: " + count);
        }
    }

    // ---------- Issue Book ----------
    private static void issueBook() {
        System.out.println("\n--- Issue Book ---");

        if (books.isEmpty()) {
            System.out.println("[INFO] No books in library.");
            return;
        }

        int id = readInt("Enter Book ID to issue: ");
        Book b = findBookById(id);

        if (b == null) {
            System.out.println("[ERROR] Book with ID " + id + " not found.");
            return;
        }

        if (b.isIssued()) {
            System.out.println("[ERROR] Book is already issued.");
            return;
        }

        b.setIssued(true);
        System.out.println("[OK] Book \"" + b.getTitle() + "\" issued successfully!");
    }

    // ---------- Return Book ----------
    private static void returnBook() {
        System.out.println("\n--- Return Book ---");

        if (books.isEmpty()) {
            System.out.println("[INFO] No books in library.");
            return;
        }

        int id = readInt("Enter Book ID to return: ");
        Book b = findBookById(id);

        if (b == null) {
            System.out.println("[ERROR] Book with ID " + id + " not found.");
            return;
        }

        if (!b.isIssued()) {
            System.out.println("[ERROR] This book was not issued.");
            return;
        }

        b.setIssued(false);
        System.out.println("[OK] Book \"" + b.getTitle() + "\" returned successfully!");
    }

    // ---------- Helpers ----------
    private static Book findBookById(int id) {
        for (Book b : books) {
            if (b.getId() == id) return b;
        }
        return null;
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("[!] Input cannot be empty.");
                continue;
            }
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("[!] Invalid input! Please enter a valid integer.");
            }
        }
    }
}