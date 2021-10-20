package com.example.androidcalorietracker;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.androidcalorietracker.DataModel.ItemEntryObject;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<ItemEntryObject> {

    Context context;
    List<ItemEntryObject> items;

    public ItemAdapter(@NonNull Context context, List<ItemEntryObject> items) {
        super(context, R.layout.single_item, items);
        this.context = context;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ItemEntryObject item = getItem(position);

        if( convertView == null ){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.single_item, parent, false);
        }

        TextView amount       = convertView.findViewById(R.id.amountView);
        TextView itemUnit     = convertView.findViewById(R.id.unitView);
        TextView itemName     = convertView.findViewById(R.id.nameView);
        TextView itemCalories = convertView.findViewById(R.id.caloriesView);

        amount.setText(  String.valueOf( item.getAmount() )  );
        itemUnit.setText( item.getMeasureBy().equals("Hand") ? " " : item.getMeasureBy() );
        itemName.setText(item.getItemName());
        itemCalories.setText(  String.valueOf( item.getCalories() )  + " calories");

        return convertView;
    }
}
