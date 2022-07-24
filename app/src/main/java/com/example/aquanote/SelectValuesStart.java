package com.example.aquanote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class SelectValuesStart extends AppCompatActivity {

    private Button addCustomValue;
    private CheckBox customOne;
    private CheckBox customTwo;
    private CheckBox customThree;
    private CheckBox customFour;
    private CheckBox customFive;
    private CheckBox customSix;
    private CheckBox customSeven;
    private CheckBox tmp = customOne;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_values_start);
        addCustomValue = (Button) findViewById(R.id.addCustomValue);
        customOne = (CheckBox) findViewById(R.id.customOne);
        customTwo = (CheckBox) findViewById(R.id.customTwo);
        customThree = (CheckBox) findViewById(R.id.customThree);
        customFour = (CheckBox) findViewById(R.id.customFour);
        customFive = (CheckBox) findViewById(R.id.customFive);
        customSix = (CheckBox) findViewById(R.id.customSix);
        customSeven = (CheckBox) findViewById(R.id.customSeven);
        addCustomValueButtonAction();

    }


    public void addCustomValueButtonAction(){ //TODO Auflistung von oben nach unten weiterer CustomValues

                addCustomValue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        customOne.setAlpha(1);
                        customOne.setClickable(true);
                    }
                });;

                addCustomValue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        customTwo.setAlpha(1);
                        customTwo.setClickable(true);
                    }
                });;

        addCustomValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customThree.setAlpha(1);
                customThree.setClickable(true);
            }
        });;

    }
}