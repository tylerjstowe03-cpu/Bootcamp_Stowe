package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    public static List<Inventory> getInventory() {
        List<Inventory> inventories = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/transactions.csv"));
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("date|")) {
                    continue;
                }
                String[] parts = line.split("\\|");
                if (line.startsWith("date|")) {
                    continue; }
                    String date = parts[0];
                    String time = parts[1];
                    String descriptionID = parts[2];
                    String vendor = parts[3];
                    double price = Double.parseDouble(parts[4]);

                    Inventory inventory = new Inventory(date, time, descriptionID, vendor, price);
                    inventories.add(inventory); }
                reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("There was a problem reading the inventory file.");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Something went from with the file.");
            throw new RuntimeException(e);}
        return inventories; }
    public static void writeInventor(Inventory inventory) {
        try {
            File file = new File("src/main/resources/transactions.csv");
            FileWriter fileWriter = new FileWriter(file, true);
            if (file.length() > 0) {
                fileWriter.write(System.lineSeparator());
            }
            fileWriter.write(String.format("%s|%s|%s|%s|%.2f", inventory.getDate(),
                    inventory.getTime(), inventory.getDescriptionID(), inventory.getVendors(), inventory.getPrice()));
            fileWriter.close(); }
        catch(IOException ex){
            System.out.println("Error writing to file."); }
    }
}