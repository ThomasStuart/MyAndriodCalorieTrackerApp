package com.example.androidcalorietracker.DataModel;

import java.sql.Timestamp;
import java.util.List;

public class MealEntryObject {

    private List<ItemEntryObject> mealItems;
    private String mealName;
    private String mealType;
    private String timeKey;

    public MealEntryObject(List<ItemEntryObject> mealItems, String mealName, String mealType, String timeKey) {
        this.mealItems = mealItems;
        this.mealName = mealName;
        this.mealType = mealType;
        this.timeKey = timeKey;
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

    public String getMealTimeKey() {
        return timeKey;
    }

    public void setTimeEntered(String timeKey) {
        this.timeKey = timeKey;
    }


    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

}
