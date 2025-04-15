package com.example.photogalleryapp;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;

public class ImageGalleryActivity extends AppCompatActivity {

    private GridView gridView;
    private ArrayList<File> imagesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_gallery);

        gridView = findViewById(R.id.gridView);
        imagesList = new ArrayList<>();

        // Assuming images are stored in a folder called "CameraPhotos"
        File directory = new File(getExternalFilesDir(null), "CameraPhotos");
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.getName().endsWith(".jpg") || file.getName().endsWith(".png")) {
                        imagesList.add(file);
                    }
                }
            }
        }

        ImageAdapter imageAdapter = new ImageAdapter(this, imagesList);
        gridView.setAdapter(imageAdapter);

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            File image = imagesList.get(position);
            Intent intent = new Intent(this, ImageDetailsActivity.class);
            intent.putExtra("image_path", image.getAbsolutePath());
            startActivity(intent);
        });
    }
}
