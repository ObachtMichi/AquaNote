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

    private LinearLayout[] arrLayout = new LinearLayout[11];

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

    private TextView[] arrText = new TextView[11];



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
        fillList();
    }

    private void fillList (){
        int t = 0;
        for (int i = 0; i < checkBox.length; i++) {
            if (checkBox[i].isChecked()){
                arrText[t].setText(checkBox[i].getText());
                arrLayout[t].setVisibility(View.VISIBLE);
                t++;
            }
        }
    }

    private void initVar(){
        arrLayout[0]=(LinearLayout)findViewById(R.id.layout1);
        arrLayout[1]=(LinearLayout)findViewById(R.id.layout2);
        arrLayout[2]=(LinearLayout)findViewById(R.id.layout3);
        arrLayout[3]=(LinearLayout)findViewById(R.id.layout4);
        arrLayout[4]=(LinearLayout)findViewById(R.id.layout5);
        arrLayout[5]=(LinearLayout)findViewById(R.id.layout6);
        arrLayout[6]=(LinearLayout)findViewById(R.id.layout7);
        arrLayout[7]=(LinearLayout)findViewById(R.id.layout8);
        arrLayout[8]=(LinearLayout)findViewById(R.id.layout9);
        arrLayout[9]=(LinearLayout)findViewById(R.id.layout10);
        arrLayout[10]=(LinearLayout)findViewById(R.id.layout11);

        arrText[0]=(TextView) findViewById(R.id.value1);
        arrText[1]=(TextView) findViewById(R.id.value2);
        arrText[2]=(TextView) findViewById(R.id.value3);
        arrText[3]=(TextView) findViewById(R.id.value4);
        arrText[4]=(TextView) findViewById(R.id.value5);
        arrText[5]=(TextView) findViewById(R.id.value6);
        arrText[6]=(TextView) findViewById(R.id.value7);
        arrText[7]=(TextView) findViewById(R.id.value8);
        arrText[8]=(TextView) findViewById(R.id.value9);
        arrText[9]=(TextView) findViewById(R.id.value10);
        arrText[10]=(TextView) findViewById(R.id.value11);



    }
}