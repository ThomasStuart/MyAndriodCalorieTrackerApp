package com.example.androidcalorietracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidcalorietracker.DataModel.ItemEntryObject;
import com.example.androidcalorietracker.LocalStorage.SharedPreferencesManager;

public class MeasureByHandItemInputActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measure_by_hand_item_input);

        TextView foodNameTextView = (TextView) findViewById(R.id.foodNameTextView);
        TextView countTextView    = (TextView) findViewById(R.id.inputCountTextView);
        TextView caloriesTextView = (TextView) findViewById(R.id.caloriesTextView);

        Button doneButton = (Button) findViewById(R.id.countItemDoneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v ){
                String foodItemName = foodNameTextView.getText().toString();
                double amount = Double.parseDouble(countTextView.getText().toString());
                int calories = Integer.parseInt(caloriesTextView.getText().toString());

                submitItemAndExitButtonAction(foodItemName, amount, calories );
            }
        });

    }

    public void submitItemAndExitButtonAction(String foodName, double amount, int calories){
        ItemEntryObject item = new ItemEntryObject(foodName, "Hand", amount, calories);

        SharedPreferencesManager.saveItemEntryObject( getApplicationContext() , item);

        Intent intent = new Intent(this, AddMealActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}