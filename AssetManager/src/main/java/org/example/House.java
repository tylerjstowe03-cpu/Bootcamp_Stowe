package org.example;

import java.math.BigDecimal;

public class House extends Asset{
    private String address;
    private int condition; //(1 -excellent, 2 -good, 3 -fair, 4 -poor)
    private int squareFoot;
    private int lotSize;

    public House(String desciption, String dateAcquired, BigDecimal originalCose, String address, int condition, int squareFoot, int lotSize) {
        super(desciption, dateAcquired, originalCose);
        this.address = address;
        this.condition = condition;
        this.squareFoot = squareFoot;
        this.lotSize = lotSize; }

    public String getAddress() {
        return address; }
    public void setAddress(String address) {
        this.address = address; }
    public int getCondition() {
        return condition; }
    public void setCondition(int condition) {
        this.condition = condition; }
    public int getSquareFoot() {
        return squareFoot; }
    public void setSquareFoot(int squareFoot) {
        this.squareFoot = squareFoot; }
    public int getLotSize() {
        return lotSize; }
    public void setLotSize(int lotSize) {
        this.lotSize = lotSize; }


    @Override
    public BigDecimal getValue() {
        double price = 0;

        if (condition == 1) {
            price = 180.00;
        } else if (condition == 2) {
            price = 130.00;
        } else if (condition == 3) {
            price = 90.00;
        } else if (condition == 4) {
            price = 80.00; }
        double total = (squareFoot * price) + (lotSize * 0.25);
        return BigDecimal.valueOf(total); }
}
