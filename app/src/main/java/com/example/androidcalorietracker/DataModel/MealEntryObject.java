package com.example.androidcalorietracker.DataModel;

import java.sql.Timestamp;
import java.util.List;

public class MealEntryObject {

    private List<ItemEntryObject> mealItems;
    private String mealType;
    private Timestamp timeEntered;

    public MealEntryObject(List<ItemEntryObject> mealItems, String mealType, Timestamp timeEntered) {
        this.mealItems = mealItems;
        this.mealType = mealType;
        this.timeEntered = timeEntered;
    }

    public List<ItemEntryObject> getMealItems() {
        return this.mealItems;
    }

    public void addMealItem (ItemEntryObject mealItem) {
        this.mealItems.add(mealItem);
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public Timestamp getTimeEntered() {
        return timeEntered;
    }

    public void setTimeEntered(Timestamp timeEntered) {
        this.timeEntered = timeEntered;
    }


}
