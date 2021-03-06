package com.example.androidcalorietracker.LocalStorage;

import static android.content.Context.MODE_PRIVATE;

import android.content.ClipData;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.androidcalorietracker.DataModel.ItemEntryObject;
import com.example.androidcalorietracker.User.User;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SharedPreferencesManager {

    private static SharedPreferences sharedPreferences = null;

    // private constructor for singleton
    private SharedPreferencesManager(){
    }

    public static void init(Context context){
        if(sharedPreferences == null){
            sharedPreferences =  context.getSharedPreferences(context.getPackageName(),MODE_PRIVATE);
        }
    }

    public static void saveItemEntryObject(Context context, ItemEntryObject item){
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        Gson gson = new Gson();

        // step 1 - get or make new array to store items
        List<ItemEntryObject> items = getItemEntries(context);

        // step 2 - add new item to array
        items.add(item);

        // step 3 - add to internal storage
        String json = gson.toJson(items);
        prefsEditor.putString("MyObject", json);
        prefsEditor.commit();
    }

    public static List<ItemEntryObject> getItemEntries(Context context){
        Gson gson = new Gson();
        Type type = new TypeToken<List<ItemEntryObject>>(){}.getType();
        String sp = sharedPreferences.getString("MyObject", "");

        List<ItemEntryObject> items =  gson.fromJson(sp, type);
        if(items == null){
            return new ArrayList<>();
        }
        return items;
    }

    public static void saveUserToSharedPreferences(Context context, User user){
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        prefsEditor.putString("User", json);
        prefsEditor.commit();
    }

    public static User getUserFromSharedPreferences(){
        Gson gson = new Gson();
        Type type = new TypeToken<User>(){}.getType();
        String sp = sharedPreferences.getString("User", "");

       return  gson.fromJson(sp, type);
    }

    public static void clearItemEntryArray(){
        sharedPreferences.edit().remove("MyObject").commit();
    }

}
