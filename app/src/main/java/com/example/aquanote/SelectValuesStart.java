package com.example.aquanote;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SelectValuesStart extends AppCompatActivity {

    private Button addCustomValue;
    private Button buttonNextHome;
    private CheckBox customOne;
    private CheckBox customTwo;
    private CheckBox customThree;
    private CheckBox customFour;
    private CheckBox customFive;
    private CheckBox customSix;
    private CheckBox customSeven;
    private CheckBox customEight;
    private CheckBox checkAlkalinity;
    private CheckBox checkCalcium;
    private CheckBox checkMagnesium;
    private CheckBox checkNitrate;
    private CheckBox checkPhosphate;
    private TextView textCountLeft;
    private EditText customName;
    private int i = 5;
    private int counter = 5;
    private static CheckBox[] arr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_values_start);
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


        buttonNextHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextActivity();
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

    public static CheckBox[] getCheckBox() {
        return arr;
    }


    public void addCustomValueButtonAction() { //TODO Auflistung von oben nach unten weiterer CustomValues

        addCustomValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customOne.setAlpha(1);
                customOne.setClickable(true);
            }
        });
        ;

        addCustomValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customTwo.setAlpha(1);
                customTwo.setClickable(true);
            }
        });
        ;

        addCustomValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customThree.setAlpha(1);
                customThree.setClickable(true);
            }
        });
        ;

    }


    private void nextActivity() {
        Intent i = new Intent(this, activity_home_screen.class);
        startActivity(i);
    }

}