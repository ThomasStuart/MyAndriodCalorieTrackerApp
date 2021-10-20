package com.example.androidcalorietracker.xViews;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.androidcalorietracker.DataModel.ItemEntryObject;
import com.example.androidcalorietracker.DataModel.MealEntryObject;
import com.example.androidcalorietracker.Database.FirebaseFirestoreManager;
import com.example.androidcalorietracker.LocalStorage.SharedPreferencesManager;
import com.example.androidcalorietracker.R;
import com.example.androidcalorietracker.Time.TimeGenerator;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidcalorietracker.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private RecyclerView breakfastRecyclerView;
    private RecyclerView lunchRecyclerView;
    private RecyclerView dinnerRecyclerView;

    private  MealAdapter breakfastAdapter;
    private  MealAdapter lunchAdapter;
    private  MealAdapter dinnerAdapter;

    private List<ItemEntryObject> breakfastList = new ArrayList<>();
    private List<ItemEntryObject> lunchList     = new ArrayList<>();
    private List<ItemEntryObject> dinnerList    = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /* new code */
        // Start local storage
        SharedPreferencesManager.init(getApplicationContext());
        // Start up the firebase
        FirebaseFirestoreManager.init();

        // find views
        breakfastRecyclerView = findViewById(R.id.bListView);
        lunchRecyclerView     = findViewById(R.id.lListView);
        dinnerRecyclerView    = findViewById(R.id.dListView);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);


        //Load content
        // (1) breakfast content
        breakfastAdapter = new MealAdapter(this, breakfastList);
        breakfastRecyclerView.setAdapter(breakfastAdapter);
        getMealFromFirebaseFirestore("Breakfast", breakfastList, breakfastAdapter);
        breakfastRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // (2) lunch content
        lunchAdapter = new MealAdapter(this, lunchList);
        lunchRecyclerView.setAdapter(lunchAdapter);
        getMealFromFirebaseFirestore("Lunch", lunchList, lunchAdapter);
        lunchRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //(3) dinner content
        dinnerAdapter = new MealAdapter(this, dinnerList);
        dinnerRecyclerView.setAdapter(dinnerAdapter);
        getMealFromFirebaseFirestore("Dinner", dinnerList, dinnerAdapter);
        dinnerRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Set dates
        TextView dayAsWordView = (TextView) findViewById(R.id.DayView);
        dayAsWordView.setText(TimeGenerator.getDayOfWeekAsWord()+ " " + TimeGenerator.getMonthOfYear() + " " + TimeGenerator.getDayOfWeekAsInt());

        FloatingActionButton addMealButton = (FloatingActionButton) findViewById(R.id.addMealButton);
        addMealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v ){
                openAddMealActivity();
            }
        });

    }


    public void openAddMealActivity(){
        Intent intent = new Intent(this, AddMealActivity.class);
        startActivity(intent);
    }

    public void getMealFromFirebaseFirestore(String mealKey, List<ItemEntryObject> list, MealAdapter adapter ){
        String dayDocumentKey = TimeGenerator.getDateDatabaseKey();

        DocumentReference docRef = FirebaseFirestoreManager.getDatabase()
                .collection(FirebaseFirestoreManager.ROOT_COLLECTION).document(FirebaseFirestoreManager.getUser())
                .collection(FirebaseFirestoreManager.DAYS_COLLECTION).document(dayDocumentKey)
                .collection(FirebaseFirestoreManager.MEALS_COLLECTION).document(mealKey);

        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                MealEntryObject mealS = documentSnapshot.toObject(MealEntryObject.class);
                if (mealS == null ) return;
                //assert mealS != null;
                list.clear(); // clear old list
                List<ItemEntryObject> newItems =  mealS.getMealItems();
                list.addAll(newItems);
                //assert list != null;
                mealS.printItems();
                adapter.notifyDataSetChanged();
            }
        });
    }

}