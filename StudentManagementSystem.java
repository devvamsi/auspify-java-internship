import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagementSystem {

    private static final ArrayList<Student> students = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("=================================================");
        System.out.println("     🎓 STUDENT MANAGEMENT SYSTEM 🎓            ");
        System.out.println("=================================================");

        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1: addStudent(); break;
                case 2: viewAllStudents(); break;
                case 3: searchStudent(); break;
                case 4:
                    System.out.println("\n👋 Exiting... Thank you!");
                    running = false;
                    break;
                default:
                    System.out.println("❌ Invalid choice! Enter 1-4.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n----------------- MAIN MENU -----------------");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student by ID");
        System.out.println("4. Exit");
        System.out.println("---------------------------------------------");
    }

    private static void addStudent() {
        System.out.println("\n--- Add New Student ---");
        int id = readInt("Enter Student ID   : ");

        if (findStudentById(id) != null) {
            System.out.println("❌ Student ID " + id + " already exists!");
            return;
        }

        System.out.print("Enter Student Name : ");
        String name = scanner.nextLine().trim();

        double marks = readDouble("Enter Student Marks: ");

        if (marks < 0 || marks > 100) {
            System.out.println("❌ Marks must be between 0 and 100.");
            return;
        }

        students.add(new Student(id, name, marks));
        System.out.println("✅ Student added successfully!");
    }

    private static void viewAllStudents() {
        System.out.println("\n--- All Student Records ---");
        if (students.isEmpty()) {
            System.out.println("📭 No student records found.");
            return;
        }
        printTableHeader();
        for (Student s : students) System.out.println(s);
        printTableFooter();
        System.out.println("Total Students: " + students.size());
    }

    private static void searchStudent() {
        System.out.println("\n--- Search Student ---");
        if (students.isEmpty()) {
            System.out.println("📭 No records to search.");
            return;
        }
        int id = readInt("Enter Student ID to search: ");
        Student s = findStudentById(id);

        if (s != null) {
            System.out.println("✅ Student Found:");
            printTableHeader();
            System.out.println(s);
            printTableFooter();
        } else {
            System.out.println("❌ Student ID " + id + " not found.");
        }
    }

    private static Student findStudentById(int id) {
        for (Student s : students) {
            if (s.getId() == id) return s;
        }
        return null;
    }

    private static void printTableHeader() {
        System.out.println("+-------+----------------------+----------+");
        System.out.printf("| %-5s | %-20s | %-8s |%n", "ID", "NAME", "MARKS");
        System.out.println("+-------+----------------------+----------+");
    }

    private static void printTableFooter() {
        System.out.println("+-------+----------------------+----------+");
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("⚠️  Enter a valid integer.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("⚠️  Enter a valid number.");
            }
        }
    }
}