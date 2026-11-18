package org.example;

import java.math.BigDecimal;

public abstract class Asset {
    private String desciption;
    private String dateAcquired;
    private BigDecimal originalCost;

    public Asset(String desciption, String dateAcquired, BigDecimal originalCose) {
        this.desciption = desciption;
        this.dateAcquired = dateAcquired;
        this.originalCost = originalCose; }

    public String getDesciption() {
        return desciption; }
    public void setDesciption(String desciption) {
        this.desciption = desciption; }
    public String getDateAcquired() {
        return dateAcquired; }
    public void setDateAcquired(String dateAcquired) {
        this.dateAcquired = dateAcquired; }
    public BigDecimal getOriginalCose() {
        return originalCost; }
    public void setOriginalCose(BigDecimal originalCose) {
        this.originalCost = originalCose; }

    public abstract BigDecimal getValue();
}
