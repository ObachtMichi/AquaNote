package com.example.aquanote;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


import com.google.android.material.bottomnavigation.BottomNavigationView;


import java.util.ArrayList;
import java.util.List;

public class activity_graph extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    //-----------------------------------------Variablen---------------------------------------------------

    private Spinner spinnerDropDown;
    private static DataBaseHelper dataBaseHelper;
    private RecyclerView recyclerView;
    private ArrayList<Value> valueList;
    private static String currentValue;

    //-----------------------------------------Variablen---------------------------------------------------


    //-----------------------------------------On Create---------------------------------------------------
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        dataBaseHelper = new DataBaseHelper(activity_graph.this, activity_home_screen.getDBName());
        dropDownMenu();

        //--------RecyclerView--------
        recyclerView = findViewById(R.id.recyclerView);
        valueList = new ArrayList<>();
        setValueInfo();
        //setAdapter();
        //--------RecyclerView--------





        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.graph);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId()){
                    case R.id.settings:
                        Intent intentSettings = new Intent(activity_graph.this, activity_Settings.class);
                        startActivity(intentSettings);
                        break;
                    case R.id.graph:
                        break;
                    case R.id.homeScreen:
                        Intent intentHome = new Intent(activity_graph.this, activity_home_screen.class);
                        startActivity(intentHome);
                        break;
                }
                return true;
            }
        });
    }
    //-----------------------------------------On Create---------------------------------------------------


    @Override
    public void onBackPressed() {
        // Your not allowed to go back
    }

    private String[] listInStringArr(List<String> listString){
        String[] retString = new String[listString.size()];

        for (int i = 0; i < listString.size(); i++) {
            retString[i] = listString.get(i);
        }
        return retString;
    }

    private void dropDownMenu(){
        spinnerDropDown = (Spinner) findViewById(R.id.spinnerDropDown);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(activity_graph.this, R.layout.selected_item, listInStringArr(dataBaseHelper.getValueTypes()));
        adapter.setDropDownViewResource(R.layout.dropdown_item);
        spinnerDropDown.setAdapter(adapter);
        spinnerDropDown.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        currentValue = parent.getItemAtPosition(position).toString();
        setAdapter();
        //Toast.makeText(parent.getContext(), text , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    private void setValueInfo(){

    }

    private void setAdapter() {
        AdapterClass adapter = new AdapterClass(dataBaseHelper.getDateAndValue(currentValue));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }


    public void deleteEntry(Value value){
        dataBaseHelper.deleteEntry(value);
    }
}