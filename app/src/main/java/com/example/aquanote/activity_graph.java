package com.example.aquanote;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
    private Button btn_AddEntry;

    //-----------------------------------------Variablen---------------------------------------------------


    //-----------------------------------------On Create---------------------------------------------------
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        btn_AddEntry = findViewById(R.id.btn_AddEntry);
        btn_AddEntry();
        dataBaseHelper = new DataBaseHelper(activity_graph.this, activity_home_screen.getDBName());
        dropDownMenu();

        //--------RecyclerView--------
        recyclerView = findViewById(R.id.recyclerView);
        valueList = new ArrayList<>();
        setValueInfo();
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
                        intentSettings.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intentSettings);
                        break;
                    case R.id.graph:
                        break;
                    case R.id.homeScreen:
                        Intent intentHome = new Intent(activity_graph.this, activity_home_screen.class);
                        intentHome.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
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

    public void changeValue(Value value){
        dataBaseHelper.changeEntry(value);
    }


    private void btn_AddEntry(){
        btn_AddEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialogAddNew = new Dialog(view.getContext());
                dialogAddNew.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialogAddNew.setCancelable(true);
                dialogAddNew.setContentView(R.layout.add_new_entry);
                dialogAddNew.show();

                EditText addValue = dialogAddNew.findViewById(R.id.addValue);
                Button btn_NewCancel = dialogAddNew.findViewById(R.id.btn_NewCancel);
                Button btn_NewAccept = dialogAddNew.findViewById(R.id.btn_NewAccept);

                btn_NewCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogAddNew.dismiss();
                    }
                });

                btn_NewAccept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!(addValue.getText().toString().equals("") && addValue.getText().toString().equals("."))){
                            Value newValue = new Value(currentValue, Float.parseFloat(addValue.getText().toString()));
                            dataBaseHelper.addEntry(newValue);
                            finish();
                            startActivity(getIntent());
                        } else{
                            Toast.makeText(activity_graph.this, "Not Valid", Toast.LENGTH_SHORT).show();
                        }

                    }
                });



            }
        });
    }
}