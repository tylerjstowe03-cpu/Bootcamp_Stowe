package org.example;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static List<Product> products = new ArrayList<>();
    static ShoppingCart shoppingCart = new ShoppingCart();
    public static void main(String[] args) {
        products = FileManager.getProduct();

        while (true) {
            showmenu();

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    showAllProducts();
                    break;
                case 2:
                    searchMenu();
                    break;
                case 3:
                    priceRangePrompt();
                    break;
                case 4:
                    addProductPrompt();
                    break;
                case 5:
                    displayCart();
                    break;
                case 6:
                    System.out.println("Gracias, See you later!");
                    return;

                default:
                    System.out.println("Invalid option. Try again."); }
        }
    }

    private static void showmenu() {
        System.out.println("          Handy Manny's");
        System.out.println("<------------------------------>");
        System.out.println("1. View all items");
        System.out.println("2. ID Locator");
        System.out.println("3. Set Price Range");
        System.out.println("4. Add a item");
        System.out.println("5. Exit");
        System.out.print("Insert a option: "); }
    public static int readInt() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    public static Product selectProductBySku(String id) {
        for (Product product : products) {
            if (product.getId().equalsIgnoreCase(id)) {
                return product;
            }
        }
        return null;
    }
    public static void showAllProducts() {
        System.out.println("<---------------Available Items--------------->");
        if (products.isEmpty()) {
            System.out.println("No items are available.");
            return;
        }
        for (Product product : products) {
            printProduct(product);
        }
    }
    public static void printProduct(Product product) {
        System.out.printf(
                "SKU: %s|%s|$%.2f| %s%n",
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDepartment());
    }
    public static void searchMenu() {
        System.out.println("<---------------Search Items--------------->");
        System.out.println("1. Search by ID");
        System.out.println("2. Search by Name");
        System.out.println("3. Search by Department");
        System.out.println("4. Back to Home");
        System.out.print("Choose an option: ");

        int choice = readInt();

        switch (choice) {
            case 1:
                System.out.println("Enter ID: ");
                String id = scanner.nextLine();
                Product product = selectProductBySku(id);

                if (product != null) {
                    printProduct(product);
                } else {
                    System.out.println("Product not found.");
                }
                break;
            case 2:
                System.out.print("Enter product name: ");
                searchProductByName(scanner.nextLine());
                break;
            case 3:
                System.out.print("Enter department: ");
                searchProductsByCategory(scanner.nextLine());
                break;
            case 4:
                return;
            default:
                System.out.println("Invalid Option.");
        }
    }
    private static void searchProductByName(String s) {
    }
    public static void searchProductsByName (String name){
            boolean found = false;
            for (Product product : products) {
                if (product.getName().toLowerCase()
                        .contains(name.toLowerCase())) {

                    printProduct(product);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No matching items found."); }
        }
        public static void searchProductByPriceRange ( double minPrice, double maxPrice){
            boolean found = false;
            for (Product product : products) {
                if (product.getPrice() >= minPrice && product.getPrice() <= maxPrice) {
                    printProduct(product);
                    found = true; }
            }
            if (!found) {
                System.out.println("No items found in this price range."); }
        }
        public static void priceRangePrompt () {
            try {
                System.out.print("Minimum Price: $");
                double min = Double.parseDouble(scanner.nextLine());
                System.out.print("Maximum price: $");
                double max = Double.parseDouble(scanner.nextLine());

                if (min < 0 || max < min) {
                    System.out.println("Invalid price range.");
                    return;
                }
                searchProductByPriceRange(min, max);
            } catch (NumberFormatException e) {
                System.out.println("Please enter vaild prices."); }
        }
        public static void searchProductsByCategory (String category){
            boolean found = false;

            for (Product product : products) {
                if (product.getDepartment().equalsIgnoreCase(category)) {
                    printProduct(product);
                    found = true; }
            }
            if (!found) {
                System.out.println("No products found in this department."); }
        }
        public static void addProductPrompt () {
            System.out.print("Enter the SKU of the item: ");
            String id = scanner.nextLine();
            Product product = selectProductBySku(id);

            if (product != null) {
                shoppingCart.addToCart(product);
                System.out.println(product.getName() + " added to cart!");
            } else {
                System.out.println("Product not found."); }
        }
        public static void displayCart () {

            while (true) {
                System.out.println("<---------------Your Shopping Cart--------------->");
                if (shoppingCart.cart.isEmpty()) {
                    System.out.println("Your cart is empty.");
                } else {
                    for (Product product : shoppingCart.cart) {
                        printProduct(product);
                    }
                }
                System.out.printf("Total: $%.2f%n",
                        shoppingCart.getCartTotal());

                System.out.println("1. Checkout");
                System.out.println("2. Remove Product");
                System.out.println("3. Back to Home");

                System.out.print("Choose an option: ");
                int choice = readInt();

                switch (choice) {
                    case 1:
                        checkout();
                        break;

                    case 2:
                        removeProductPrompt();
                        break;

                    case 3:
                        return;

                    default:
                        System.out.println("Invalid option.");
                }
            }
        }
        public static void removeProductPrompt () {

            System.out.print("Enter SKU to remove: ");
            String sku = scanner.nextLine();

            Product productToRemove = null;

            for (Product product : shoppingCart.cart) {

                if (product.getId().equalsIgnoreCase(sku)) {
                    productToRemove = product;
                    break;}
            }
            if (productToRemove != null) {
                shoppingCart.removeFromCart(productToRemove);
                System.out.println("Product removed from cart.");
            } else {
                System.out.println("Product not found in cart."); }
        }
        public static void checkout() {

            if (shoppingCart.cart.isEmpty()) {
                System.out.println("Your cart is empty.");
                return;
            }

            LocalDateTime now = LocalDateTime.now();

            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");

            String fileName = now.format(formatter) + ".txt";

            File folder = new File("Receipts");

            if (!folder.exists() && !folder.mkdirs()) {
                System.out.println("Could not create Receipts folder.");
                return; }
            File receiptFile = new File(folder, fileName);

            StringBuilder receipt = new StringBuilder();

            receipt.append("<---------------HANDY MANNY'S STORE--------------->");
            receipt.append("<------------------------------>");
            receipt.append("Date: ").append(now).append("");

            for (Product product : shoppingCart.cart) {

                receipt.append(String.format(
                        "%s | %s | $%.2f%n",
                        product.getId(),
                        product.getName(),
                        product.getPrice())); }
        receipt.append("<------------------------------>");

        receipt.append(String.format(
                "TOTAL: $%.2f%n",
                shoppingCart.getCartTotal()));
        receipt.append("Thank you for shopping!\n");

        try (FileWriter writer = new FileWriter(receiptFile)) {

            writer.write(receipt.toString());
            System.out.println("\n" + receipt);
            System.out.println("Receipt saved: "
                    + receiptFile.getPath());

            shoppingCart.cart.clear();
        } catch (IOException e) {
            System.out.println("Error creating receipt."); }
    }
}