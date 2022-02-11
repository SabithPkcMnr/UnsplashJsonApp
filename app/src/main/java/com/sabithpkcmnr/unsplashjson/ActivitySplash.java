package com.sabithpkcmnr.unsplashjson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivitySplash extends AppCompatActivity {

    TextView itemTitle;
    ImageView itemImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        itemImage = findViewById(R.id.itemImage);
        itemTitle = findViewById(R.id.itemTitle);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Pair[] transitionList = new Pair[2];
                transitionList[0] = Pair.create(itemImage, "itemImage");
                transitionList[1] = Pair.create(itemTitle, "itemTitle");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(ActivitySplash.this, transitionList);
                startActivity(new Intent(ActivitySplash.this, ActivityHome.class),
                        options.toBundle());
                finishAffinity();
            }
        },1600);
    }

}