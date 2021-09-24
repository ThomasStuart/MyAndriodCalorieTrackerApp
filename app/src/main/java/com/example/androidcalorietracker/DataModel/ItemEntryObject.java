package com.example.androidcalorietracker.DataModel;

public class ItemEntryObject {

    private String itemName;
    private String measureBy;
    private float amount;
    private int   calories;

    public ItemEntryObject(String itemName, String measureBy, float amount, int calories) {
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

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

}
