package com.example.aquanote;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


public class activity_home_screen extends AppCompatActivity {


    private ImageView imageAquariumHome;
    private TextView myImageViewText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        imageAquariumHome = (ImageView) findViewById(R.id.imageAquariumHome);
        myImageViewText = (TextView) findViewById(R.id.myImageViewText);

        imageAquariumHome.setImageURI(MainActivity.getPicture());
        myImageViewText.setText(MainActivity.getName());

    }
}