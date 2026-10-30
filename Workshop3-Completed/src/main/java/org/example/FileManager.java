package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStreamReader;

public class FileManager {
    public static List<Product> getProduct(){
        List<Product> products = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(FileManager.class.getClassLoader().getResourceAsStream("products.csv")));
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("SKU|")) {
                    continue; }

                String[] parts = line.split("\\|");
                if (line.startsWith("SKU|")) {
                    continue; }
                String id = parts[0];
                String name = parts[1];
                double price = Double.parseDouble(parts[2]);
                String department = parts[3];

                Product product = new Product(id, name, price, department);
                products.add(product);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("There was a problem reading the inventory file.");
        } catch(Exception ex){
            ex.printStackTrace();
            System.out.println("Something went from with the file."); }
        return products;
    }
    public static void writeProduct(Product product) {
        try {
            File file = new File("src/main/resources/products.csv");
            FileWriter fileWriter = new FileWriter(file, true);
            if (file.length() > 0) {
                fileWriter.write(System.lineSeparator());
            }
            fileWriter.write(String.format("%s|%s|%.2f|%s", product.getId(),
                    product.getName(), product.getDepartment(), product.getPrice()));
            fileWriter.close(); }
        catch(IOException ex){
            System.out.println("Error writing to file."); }
    }
}

