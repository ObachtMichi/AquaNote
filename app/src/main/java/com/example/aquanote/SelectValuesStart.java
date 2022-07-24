package com.example.aquanote;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

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
    private TextView textCountLeft;
    EditText customName;
    private int i = 1;
    int counter = 5;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_values_start);
        addCustomValue = (Button) findViewById(R.id.addCustomValue);
        customTwo = (CheckBox) findViewById(R.id.customTwo);
        customThree = (CheckBox) findViewById(R.id.customThree);
        customFour = (CheckBox) findViewById(R.id.customFour);
        customFive = (CheckBox) findViewById(R.id.customFive);
        customSix = (CheckBox) findViewById(R.id.customSix);
        customSeven = (CheckBox) findViewById(R.id.customSeven);
        customEight = (CheckBox) findViewById(R.id.customEight);
        textCountLeft = (TextView)findViewById(R.id.textCountLeft);

        CheckBox[] arr = new CheckBox[8];
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
                                if(!customName.getText().toString().equals("")) {
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