/**
 * Student.java
 * Represents a single student record.
 * Demonstrates OOP Encapsulation using private fields and public getters.
 */
public class Student {

    // ---------- Attributes ----------
    private int id;
    private String name;
    private double marks;

    // ---------- Constructor ----------
    public Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    // ---------- Getters ----------
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMarks() {
        return marks;
    }

    // ---------- Setters ----------
    public void setName(String name) {
        this.name = name;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    // ---------- toString() for easy printing ----------
    @Override
    public String toString() {
        return String.format("| %-5d | %-20s | %-8.2f |", id, name, marks);
    }
}