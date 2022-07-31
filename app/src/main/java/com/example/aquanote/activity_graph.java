package com.example.aquanote;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class activity_graph extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);



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


    @Override
    public void onBackPressed() {
        // Your not allowed to go back
    }


    private void fillDropDown(){

    }
}