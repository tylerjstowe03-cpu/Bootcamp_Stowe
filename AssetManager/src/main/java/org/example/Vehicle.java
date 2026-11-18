package org.example;

import java.math.BigDecimal;

public class Vehicle extends Asset {
    private String makeModel;
    private int year;
    private int odometer;

    public Vehicle(String desciption, String dateAcquired, BigDecimal originalCose, String makeModel, int year, int odometer) {
        super(desciption, dateAcquired, originalCose);
        this.makeModel = makeModel;
        this.year = year;
        this.odometer = odometer; }

    public String getMakeModel() {
        return makeModel; }
    public void setMakeModel(String makeModel) {
        this.makeModel = makeModel; }
    public int getYear() {
        return year; }
    public void setYear(int year) {
        this.year = year; }
    public int getOdometer() {
        return odometer; }
    public void setOdometer(int odometer) {
        this.odometer = odometer; }
    private BigDecimal getOriginalCost() {
        return getOriginalCose(); }

    @Override
    public BigDecimal getValue() {
        BigDecimal value = getOriginalCost();
        BigDecimal depreciationRate;

        if (year <= 3) {
            depreciationRate = new BigDecimal("0.03");
        } else if (year <= 6) {
            depreciationRate = new BigDecimal("0.06");
        } else if (year <= 10) {
            depreciationRate = new BigDecimal("0.08");
        } else {
            depreciationRate = BigDecimal.ZERO; }
        if (year > 10) {
            value = new BigDecimal("1000.00");
        } else {
            BigDecimal depreciation = value.multiply(depreciationRate)
                    .multiply(BigDecimal.valueOf(year));
            value = value.subtract(depreciation); }
        if (odometer > 100000 && !makeModel.toLowerCase().contains("honda") && !makeModel.toLowerCase().contains("toyota")) {
            value = value.multiply(new BigDecimal("0.75")); }
        return value; }
}
