package com.sabithpkcmnr.unsplashjson;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.switchmaterial.SwitchMaterial;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ActivityHome extends AppCompatActivity {

    int limitPage = 0;  //Page count on single fetch
    int limitImage = 9; //Total image to fetch on single try
    boolean canLoadAgain;

    RecyclerView itemList;
    AdapterList adapterList;
    ProgressBar itemProgress;
    SwitchMaterial itemSwitch;
    NestedScrollView itemNested;
    ArrayList<ModelList> modelList = new ArrayList<>();

    String imageDataUrlOne = "https://picsum.photos/v2/list?page=";
    String imageDataUrlTwo = "&limit=" + limitImage;
    String imageDataUrlFinal = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        itemList = findViewById(R.id.itemList);
        itemNested = findViewById(R.id.itemNested);
        itemSwitch = findViewById(R.id.itemSwitch);
        itemProgress = findViewById(R.id.itemProgress);

        itemSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        changeListViewType(isChecked);
                    }
                }, 260);
            }
        });

        adapterList = new AdapterList(this, modelList, true);

        itemList.setHasFixedSize(true);
        itemList.setNestedScrollingEnabled(false);
        itemList.setLayoutManager(new GridLayoutManager(this, 3));
        itemList.setAdapter(adapterList);

        getImageJsonData();

        itemNested.getViewTreeObserver().addOnScrollChangedListener(() -> {
            View view1 = itemNested.getChildAt(itemNested.getChildCount() - 1);
            int diff = (view1.getBottom() - (itemNested.getHeight() + itemNested.getScrollY()));
            if (diff == 0 && canLoadAgain) {
                getImageJsonData();
            }
        });
    }

    private void getImageJsonData() {
        limitPage++;
        canLoadAgain = false;
        imageDataUrlFinal = imageDataUrlOne + limitPage + imageDataUrlTwo;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, imageDataUrlFinal,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                ModelList itemSingle = new ModelList();
                                JSONObject jsonList = jsonArray.getJSONObject(i);
                                itemSingle.setId(jsonList.getInt("id"));
                                itemSingle.setWidth(jsonList.getInt("width"));
                                itemSingle.setHeight(jsonList.getInt("height"));
                                itemSingle.setImageUrl(jsonList.getString("url"));
                                itemSingle.setAuthor(jsonList.getString("author"));
                                itemSingle.setDownloadUrl(jsonList.getString("download_url"));
                                modelList.add(itemSingle);
                            }
                            adapterList.notifyDataSetChanged();
                            canLoadAgain = true;

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ActivityHome.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void changeListViewType(boolean showGrid) {
        adapterList = new AdapterList(this, modelList, showGrid);
        itemList.setLayoutManager(showGrid ? new GridLayoutManager(this, 3) :
                new LinearLayoutManager(this));
        itemList.setAdapter(adapterList);
    }

}