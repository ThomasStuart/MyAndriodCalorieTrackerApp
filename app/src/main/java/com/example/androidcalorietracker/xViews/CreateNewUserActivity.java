package com.example.androidcalorietracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidcalorietracker.DataModel.ItemEntryObject;
import com.example.androidcalorietracker.DataModel.MealEntryObject;
import com.example.androidcalorietracker.Database.FirebaseFirestoreManager;
import com.example.androidcalorietracker.User.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.List;

public class CreateNewUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_user);

        TextView usernameTextView = (TextView) findViewById(R.id.newUsernameTV);
        TextView passswordText    = (TextView) findViewById(R.id.newPasswordTV);

        Button doneButton = (Button) findViewById(R.id.submitNewUser);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v ){
                String username  = usernameTextView.getText().toString();
                String password  = passswordText.getText().toString();
                User newUser = new User(username, password);
                submitNewUser(newUser);
            }
        });
    }

    public void submitNewUser(User newUser){
        FirebaseFirestoreManager.init();
        FirebaseFirestoreManager.addNewUser(newUser);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}