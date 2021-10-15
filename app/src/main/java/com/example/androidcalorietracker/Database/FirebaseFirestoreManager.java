package com.example.androidcalorietracker.Database;

import static android.content.ContentValues.TAG;
import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;

import com.example.androidcalorietracker.DataModel.ItemEntryObject;
import com.example.androidcalorietracker.DataModel.MealEntryObject;
import com.example.androidcalorietracker.Time.TimeGenerator;
import com.example.androidcalorietracker.User.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FirebaseFirestoreManager {
    public static String ROOT_COLLECTION  = "Users";
    public static String DAYS_COLLECTION  = "Days";
    public static String MEALS_COLLECTION = "Meals";

    public static FirebaseFirestore database = null;
    private static String user = "user1";

    // private constructor for singleton
    private FirebaseFirestoreManager(){
    }

    public static void init(){
        if(database == null){
            database = FirebaseFirestore.getInstance();
        }
    }

    public static void addNewMealToFirebaseFirestore(MealEntryObject meal){
        String dayDocumentKey = TimeGenerator.getDateDatabaseKey();

        database.collection(ROOT_COLLECTION).document(user)
                .collection(DAYS_COLLECTION).document(dayDocumentKey)
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


    public static void addNewUser(User newUser){

        database.collection(ROOT_COLLECTION).document(newUser.getUsername() )
                .set(newUser)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "DocumentSnapshot successfully written!");
                user = newUser.getUsername();
            }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.w(TAG, "Error writing document", e);
                }
            });
    }

    public static FirebaseFirestore getDatabase(){
        return database;
    }
    public static String getUser(){ return user; }



}
