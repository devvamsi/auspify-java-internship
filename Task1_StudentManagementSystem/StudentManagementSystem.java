import java.util.ArrayList;
import java.util.Scanner;

/**
 * StudentManagementSystem.java
 * Main class - Console-based application to manage student records.
 *
 * Features:
 *   1. Add Student
 *   2. View All Students
 *   3. Search Student by ID
 *   4. Exit
 */
public class StudentManagementSystem {

    // ---------- Store data using ArrayList ----------
    private static final ArrayList<Student> students = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("=================================================");
        System.out.println("        STUDENT MANAGEMENT SYSTEM                ");
        System.out.println("=================================================");

        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    System.out.println("\nExiting... Thank you for using Student Management System!");
                    running = false;
                    break;
                default:
                    System.out.println("[ERROR] Invalid choice! Please enter 1-4.");
            }
        }

        scanner.close();
    }

    // ---------- Menu ----------
    private static void printMenu() {
        System.out.println("\n----------------- MAIN MENU -----------------");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student by ID");
        System.out.println("4. Exit");
        System.out.println("---------------------------------------------");
    }

    // ---------- Add Student ----------
    private static void addStudent() {
        System.out.println("\n--- Add New Student ---");

        int id = readInt("Enter Student ID   : ");

        // Check for duplicate ID
        if (findStudentById(id) != null) {
            System.out.println("[ERROR] A student with ID " + id + " already exists!");
            return;
        }

        System.out.print("Enter Student Name : ");
        String name = scanner.nextLine().trim();

        double marks = readDouble("Enter Student Marks: ");

        // Validate marks range
        if (marks < 0 || marks > 100) {
            System.out.println("[ERROR] Marks should be between 0 and 100.");
            return;
        }

        students.add(new Student(id, name, marks));
        System.out.println("[OK] Student added successfully!");
    }

    // ---------- View All Students ----------
    private static void viewAllStudents() {
        System.out.println("\n--- All Student Records ---");

        if (students.isEmpty()) {
            System.out.println("[INFO] No student records found.");
            return;
        }

        printTableHeader();
        for (Student s : students) {
            System.out.println(s);
        }
        printTableFooter();
        System.out.println("Total Students: " + students.size());
    }

    // ---------- Search Student by ID ----------
    private static void searchStudent() {
        System.out.println("\n--- Search Student ---");

        if (students.isEmpty()) {
            System.out.println("[INFO] No records available to search.");
            return;
        }

        int id = readInt("Enter Student ID to search: ");
        Student s = findStudentById(id);

        if (s != null) {
            System.out.println("[OK] Student Found:");
            printTableHeader();
            System.out.println(s);
            printTableFooter();
        } else {
            System.out.println("[ERROR] Student with ID " + id + " not found.");
        }
    }

    // ---------- Helper: Find by ID ----------
    private static Student findStudentById(int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    // ---------- Helper: Table Formatting ----------
    private static void printTableHeader() {
        System.out.println("+-------+----------------------+----------+");
        System.out.printf("| %-5s | %-20s | %-8s |%n", "ID", "NAME", "MARKS");
        System.out.println("+-------+----------------------+----------+");
    }

    private static void printTableFooter() {
        System.out.println("+-------+----------------------+----------+");
    }

    // ---------- Helper: Safe Integer Input ----------
    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("[!] Invalid input! Please enter a valid integer.");
            }
        }
    }

    // ---------- Helper: Safe Double Input ----------
    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("[!] Invalid input! Please enter a valid number.");
            }
        }
    }
}