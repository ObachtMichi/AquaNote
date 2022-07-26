package com.example.aquanote;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class activity_home_screen extends AppCompatActivity {


    private ImageView imageAquariumHome;
    private TextView myImageViewText;
    private CheckBox[] checkBox;

    private LinearLayout layout1;
    private LinearLayout layout2;
    private LinearLayout layout3;
    private LinearLayout layout4;
    private LinearLayout layout5;
    private LinearLayout layout6;
    private LinearLayout layout7;
    private LinearLayout layout8;
    private LinearLayout layout9;
    private LinearLayout layout10;
    private LinearLayout layout11;

    private TextView value1;
    private TextView value2;
    private TextView value3;
    private TextView value4;
    private TextView value5;
    private TextView value6;
    private TextView value7;
    private TextView value8;
    private TextView value9;
    private TextView value10;
    private TextView value11;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        imageAquariumHome = (ImageView) findViewById(R.id.imageAquariumHome);
        myImageViewText = (TextView) findViewById(R.id.myImageViewText);
        initVar();
        imageAquariumHome.setImageURI(MainActivity.getPicture());
        myImageViewText.setText(MainActivity.getName());
        checkBox = SelectValuesStart.getCheckBox();
    }

    private void fillList (){

        for (int i = 0; i < checkBox.length; i++) {

            if (checkBox[i].isChecked()){
                value1.setText(checkBox[i].getText());
                layout1.setVisibility(View.VISIBLE);
            }
        }
    }

    private void initVar(){
        layout1 = (LinearLayout)findViewById(R.id.layout1);
        layout2 = (LinearLayout)findViewById(R.id.layout2);
        layout3 = (LinearLayout)findViewById(R.id.layout3);
        layout4 = (LinearLayout)findViewById(R.id.layout4);
        layout5 = (LinearLayout)findViewById(R.id.layout5);
        layout6 = (LinearLayout)findViewById(R.id.layout6);
        layout7 = (LinearLayout)findViewById(R.id.layout7);
        layout8 = (LinearLayout)findViewById(R.id.layout8);
        layout9 = (LinearLayout)findViewById(R.id.layout9);
        layout10 = (LinearLayout)findViewById(R.id.layout10);
        layout11 = (LinearLayout)findViewById(R.id.layout11);

        value1 = (TextView) findViewById(R.id.value1);
        value2 = (TextView) findViewById(R.id.value2);
        value3 = (TextView) findViewById(R.id.value3);
        value4 = (TextView) findViewById(R.id.value4);
        value5 = (TextView) findViewById(R.id.value5);
        value6 = (TextView) findViewById(R.id.value6);
        value7 = (TextView) findViewById(R.id.value7);
        value8 = (TextView) findViewById(R.id.value8);
        value9 = (TextView) findViewById(R.id.value9);
        value10 = (TextView) findViewById(R.id.value10);
        value11 = (TextView) findViewById(R.id.value11);

    }
}