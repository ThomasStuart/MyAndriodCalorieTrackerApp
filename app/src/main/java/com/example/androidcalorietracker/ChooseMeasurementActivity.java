package com.example.androidcalorietracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ChooseMeasurementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_measurement);

        ImageButton measureByCountButton = (ImageButton) findViewById(R.id.MBCB);
        measureByCountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v ){
                openMeasureByCountActivity();
            }
        });
    }

    public void openMeasureByCountActivity(){
        Intent intent = new Intent(this, MeasureByHandItemInputActivity.class);
        startActivity(intent);
    }
}