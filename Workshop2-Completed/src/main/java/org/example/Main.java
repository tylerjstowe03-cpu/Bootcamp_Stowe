package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        Book[] books = new Book[20];
        books[0] = new Book(1, "978001", "Harry Potter", false, "");
        books[1] = new Book(2, "978002", "The Hobbit", false, "");
        books[2] = new Book(3, "978003", "The Great Gatsby", false, "");
        books[3] = new Book(4, "978004", "David Goes to School", true, "");
        books[4] = new Book(5, "978005", "Room on the Broom", false, "");
        books[5] = new Book(6, "978006", "Where the Wild Things Are", false, "");
        books[6] = new Book(7, "978007", "Curious George", true, "");
        books[7] = new Book(8, "978008", "Five Little Monkeys Jumping on the Bed", false, "");
        books[8] = new Book(9, "978009", "Don't Let the Pigeon Drive the Bus", true, "");
        books[9] = new Book(10, "978010", "The Rainbow Fish", true, "");
        books[10] = new Book(11, "978011", "Arthur's Pet Business", true, "");
        books[11] = new Book(12, "978012", "A Bad Case of Stripes", false, "");
        books[12] = new Book(13, "978013", "Alexander and the Terrible, Horrible, No Good, Very Bad Day", true, "");
        books[13] = new Book(14, "9780014", "The Bery Hungry Caterpillar", false, "");
        books[14] = new Book(15, "978015", "No David!", true, "");
        books[15] = new Book(16, "978016", "Corduroy", false, "");
        books[16] = new Book(17, "978017", "Llama Llama Red Pajama", true, "");
        books[17] = new Book(18, "978018", "There Was an Old Lady Who Swallowed a Fly", false, "");
        books[18] = new Book(19, "978019", "If You Give a Mouse a Cookie", false, "");
        books[19] = new Book(20, "978020", "Biscuit", true, "");

        while (choice != 3) {
            System.out.println("Neighborhood Library");
            System.out.println("1) Available Books");
            System.out.println("2) Checked Out Books");
            System.out.println("3) Exit");


            System.out.println("Enter your selection: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                System.out.println("Null input. Enter a number.");
                scanner.nextLine();
                continue;
            }
            if (choice == 1) {
                showAvailableBooks(books);
                System.out.println("Y - Check Out a Book");
                System.out.println("N - Return the Home Screen");
                System.out.println("Enter your choice");

                String option = scanner.next();

                if (option.equalsIgnoreCase("Y")) {
                    checkOutBook(books, scanner);
                } else if (option.equalsIgnoreCase("N")) {
                    System.out.println("Going to Home Menu...");
                } else {
                    System.out.println("Null Option");
                }
            } else if (choice == 2) {
                showCheckedOutBooks(books);
                System.out.println("Y - Check Out a Book");
                System.out.println("N - Return the Home Screen");
                System.out.println("Enter your choice");

                String option = scanner.next();
                if (option.equalsIgnoreCase("Y")) {
                    checkInBook(books, scanner);
                } else if (option.equalsIgnoreCase("N")) {
                    System.out.println("Going to Home Menu...");
                } else {
                    System.out.println("Null Option");
                }
            } else if (choice == 3) {
                System.out.println("Thank you for visting the Neighboehood Library. Take Care!");
            } else {
                System.out.println("Null Option");
            }
        }
        scanner.close();
    }

    public static void showAvailableBooks(Book[] books) {
        boolean found = false;
        System.out.println("Available Books");
        for (int i = 0; i < books.length; i++) {
            if (books[i].isCheckedOut()) {
                System.out.println("ID: " + books[i].getId());
                System.out.println("ISBN: " + books[i].getIsbn());
                System.out.println("Title: " + books[i].getTitle());
                System.out.println("Checked Out To: " + books[i].getCheckedOutTo());
                System.out.println("--------------------");
                found = true;
            }
        }
        if (!found) {
            System.out.println("Not Available"); }
    }
    public static void showCheckedOutBooks(Book[] books) {
        boolean found = false;
        System.out.println("Checked Out Book");
        for (int i = 0; i < books.length; i++) {
            if (books[i].isCheckedOut()) {
                System.out.println("ID: " + books[i].getId());
                System.out.println("ISBN: " + books[i].getIsbn());
                System.out.println("Title: " + books[i].getTitle());
                System.out.println("Checked Out To: " + books[i].getCheckedOutTo());
                System.out.println("--------------------");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books are currently checked out.");
        }
    }
    public static void checkOutBook(Book[] books, Scanner scanner) {
        System.out.println("Enter Book ID: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid Book ID.");
            scanner.nextLine();
            return;
        }
        int bookId = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < books.length; i++) {
            if (books[i].getId() == bookId) {
                if (books[i].isCheckedOut()) {
                    System.out.println("Already Checked Out.");
                    return;
                }
                System.out.println("Enter Name: ");
                String name = scanner.nextLine();

                if (name.trim().isEmpty()) {
                    System.out.println("Invalid Name: ");
                    return;
                }
                books[i].checkOut(name);
                System.out.println("Book successfully checked out to " + name);
                return;
            }
        }
    }
    public static void checkInBook(Book[] books, Scanner scanner) {
        System.out.println("Enter Book ID: ");
        int bookID = scanner.nextInt();
        for (int i = 0; i < books.length; i++) {
            if (books[i].getId() == bookID) {
                if (books[i].isCheckedOut()) {
                    books[i].checkIn();
                    System.out.println("Book has been returned.");
                } else {
                    System.out.println("This book is not checked out.");
                }
                return;
            }
        }
    }
}

