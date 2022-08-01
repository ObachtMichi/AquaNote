package com.example.aquanote;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private EditText nameAquaInput;
    private EditText volumeAquaInput;
    private Button buttonSelectValue;
    private ImageButton addPhotoButton;
    private TextView immNameTextField;
    public static String name;
    private static Uri selectedImage;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SKIP EVTL

        if (checkDBExist()) skipIfDBExists();

        nameAquaInput = (EditText) findViewById(R.id.nameAquaInput);
        volumeAquaInput = (EditText) findViewById(R.id.volumeAquaInput);
        buttonSelectValue = (Button) findViewById(R.id.buttonSelectValue);
        addPhotoButton = (ImageButton) findViewById(R.id.addPhotoButton);
        immNameTextField = (TextView) findViewById(R.id.immNameTextField);

        buttonSelectValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!nameAquaInput.getText().toString().equals("")){
                    name = nameAquaInput.getText().toString();
                    nextActivity();
                } else {
                    Animation shake = AnimationUtils.loadAnimation(MainActivity.this, R.anim.shake);
                    nameAquaInput.startAnimation(shake);
                    immNameTextField.startAnimation(shake);
                }
            }
        });

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
            selectedImage = data.getData();
            addPhotoButton.setImageURI(selectedImage);
        }
    }

    public static Uri getPicture(){
        return selectedImage;
    }

    public static String getName(){
        return name;
    }

    private void nextActivity(){
        Intent i = new Intent(this, SelectValuesStart.class);
        startActivity(i);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void skipIfDBExists(){
        Intent i = new Intent(this, activity_home_screen.class);
        startActivity(i);
    }

    private boolean checkDBExist(){
        File f = new File("/data/data/com.example.aquanote/databases");
        if (f.exists()) return true;
        return false;
    }

}
