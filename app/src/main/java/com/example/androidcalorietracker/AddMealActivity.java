package com.example.androidcalorietracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddMealActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);


        FloatingActionButton addItemButton = (FloatingActionButton) findViewById(R.id.addItemButton);
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v ){
                openChooseMeasurementActivity();
            }
        });
    }

    public void openChooseMeasurementActivity(){
        Intent intent = new Intent(this, ChooseMeasurementActivity.class);
        startActivity(intent);
    }

}