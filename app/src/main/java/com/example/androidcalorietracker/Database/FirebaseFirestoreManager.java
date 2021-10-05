package com.example.androidcalorietracker.Database;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.androidcalorietracker.DataModel.MealEntryObject;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class FirebaseFirestoreManager {
    private static String ROOT_COLLECTION  = "Users";
    private static String DAYS_COLLECTION  = "Days";
    private static String MEALS_COLLECTION = "Meals";

    private static FirebaseFirestore database = null;

    // private constructor for singleton
    private FirebaseFirestoreManager(){
    }

    public static void init(){
        if(database == null){
            database = FirebaseFirestore.getInstance();
        }
    }

    public static void addNewMealToFirebaseFirestore(String user, MealEntryObject meal, String dayKey){
        Map<String, Object> map = new HashMap<>();
        map.put(meal.getMealName(), meal);

        database.collection(ROOT_COLLECTION).document(user)
                .collection(DAYS_COLLECTION).document(dayKey)
                .collection(MEALS_COLLECTION).document(meal.getMealName())
                .set(meal)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }

}
