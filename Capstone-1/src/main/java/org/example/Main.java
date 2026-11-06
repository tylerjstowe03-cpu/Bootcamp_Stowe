package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static List<Inventory> transactions = new ArrayList<>();
    static AddCart addCart = new AddCart();

    public static void main(String[] args) {
        transactions = FileManager.getInventory();
        boolean running = true;

        while (running) {
            showMenu();
            String choice = scanner.nextLine().trim().toUpperCase();
            switch (choice) {
                case "D":
                    addDeposit();
                    break;
                case "P":
                    makePayment();
                    break;
                case "L":
                    ledgerMenu();
                    break;
                case "X":
                    System.out.println("Thank you for visiting, See you later!");
                    return;
                default:
                    System.out.println("Null option, Try again."); } }
        System.out.println("Goodbye, have a great day!");
    }

    private static void showMenu() {
        System.out.println("\u001B[32m      Everest Digital Banking\u001B[0m");
        System.out.println("<======================================>");
        System.out.println("\u001B[32m    Welcome to your financial hub!\u001B[0m");
        System.out.println("<======================================>");
        System.out.println("D) Add Deposit");
        System.out.println("P) Make Payment");
        System.out.println("L) Ledger");
        System.out.println("X) Exit");
        System.out.println("<======================================>");
        System.out.print("Insert a option: "); }

    public static void ledgerMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\u001B[32m                 Ledger\u001B[0m");
            System.out.println("<======================================>");
            System.out.println("A) All Transactions");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");
            System.out.print("Selection: ");
            String choice = scanner.nextLine().trim().toUpperCase();
            switch (choice) {
                case "A":
                    showTransactions("A");
                    break;
                case "D":
                    showTransactions("D");
                    break;
                case "P":
                    showTransactions("P");
                    break;
                case "R": statementReports();
                    break;
                case "H": running = false;
                    break;
                default:
                    System.out.println("\\u001B[31mVoid decision.\u001B[0m");}
        }
    }

    public static void addDeposit() {
        addTransaction(true); }

    public static void makePayment() {
        addTransaction(false); }

    public static void addTransaction(boolean deposit) {
        System.out.print("Description: ");
        String description = scanner.nextLine().trim();
        System.out.println("Vendor: ");
        String vendor = scanner.nextLine().trim();
        System.out.println("Bill: ");
        double bill;
        try {
            bill = Double.parseDouble(scanner.nextLine());
            if (bill <= 0) {
                System.out.println("Amount must be greater than zero.");
                return; }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number.");
            return; }

        if (!deposit) {
            bill = -bill; }
            String date = LocalDate.now().toString();
            String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

            Inventory item = new Inventory(date, time, description, vendor, bill);
            FileManager.writeInventor(item);
            transactions.add(item);
            System.out.println("\u001B[32mTransaction completed!\u001B[0m"); }

    public static void showTransactions(String type) {
        System.out.println("           Transactions");
        System.out.println("<======================================>");
        boolean found = false;

        for (Inventory unit : newestFirst()) {
            boolean show = false;
            switch (type) {
                case "A":
                    show = true;
                    break;
                case "D":
                    if (unit.getPrice() > 0) {
                        show = true; }
                    break;
                case "P":
                    if (unit.getPrice() < 0) {
                        show = true; }
                    break; }
            if (show) {
                System.out.println(unit);
                found = true; }
        }
        if (!found) {
            System.out.println("\u001B[31mNo purchases or payments found.\u001B[0m"); }
    }

    private static List<Inventory> newestFirst() {
        ArrayList<Inventory> category = new ArrayList<>(transactions);
        for (int i = 0; i < category.size(); i++) {
            for (int j = i + 1; j < category.size(); j++) {
                Inventory first = category.get(i);
                Inventory second = category.get(j);
                String firstDateTime = first.getDate() + first.getTime();
                String secondDateTime = second.getDate() + second.getTime();
                if (secondDateTime.compareTo(firstDateTime) > 0) {
                    category.set(i, second);
                    category.set(j, first); }
            }
        }
        return category; }

    public static void dateReport(int option) {
        LocalDate today = LocalDate.now();
        LocalDate lastMonth = today.minusMonths(1);
        boolean found = false;

        System.out.println("<========== Report Results ==========>");
        for (Inventory unit : newestFirst()) {
            LocalDate date;
        try {
            date = LocalDate.parse(unit.getDate());
        } catch (Exception e) {
            continue; }

        boolean equate = false;
        switch (option) {
            case 1:
                equate = date.getYear() == today.getYear()
                        && date.getMonthValue() == today.getMonthValue()
                        && !date.isAfter(today);
                break;
            case 2:
                equate = date.getYear() == lastMonth.getYear()
                        && date.getMonthValue() == lastMonth.getMonthValue();
                break;
            case 3:
                equate = date.getYear() == today.getYear()
                        && !date.isAfter(today);
                break;
            case 4:
                equate = date.getYear() == today.getYear() - 1;
                break; }
                if (equate) {
                    System.out.println(unit);
                    found = true; }
        } if (!found) { System.out.println("\u001B[31mTransaction history is empty.\u001B[0m"); }
        }

    public static void vendorReport() {
        System.out.print("Vendor Name:");
        String vendor = scanner.nextLine().trim();
        boolean found = false;
        System.out.println("           Vendor Outcome");
        System.out.println("<======================================>");

        if (vendor.isEmpty()) {
            System.out.println("Enter vendor credentials.");
            return; }
        List<Inventory> units = newestFirst();

        for (int i = 0; i < units.size(); i++) {
            Inventory unit = units.get(i);
            if (unit.getVendors().toLowerCase().contains(vendor.toLowerCase())) {
                System.out.println(unit);
                found = true; }
        }
        if (!found) {
            System.out.println("\u001B[31mNo matching transactions.\u001B[0m"); }
    }

    public static void statementReports() {
        boolean running = true;
        while (running) {
            System.out.println("               Reports");
            System.out.println("<======================================>");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search By Vendor");
            System.out.println("0) Back to Ledger");

            System.out.print("Selection: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    dateReport(1);
                    break;
                case "2":
                    dateReport(2);
                    break;
                case "3":
                    dateReport(3);
                    break;
                case "4":
                    dateReport(4);
                    break;
                case "5":
                    vendorReport();
                    break;
                case "0":
                    running = false;
                    break;

                default:
                    System.out.println("\u001B[31mInvalid option.\u001B[0m"); }
        }
    }

    }