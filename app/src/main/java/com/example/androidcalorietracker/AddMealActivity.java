package com.example.androidcalorietracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.androidcalorietracker.DataModel.ItemEntryObject;
import com.example.androidcalorietracker.DataModel.MealEntryObject;
import com.example.androidcalorietracker.Database.FirebaseFirestoreManager;
import com.example.androidcalorietracker.Time.TimeGenerator;
import com.example.androidcalorietracker.LocalStorage.SharedPreferencesManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class AddMealActivity extends AppCompatActivity {
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);

        lv = (ListView) findViewById(R.id.mealList);
        List<ItemEntryObject> items = SharedPreferencesManager.getItemEntries(getApplicationContext());

        ItemAdapter itemAdapter = new ItemAdapter(this,  items);
        lv.setAdapter(itemAdapter);

        FloatingActionButton addItemButton = (FloatingActionButton) findViewById(R.id.addItemButton);
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v ){
                openChooseMeasurementActivity();
            }
        });

        Button doneAddingMealButton = (Button) findViewById(R.id.doneAddingMealButton);
        doneAddingMealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                uploadMealToFirebaseFirestore(items);
                SharedPreferencesManager.clearItemEntryArray();
                finish();
            }
        });
    }

    public void openChooseMeasurementActivity(){
        Intent intent = new Intent(this, ChooseMeasurementActivity.class);
        startActivity(intent);
    }

    public void uploadMealToFirebaseFirestore(List<ItemEntryObject> items){
        //TODO:: make meal name dynamic by adding a text field
        //TODO:: make meal type dynamic by using time
        MealEntryObject meal = new MealEntryObject(items, "meal1", "snack", TimeGenerator.getTimeStamp());
        //TODO:: make the user argument dynamic
        FirebaseFirestoreManager.addNewMealToFirebaseFirestore("user1", meal, TimeGenerator.getDateDatabaseKey() );
    }
}