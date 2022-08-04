package com.example.aquanote;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@RequiresApi(api = Build.VERSION_CODES.O)
public class activity_home_screen extends AppCompatActivity implements TextWatcher {

    //-----------------------------------------Variablen---------------------------------------------------
    private ImageView imageAquariumHome;
    private Button addValueButton;
    private TextView myImageViewText;
    private CheckBox[] checkBox;
    private DataBaseHelper dataBaseHelper;
    private BottomNavigationView bottomNavigationView;
    private String[] val = {"", "", "", "", "", "", "", "", "", "", ""};
    private EditText[] arrEditText = new EditText[11];
    private static ArrayList<String> arrComplete = new ArrayList<String>();
    private LinearLayout[] arrLayout = new LinearLayout[11];
    private TextView textAddedSuc;
    private List<String> selValues;
    private TextView[] arrText = new TextView[11];

    //-----------------------------------------Variablen---------------------------------------------------


    //-----------------------------------------On Create---------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        //INIT Variablen
        dataBaseHelper = new DataBaseHelper(activity_home_screen.this, getDBName());
        imageAquariumHome = (ImageView) findViewById(R.id.imageAquariumHome);
        myImageViewText = (TextView) findViewById(R.id.myImageViewText);
        addValueButton = (Button) findViewById(R.id.addValueButton);
        myImageViewText.setText(getDBName());
        checkBox = SelectValuesStart.getCheckBox();
        selValues = dataBaseHelper.getValueTypes();
        textAddedSuc = (TextView) findViewById(R.id.textAddedSuc);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.homeScreen);


        //Startup Methoden
        saveImageToInternalStorage(MainActivity.getPicture());
        initVar();
        setImageAquariumHome();
        fillList();
        addValueToTyp();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.settings:
                        nextSettings();

                        break;
                    case R.id.graph:
                        nextGraph();
                        break;
                    case R.id.homeScreen:
                        break;
                }
                return true;
            }
        });


    }
    //-----------------------------------------On Create---------------------------------------------------


    private void fillList() {

        int counter = selValues.size();

        for (int i = 0; i < counter; i++) {
            arrText[i].setText(selValues.get(i));
            arrLayout[i].setVisibility(View.VISIBLE);
            arrEditText[i].addTextChangedListener(this);
        }


    }

    private void initVar() {
        arrLayout[0] = (LinearLayout) findViewById(R.id.layout1);
        arrLayout[1] = (LinearLayout) findViewById(R.id.layout2);
        arrLayout[2] = (LinearLayout) findViewById(R.id.layout3);
        arrLayout[3] = (LinearLayout) findViewById(R.id.layout4);
        arrLayout[4] = (LinearLayout) findViewById(R.id.layout5);
        arrLayout[5] = (LinearLayout) findViewById(R.id.layout6);
        arrLayout[6] = (LinearLayout) findViewById(R.id.layout7);
        arrLayout[7] = (LinearLayout) findViewById(R.id.layout8);
        arrLayout[8] = (LinearLayout) findViewById(R.id.layout9);
        arrLayout[9] = (LinearLayout) findViewById(R.id.layout10);
        arrLayout[10] = (LinearLayout) findViewById(R.id.layout11);

        arrText[0] = (TextView) findViewById(R.id.value1);
        arrText[1] = (TextView) findViewById(R.id.value2);
        arrText[2] = (TextView) findViewById(R.id.value3);
        arrText[3] = (TextView) findViewById(R.id.value4);
        arrText[4] = (TextView) findViewById(R.id.value5);
        arrText[5] = (TextView) findViewById(R.id.value6);
        arrText[6] = (TextView) findViewById(R.id.value7);
        arrText[7] = (TextView) findViewById(R.id.value8);
        arrText[8] = (TextView) findViewById(R.id.value9);
        arrText[9] = (TextView) findViewById(R.id.value10);
        arrText[10] = (TextView) findViewById(R.id.value11);

        arrEditText[0] = (EditText) findViewById(R.id.editText1);
        arrEditText[1] = (EditText) findViewById(R.id.editText2);
        arrEditText[2] = (EditText) findViewById(R.id.editText3);
        arrEditText[3] = (EditText) findViewById(R.id.editText4);
        arrEditText[4] = (EditText) findViewById(R.id.editText5);
        arrEditText[5] = (EditText) findViewById(R.id.editText6);
        arrEditText[6] = (EditText) findViewById(R.id.editText7);
        arrEditText[7] = (EditText) findViewById(R.id.editText8);
        arrEditText[8] = (EditText) findViewById(R.id.editText9);
        arrEditText[9] = (EditText) findViewById(R.id.editText10);
        arrEditText[10] = (EditText) findViewById(R.id.editText11);


    }

    private void addValueToTyp() {
        addValueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Value value;
                LocalDate localDate = LocalDate.now();
                for (int i = 0; i < arrText.length; i++) {
                    if (!val[i].toString().equals("")) {

                        try {
                            float tmp = Float.parseFloat(val[i]);

                            //Objekt für Datenbank
                            value = new Value(arrText[i].getText().toString(), tmp);

                            DataBaseHelper dataBaseHelper = new DataBaseHelper(activity_home_screen.this, getDBName());
                            boolean success = dataBaseHelper.addValueToType(value);

                            arrComplete.add(arrText[i].getText().toString() + "," + val[i] + "," + localDate);
                            textAddedSuc.setText("Added Succesfully");
                            textAddedSuc.setVisibility(View.VISIBLE);

                        } catch (Exception e) {
                            textAddedSuc.setVisibility(View.VISIBLE);
                            textAddedSuc.setText("Not valid");
                            System.out.println("NOT VALID");
                        }
                    }
                    //}
                }
                System.out.println(arrComplete.toString());
            }
        });
    }

    @Override
    public void onBackPressed() {
        // Your not allowed to go back
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void nextSettings() {
        Intent i = new Intent(this, activity_Settings.class);
        startActivity(i);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void nextGraph() {
        Intent i = new Intent(this, activity_graph.class);
        startActivity(i);
    }


    public static ArrayList<String> getListComplete() {
        return arrComplete;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        //Toast.makeText(this,"before", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        //Toast.makeText(this,"on", Toast.LENGTH_LONG).show();


    }

    @Override
    public void afterTextChanged(Editable editable) {
        //Toast.makeText(this,editable.toString(), Toast.LENGTH_LONG).show();

        for (int i = 0; i < arrEditText.length; i++) {
            if (arrEditText[i].getText().hashCode() == editable.hashCode()) {
                val[i] = editable.toString();
            }
        }
    }

    public static String getDBName(){

        if (MainActivity.getName()==null){
            File f = new File("/data/data/com.example.aquanote/databases/");
            File[] files = f.listFiles();
            String[] ret = files[0].toString().split("/");

            return ret[5];
        } else{
            return MainActivity.getName();
        }
    }

    private void setImageAquariumHome(){
        try{
            Bitmap bitmap = BitmapFactory.decodeFile("/data/data/com.example.aquanote/files/aquariumPicture.png");
            imageAquariumHome.setImageBitmap(bitmap);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    public void saveImageToInternalStorage(Bitmap image) {

        if (image == null){
            return;
        }

        try {
            Bitmap smallBmp = ImageResizer.reduceBitmapSize(image, 240000);

            FileOutputStream fos = openFileOutput("aquariumPicture.png", Context.MODE_PRIVATE);

            smallBmp.compress(Bitmap.CompressFormat.PNG, 0, fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


