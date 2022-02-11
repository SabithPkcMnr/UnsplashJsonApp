package com.sabithpkcmnr.unsplashjson;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.github.chrisbanes.photoview.PhotoView;

public class ActivityViewer extends AppCompatActivity {

    String imageUrl;
    PhotoView itemImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewer);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageUrl = getIntent().getStringExtra("imageUrl");
        itemImage = findViewById(R.id.itemImage);

        Log.d("logActivityVieewer", "URL: " + imageUrl);

        Glide.with(this)
                .load(imageUrl)
                .into(itemImage);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}