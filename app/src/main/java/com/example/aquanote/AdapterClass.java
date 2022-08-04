package com.example.aquanote;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyViewHolder> {

    private ArrayList<Value> valueList;
    public AdapterClass(ArrayList<Value> valueList){
        this.valueList = valueList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView value;
        private TextView date;
        private Button buttonSelectEntry;

        public MyViewHolder(final View view){
            super(view);
            value = view.findViewById(R.id.textListValue);
            date = view.findViewById(R.id.textListDate);
            buttonSelectEntry = view.findViewById(R.id.buttonSelectEntry);
        }

    }

    @NonNull
    @Override
    public AdapterClass.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterClass.MyViewHolder holder, int position) {
        float value = valueList.get(position).getValueNumber();
        String date = valueList.get(position).getDate();
        int id = valueList.get(position).getId();
        holder.value.setText(String.valueOf(value));
        holder.date.setText(date);
        holder.buttonSelectEntry.setText(String.valueOf(id));

    }

    @Override
    public int getItemCount() {
        return valueList.size();
    }

}


