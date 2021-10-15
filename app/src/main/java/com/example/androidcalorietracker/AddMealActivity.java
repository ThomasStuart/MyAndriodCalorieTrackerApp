package com.example.androidcalorietracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

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

        Switch snackSwitch        = (Switch)   findViewById(R.id.snakSwitch);

        Button doneAddingMealButton = (Button) findViewById(R.id.doneAddingMealButton);
        doneAddingMealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                uploadMealToFirebaseFirestore(items, snackSwitch.isChecked() );
                SharedPreferencesManager.clearItemEntryArray();
                finish();
            }
        });
    }

    public void openChooseMeasurementActivity(){
        Intent intent = new Intent(this, ChooseMeasurementActivity.class);
        startActivity(intent);
    }

    public void uploadMealToFirebaseFirestore(List<ItemEntryObject> items,  boolean isSnackChecked){
        String mealName = determineMealType(isSnackChecked);

        MealEntryObject meal = new MealEntryObject(items, mealName, determineMealType(isSnackChecked), TimeGenerator.getTimeStamp());
        FirebaseFirestoreManager.addNewMealToFirebaseFirestore( meal );
    }

    public String determineMealType( boolean isSnackChecked ){
        if(isSnackChecked) return "Snack";
        int hourOfDay = TimeGenerator.getMilitaryHoursInteger();
        String mealType = "";

        if (  isBetween(hourOfDay, 4, 11)) {
            mealType = "Breakfast";
        } else if (isBetween(hourOfDay, 12, 15)) {
            mealType = "Lunch";
        }else if (isBetween(hourOfDay, 16, 21)) {
            mealType = "Dinner";
        }
        else {
            mealType = "Snack";
        }
        return  mealType;
    }

    public static boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;
    }



}