/**
 * Book.java
 * Represents a single book in the library.
 * Demonstrates OOP Encapsulation with private fields and public methods.
 */
public class Book {

    private int id;
    private String title;
    private String author;
    private boolean issued;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.issued = false;   // new book is available by default
    }

    // ---------- Getters ----------
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isIssued() { return issued; }

    // ---------- Setters ----------
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setIssued(boolean issued) { this.issued = issued; }

    // ---------- Status ----------
    public String getStatus() {
        return issued ? "Issued" : "Available";
    }

    @Override
    public String toString() {
        return "ID: " + id +
               " | Title: " + title +
               " | Author: " + author +
               " | Status: " + getStatus();
    }
}