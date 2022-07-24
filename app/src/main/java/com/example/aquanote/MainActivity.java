package com.example.aquanote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText nameAquaInput;
    private EditText volumeAquaInput;
    private Button buttonSelectValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameAquaInput = (EditText) findViewById(R.id.nameAquaInput);
        volumeAquaInput = (EditText) findViewById(R.id.volumeAquaInput);
        buttonSelectValue = (Button) findViewById(R.id.buttonSelectValue);

    }

    public void nextActivity(View v){
        Intent i = new Intent(this, SelectValuesStart.class);
        startActivity(i);
    }
}
