package com.example.photogalleryapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class ImageDetailsActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView imageName, imagePath, imageSize, imageDate;
    private Button deleteButton;
    private File imageFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);

        imageView = findViewById(R.id.imageView);
        imageName = findViewById(R.id.imageName);
        imagePath = findViewById(R.id.imagePath);
        imageSize = findViewById(R.id.imageSize);
        imageDate = findViewById(R.id.imageDate);
        deleteButton = findViewById(R.id.deleteButton);

        String path = getIntent().getStringExtra("image_path");
        imageFile = new File(path);

        // Set image details
        imageView.setImageURI(android.net.Uri.fromFile(imageFile));
        imageName.setText("Name: " + imageFile.getName());
        imagePath.setText("Path: " + imageFile.getAbsolutePath());
        imageSize.setText("Size: " + imageFile.length() / 1024 + " KB");
        imageDate.setText("Date: " + new java.util.Date(imageFile.lastModified()).toString());

        deleteButton.setOnClickListener(v -> showDeleteConfirmationDialog());
    }

    private void showDeleteConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to delete this image?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    if (imageFile.delete()) {
                        Toast.makeText(this, "Image deleted", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(this, "Failed to delete image", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}
