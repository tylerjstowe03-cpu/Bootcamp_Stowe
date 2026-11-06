package org.example;

public class Inventory {
    private String date;
    private String time;
    private String descriptionID;
    private String vendors;
    private double price;

    public Inventory(String date, String time, String descriptionID, String vendors, double price) {
        this.date = date;
        this.time = time;
        this.descriptionID = descriptionID;
        this.vendors = vendors;
        this.price = price; }

    public String getDate() {
        return date; }
    public void setDate(String date) {
        this.date = date; }
    public String getTime() {
        return time; }
    public void setTime(String time) {
        this.time = time; }
    public String getDescriptionID() {
        return descriptionID; }
    public void setDescriptionID(String descriptionID) {
        this.descriptionID = descriptionID; }
    public String getVendors() {
        return vendors; }
    public void setVendors(String vendors) {
        this.vendors = vendors; }
    public double getPrice() {
        return price; }
    public void setPrice(double price) {
        this.price = price; }

    @Override
    public String toString() {
        return String.format("%s|%s|%s|%s|%.2f", date, time, descriptionID, vendors, price); }
}
