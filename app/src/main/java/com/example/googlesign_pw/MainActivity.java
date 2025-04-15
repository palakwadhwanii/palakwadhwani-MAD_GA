package com.example.photogalleryapp;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST_CODE = 1;
    private static final int SELECT_FOLDER_REQUEST_CODE = 2;

    private Button takePhotoButton, selectFolderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        takePhotoButton = findViewById(R.id.takePhotoButton);
        selectFolderButton = findViewById(R.id.selectFolderButton);

        takePhotoButton.setOnClickListener(v -> takePhoto());
        selectFolderButton.setOnClickListener(v -> openFolderSelector());
    }

    private void takePhoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
        }
    }

    private void openFolderSelector() {
        Intent intent = new Intent(this, ImageGalleryActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            // Save image or handle as needed
            Toast.makeText(this, "Photo taken successfully!", Toast.LENGTH_SHORT).show();
        }
    }
}
