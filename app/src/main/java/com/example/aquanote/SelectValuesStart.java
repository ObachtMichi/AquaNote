package com.example.aquanote;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


public class SelectValuesStart extends AppCompatActivity {

    //-----------------------------------------Variablen---------------------------------------------------

    private Button addCustomValue, buttonNextHome, buttonSelectAll;
    private CheckBox customOne,customTwo,customThree,customFour,customFive,customSix,customSeven,customEight,checkAlkalinity,checkCalcium,checkMagnesium,checkNitrate,checkPhosphate;
    private TextView textCountLeft,textSelOne;
    private EditText customName;
    private int i = 5;
    private int counter = 5;
    private static CheckBox[] arr;
    private int w = 0;
    //-----------------------------------------Variablen---------------------------------------------------



    //-----------------------------------------Variablen---------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_values_start);

        //-----------------------------------------INIT Variablen---------------------------------------------------

        buttonSelectAll = (Button) findViewById(R.id.buttonSelectAll);
        textSelOne = (TextView) findViewById(R.id.textSelOne);

        addCustomValue = (Button) findViewById(R.id.addCustomValue);
        buttonNextHome = (Button) findViewById(R.id.buttonNextHome);
        customTwo = (CheckBox) findViewById(R.id.customTwo);
        customThree = (CheckBox) findViewById(R.id.customThree);
        customFour = (CheckBox) findViewById(R.id.customFour);
        customFive = (CheckBox) findViewById(R.id.customFive);
        customSix = (CheckBox) findViewById(R.id.customSix);
        customSeven = (CheckBox) findViewById(R.id.customSeven);
        customEight = (CheckBox) findViewById(R.id.customEight);

        textCountLeft = (TextView) findViewById(R.id.textCountLeft);
        checkCalcium = (CheckBox) findViewById(R.id.checkCalcium);
        checkMagnesium = (CheckBox) findViewById(R.id.checkMagnesium);
        checkNitrate = (CheckBox) findViewById(R.id.checkNitrate);
        checkPhosphate = (CheckBox) findViewById(R.id.checkPhostphate);

        arr = new CheckBox[12];
        arr[0] = (CheckBox) findViewById(R.id.checkAlkalinity);
        arr[1] = checkCalcium;
        arr[2] = checkMagnesium;
        arr[3] = checkNitrate;
        arr[4] = checkPhosphate;
        arr[5] = customTwo;
        arr[6] = customThree;
        arr[7] = customFour;
        arr[8] = customFive;
        arr[9] = customSix;
        arr[10] = customSeven;
        arr[11] = customEight;
        //-----------------------------------------INIT Variablen---------------------------------------------------


        buttonSelectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(w == 0) {

                    for (int j = 0; j < arr.length-1; j++) {
                        if(!arr[j].getText().equals("X"))
                            arr[j].setChecked(true);
                    }
                    w = 1;
                }
                 else{
                    for (int k = 0; k < arr.length-1; k++) {
                        arr[k].setChecked(false);
                    }
                    w=0;
                }
            }
        });


        buttonNextHome.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                if(checkIfOneIsChecked()) {
                    nextActivity();
                } else{
                    textSelOne.setVisibility(View.VISIBLE);
                    Animation shake = AnimationUtils.loadAnimation(SelectValuesStart.this, R.anim.shake);
                    textSelOne.startAnimation(shake);
                }

            }
        });


        addCustomValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (i < 11) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(SelectValuesStart.this);
                    customName = new EditText(SelectValuesStart.this);
                    customName.setInputType(InputType.TYPE_CLASS_TEXT);
                    dialog.setView(customName);
                    dialog.setCancelable(true);
                    dialog.setTitle("Please enter a name");
                    dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int j) {
                            dialogInterface.cancel();
                        }
                    });
                    dialog.setPositiveButton("Set", new DialogInterface.OnClickListener() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onClick(DialogInterface dialogInterface, int j) {
                            if (!customName.getText().toString().equals("")) {
                                arr[i].setText(customName.getText().toString());
                                arr[i].setAlpha(1);
                                arr[i].setClickable(true);
                                arr[i].setChecked(true);
                                textCountLeft.setText(counter + " left");
                                counter--;
                                i++;
                            }

                        }
                    });
                    dialog.show();
                }
            }
        });
    }

    //-----------------------------------------On Create---------------------------------------------------


    public static CheckBox[] getCheckBox() {
        return arr;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void nextActivity() {
        Intent i = new Intent(this, activity_home_screen.class);
        startActivity(i);
    }


    private boolean checkIfOneIsChecked(){

        for (int j = 0; j < arr.length; j++) {

            if(arr[j].isChecked()){
                return true;
            }
        }
        return false;
    }
}