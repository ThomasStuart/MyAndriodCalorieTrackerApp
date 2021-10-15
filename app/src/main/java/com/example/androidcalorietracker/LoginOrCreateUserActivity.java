package com.example.androidcalorietracker;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.androidcalorietracker.Database.FirebaseFirestoreManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class LoginOrCreateUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        FirebaseFirestoreManager.init();

        TextView usernameTextView = (TextView) findViewById(R.id.userLoginTV);
        TextView passswordText    = (TextView) findViewById(R.id.passwordLoginTV);

        Button loginButton = (Button) findViewById(R.id.loginUserButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v ){
                checkCredentials(usernameTextView.getText().toString(), passswordText.getText().toString() );
            }
        });

        Button createNewUserButton = (Button) findViewById(R.id.createNewUserButton);
        createNewUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v ){
                openCreateNewUserActivity();
            }
        });

    }

    public void checkCredentials(String username, String password){

        Query myQuery = FirebaseFirestoreManager.getDatabase().collection("Users").whereEqualTo("username", username);

        myQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            System.out.println("SUCCESS");

                            for (QueryDocumentSnapshot document : task.getResult()) {

                                String userName = document.getString("username");
                                String passWord = document.getString("password");

                                if( userName.equals(username) && passWord.equals(password) ){
                                    openMainActivity();
                                }
                            }

                            if(task.getResult().size() == 0 ){
                                Log.d(TAG, "User not Exists");
                                //You can store new user information here

                            }

                        } else {
                            System.out.println("FAILURE");
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openCreateNewUserActivity(){
        Intent intent = new Intent(this, CreateNewUserActivity.class);
        startActivity(intent);
    }

}