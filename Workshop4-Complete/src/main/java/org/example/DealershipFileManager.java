package org.example;

import java.io.*;
import java.math.BigDecimal;

public class DealershipFileManager {
    private static String materialFile = "src/main/resources/inventory.csv";

    public Dealership getDealership() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(materialFile));
            String line = reader.readLine();
            if (line == null) {
                reader.close();
                System.out.println("File is empty.");
                return null; }

            String[] dealershipData = line.split("\\|");
            Dealership dealership = new Dealership(dealershipData[0], dealershipData[1], dealershipData[2]);
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue; }
                String[] parts = line.split("\\|");
                int vin = Integer.parseInt(parts[0]);
                int year = Integer.parseInt(parts[1]);
                String make = parts[2];
                String model = parts[3];
                String type = parts[4];
                String color = parts[5];
                int odometer = Integer.parseInt(parts[6]);
                BigDecimal price = new BigDecimal(parts[7]);
                Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
                dealership.addVehicle(vehicle); }
            reader.close();
            return dealership; }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("There was a problem reading the inventory file.");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Something went wrong with the file."); }
        return null; }

    public boolean saveDealership(Dealership dealership) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(materialFile));
            writer.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone());
            writer.newLine();
            for (Vehicle vehicle : dealership.getAllVehicles()) {
                writer.write(vehicle.getVin() + "|" +
                        vehicle.getYear() + "|" +
                        vehicle.getMake() + "|" +
                        vehicle.getModel() + "|" +
                        vehicle.getVehicleType() + "|" +
                        vehicle.getColor() + "|" +
                        vehicle.getOdometer() + "|" +
                        vehicle.getPrice().toPlainString());

                writer.newLine(); }
            writer.close();
            return true; }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("There was a problem saving the inventory file.");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Something went wrong with the file."); }
        return false; }
}
