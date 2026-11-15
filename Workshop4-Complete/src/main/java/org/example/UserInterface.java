package org.example;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;



public class UserInterface {
    private Dealership dealership;
    private Scanner scanner;
    private DealershipFileManager fileManager;

    public UserInterface() {
        scanner = new Scanner(System.in);
        fileManager = new DealershipFileManager(); }
    private void init() {
        dealership = fileManager.getDealership(); }
    public void display() {
        init();
        if (dealership == null) { return; }


        boolean startup = true;
        while (startup) {
            System.out.println("\u001B[32m ========================================\u001B[0m");
            System.out.println("              " + dealership.getName());
            System.out.println("\u001B[32m ========================================\u001B[0m");
            System.out.println("1 - Vehicle Price Range");
            System.out.println("2 - Make & Model");
            System.out.println("3 - Vehicle Year Range");
            System.out.println("4 - Vehicle Color");
            System.out.println("5 - Mileage Range");
            System.out.println("6 - Vehicle Type");
            System.out.println("7 - Vehicle List");
            System.out.println("8 - Add a vehicle");
            System.out.println("9 - Remove a vehicle");
            System.out.println("99 -\u001B[31m Quit \u001B[0m");

            try {
                System.out.print("Choose an option: ");
                String input = scanner.nextLine();
                int choice = Integer.parseInt(input);
                switch (choice) {
                    case 1:
                        System.out.println("\u001B[33m Seeking Vehicle price... \u001B[0m");
                        processGetByPriceRequest();
                        break;
                    case 2:
                        System.out.println("\u001B[33m Finding Make & Model... \u001B[0m");
                        processGetByMakeModelRequest();
                        break;
                    case 3:
                        System.out.println("\u001B[33m Searching for Year Range... \u001B[0m");
                        processGetByYearRequest();
                        break;
                    case 4:
                        System.out.println("\u001B[33m Seeking for Vehicle Color... \u001B[0m");
                        processGetByColorRequest();
                        break;
                    case 5:
                        System.out.println("\u001B[33m Roaming for Vehicle Milage Range... \u001B[0m");
                        processGetByMileageRequest();
                        break;
                    case 6:
                        System.out.println("\u001B[33m Searching Vehicle Type... \u001B[0m");
                        processGetByVehicleTypeRequest();
                        break;
                    case 7:
                        System.out.println("\u001B[33m Showing all Vehicles... \u001B[0m");
                        processGetAllVehiclesRequest();
                        break;
                    case 8:
                        System.out.println("\u001B[33m Adding Vehicle... \u001B[0m");
                        processAddVehicleRequest();
                        break;
                    case 9:
                        System.out.println("\u001B[33m Removing Vehicle... \u001B[0m");
                        processRemoveVehicleRequest();
                        break;
                    case 99:
                        startup = false;
                        break;
                    default:
                        System.out.println("Invalid option. Try again.");
                        break; }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number."); }
        }
        System.out.println("Goodbye!"); }

    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
            return; }

        for (Vehicle vehicle : vehicles) {
            System.out.println(
                    vehicle.getVin() + " | " +
                            vehicle.getYear() + " | " +
                            vehicle.getMake() + " | " +
                            vehicle.getModel() + " | " +
                            vehicle.getVehicleType() + " | " +
                            vehicle.getColor() + " | " +
                            vehicle.getOdometer() + " | $" +
                            vehicle.getPrice()); }
    }

    private void processGetByPriceRequest() {
        System.out.print("Minimum price: ");
        String minInput = scanner.nextLine();
        BigDecimal min = new BigDecimal(minInput);

        System.out.print("Maximum price: ");
        String maxInput = scanner.nextLine();
        BigDecimal max = new BigDecimal(maxInput);

        List<Vehicle> matches = dealership.getVehiclesByPrice(min, max);
        displayVehicles(matches); }

    private void processGetByMakeModelRequest() {
        System.out.print("Make: ");
        String make = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();

        displayVehicles(dealership.getVehiclesByMakeModel(make, model)); }

    private void processGetByYearRequest() {
        System.out.print("Minimum year: ");
        int min = Integer.parseInt(scanner.nextLine());
        System.out.print("Maximum year: ");
        int max = Integer.parseInt(scanner.nextLine());

        displayVehicles(dealership.getVehiclesByYear(min, max)); }

    private void processGetByColorRequest() {
        System.out.print("Color: ");
        String color = scanner.nextLine();

        displayVehicles(dealership.getVehiclesByColor(color)); }

    private void processGetByMileageRequest() {
        System.out.print("Minimum mileage: ");
        int min = Integer.parseInt(scanner.nextLine());
        System.out.print("Maximum mileage: ");
        int max = Integer.parseInt(scanner.nextLine());

        displayVehicles(dealership.getVehiclesByMileage(min, max)); }

    private void processGetByVehicleTypeRequest() {
        System.out.print("Vehicle type: ");
        String type = scanner.nextLine();

        displayVehicles(dealership.getVehiclesByType(type)); }

    private void processGetAllVehiclesRequest() {
        displayVehicles(dealership.getAllVehicles()); }

    private void processAddVehicleRequest() {
        System.out.print("VIN: ");
        int vin = Integer.parseInt(scanner.nextLine());
        for (Vehicle existing : dealership.getAllVehicles()) {
            if (existing.getVin() == vin) {
                System.out.println("That VIN already exists.");
                return; }
        }
        System.out.print("Make: ");
        String make = scanner.nextLine();

        System.out.print("Year: ");
        int year = Integer.parseInt(scanner.nextLine());

        System.out.print("Model: ");
        String model = scanner.nextLine();

        System.out.print("Type (car, truck, SUV, van): ");
        String type = scanner.nextLine();

        System.out.print("Color: ");
        String color = scanner.nextLine();

        System.out.print("Mileage: ");
        int mileage = Integer.parseInt(scanner.nextLine());

        System.out.print("Price: ");
        BigDecimal price = new BigDecimal(scanner.nextLine());

        Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, mileage, price);
        dealership.addVehicle(vehicle);
        if (fileManager.saveDealership(dealership)) {
            System.out.println("Vehicle added and saved."); }
    }

    private void processRemoveVehicleRequest() {
        System.out.print("VIN of vehicle to remove: ");
        int vin = Integer.parseInt(scanner.nextLine());
        Vehicle found = null;

        for (Vehicle vehicle : dealership.getAllVehicles()) {
            if (vehicle.getVin() == vin) {
                found = vehicle;
                break; }
        }
        if (found == null) {
            System.out.println("No Vin found.");
            return; }
        dealership.removeVehicle(found);
        if (fileManager.saveDealership(dealership)) {
            System.out.println("Vehicle removed and saved."); }
    }
}
