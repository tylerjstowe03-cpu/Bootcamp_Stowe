package org.example;

public class Book {
    private int id;
    private String isbn;
    private String title;
    private boolean isCheckedOut;
    private String checkedOutTo;
    private String showAvailableBooks;

    public Book(int id, String isBn, String title, boolean isCheckedOut, String checkedOutTo) {
        this.id = id;
        this.isbn = isBn;
        this.title = title;
        this.isCheckedOut = isCheckedOut;
        this.checkedOutTo = checkedOutTo;
    }

    public int getId() {
        return id;}
    public void setId(int id) {
        this.id = id;}
    public String getIsbn() {
        return isbn;}
    public void setIsbn(String isBn) {
        this.isbn = isBn;}
    public String getTitle() {
        return title;}
    public void setTitle(String title) {
        this.title = title;}
    public boolean isCheckedOut() {
        return isCheckedOut;}
    public void setCheckedOut(boolean checkedOut) {
        isCheckedOut = checkedOut;}
    public String getCheckedOutTo() {
        return checkedOutTo;}
    public void setCheckedOutTo(String checkedOutTo) {
        this.checkedOutTo = checkedOutTo;}

    public void checkOut(String name) {
        checkedOutTo = name;
        isCheckedOut = true;
    }
    public void checkIn() {
        checkedOutTo = "";
        isCheckedOut = false;
    }
}
