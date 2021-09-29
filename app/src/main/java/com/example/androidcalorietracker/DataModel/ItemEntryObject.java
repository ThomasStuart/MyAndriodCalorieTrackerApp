package com.example.androidcalorietracker.DataModel;

public class ItemEntryObject {

    private String itemName;
    private String measureBy;
    private double amount;
    private int   calories;

    public ItemEntryObject(String itemName, String measureBy, double amount, int calories) {
        this.itemName = itemName;
        this.measureBy = measureBy;
        this.amount = amount;
        this.calories = calories;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getMeasureBy() {
        return measureBy;
    }

    public void setMeasureBy(String measureBy) {
        this.measureBy = measureBy;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

}
