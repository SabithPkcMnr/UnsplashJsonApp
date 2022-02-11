package com.sabithpkcmnr.unsplashjson;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
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
                .addListener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Log.d("logActivityVieewer", "Failed - " + e.getMessage());
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        Log.d("logActivityVieewer", "Loaded");
                        return false;
                    }
                })
                .transition(new DrawableTransitionOptions().crossFade())
                .placeholder(R.drawable.placeholder_image_rec)
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