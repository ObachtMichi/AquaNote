package com.example.aquanote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private EditText nameAquaInput;
    private EditText volumeAquaInput;
    private Button buttonSelectValue;
    private ImageButton addPhotoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameAquaInput = (EditText) findViewById(R.id.nameAquaInput);
        volumeAquaInput = (EditText) findViewById(R.id.volumeAquaInput);
        buttonSelectValue = (Button) findViewById(R.id.buttonSelectValue);
        addPhotoButton = (ImageButton) findViewById(R.id.addPhotoButton);

        addPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 3);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && data != null){
            Uri selectedImage = data.getData();
            addPhotoButton.setImageURI(selectedImage);
        }
    }

    public void nextActivity(View v){
        Intent i = new Intent(this, SelectValuesStart.class);
        startActivity(i);
    }
}
