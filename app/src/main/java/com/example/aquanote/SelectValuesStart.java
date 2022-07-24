package com.example.aquanote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class SelectValuesStart extends AppCompatActivity{

    private Button addCustomValue;
    private CheckBox customOne;
    private CheckBox customTwo;
    private CheckBox customThree;
    private CheckBox customFour;
    private CheckBox customFive;
    private CheckBox customSix;
    private CheckBox customSeven;
    private CheckBox customEight;
    private int i = 0;



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
        customEight = (CheckBox) findViewById(R.id.customEight);

        CheckBox[] arr = new CheckBox[8];
        arr[0] = customOne;
        arr[1] = customTwo;
        arr[2] = customThree;
        arr[3] = customFour;
        arr[4] = customFive;
        arr[5] = customSix;
        arr[6] = customSeven;
        arr[7] = customEight;


            addCustomValue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if( i < 7){
                        i++;
                        arr[i].setAlpha(1);
                        arr[i].setClickable(true);
                    }
                    }

            });




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