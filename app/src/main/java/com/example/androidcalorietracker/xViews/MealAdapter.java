package com.example.androidcalorietracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidcalorietracker.DataModel.ItemEntryObject;
import com.example.androidcalorietracker.DataModel.MealEntryObject;

import java.util.List;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.MealViewHolder>{

    Context context;
    List<ItemEntryObject> items;

    public MealAdapter(Context context, List<ItemEntryObject> items){
        this.items = items;
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.meal_item_row, viewGroup, false);

        return new MealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        holder.amountTV.setText( Double.toString( items.get(position).getAmount() ) );

        String unitsString = items.get(position).getMeasureBy().equals("Hand") ? " " : items.get(position).getMeasureBy();

        holder.unitTV.setText( unitsString );
        holder.nameTV.setText(  items.get(position).getItemName() );
        holder.caloriesTV.setText( Integer.toString( items.get(position).getCalories() ) + " calories" );
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    /*
     SubClass
    */
    public class MealViewHolder extends  RecyclerView.ViewHolder {
        public TextView amountTV, unitTV, nameTV, caloriesTV;

        public MealViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            amountTV   = (TextView) view.findViewById(R.id.amountTV);
            unitTV     = (TextView) view.findViewById(R.id.unitTV);
            nameTV     = (TextView) view.findViewById(R.id.nameTV);
            caloriesTV = (TextView) view.findViewById(R.id.caloriesTV);
        }
    }
}
