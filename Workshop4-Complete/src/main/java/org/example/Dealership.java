package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        inventory = new ArrayList<Vehicle>(); }

    public String getName() {
        return name; }
    public void setName(String name) {
        this.name = name; }
    public String getAddress() {
        return address; }
    public void setAddress(String address) {
        this.address = address; }
    public String getPhone() {
        return phone; }
    public void setPhone(String phone) {
        this.phone = phone; }

    public List<Vehicle> getVehiclesByPrice(BigDecimal min, BigDecimal max) {
        List<Vehicle> matches = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getPrice().compareTo(min) < 0) {
                continue; }
            if (vehicle.getPrice().compareTo(max) > 0) {
                continue; }
            matches.add(vehicle); }
        return matches; }

    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        List<Vehicle> matches = new ArrayList<Vehicle>();
        for (Vehicle vehicle : inventory) {
            if (!make.isEmpty() && !vehicle.getMake().equalsIgnoreCase(make)) {
                continue; }
            if (!model.isEmpty() && !vehicle.getModel().equalsIgnoreCase(model)) {
                continue; }
            matches.add(vehicle); }
        return matches; }

    public List<Vehicle> getVehiclesByYear(int min, int max) {
        List<Vehicle> matches = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getYear() < min) {
                continue; }
            if (vehicle.getYear() > max) {
                continue; }
            matches.add(vehicle); }
        return matches; }

    public List<Vehicle> getVehiclesByColor(String color) {
        List<Vehicle> matches = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (!vehicle.getColor().equalsIgnoreCase(color)) {
                continue; }
            matches.add(vehicle); }
        return matches; }

    public List<Vehicle> getVehiclesByMileage(int min, int max) {
        List<Vehicle> matches = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getOdometer() < min) {
                continue; }
            if (vehicle.getOdometer() > max) {
                continue; }
            matches.add(vehicle); }
        return matches; }

    public List<Vehicle> getVehiclesByType(String vehicleType) {
        List<Vehicle> matches = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (!vehicle.getVehicleType().equalsIgnoreCase(vehicleType)) {
                continue; }
            matches.add(vehicle); }
        return matches; }

    public List<Vehicle> getAllVehicles() {
        return inventory; }
    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle); }
    public void removeVehicle(Vehicle vehicle) {
        inventory.remove(vehicle); }
}
