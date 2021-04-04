package com.sabithpkcmnr.unsplashjson;

import android.os.Bundle;
import android.view.Menu;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
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

    RecyclerView homeRv;
    AdapterList adapterList;
    ArrayList<ModelList> modelList = new ArrayList<>();
    String imageDataUrl = "https://picsum.photos/v2/list?page=1&limit=20";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        homeRv = findViewById(R.id.homeRv);
        adapterList = new AdapterList(this, modelList, false);

        homeRv.setHasFixedSize(true);
        homeRv.setNestedScrollingEnabled(true);
        homeRv.setLayoutManager(new LinearLayoutManager(this));
        homeRv.setAdapter(adapterList);

        getImageJsonData();
    }

    private void getImageJsonData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, imageDataUrl,
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_grid, menu);
        SwitchMaterial mySwitch = menu.findItem(R.id.menuSwitchList)
                .setActionView(R.layout.item_menu_switch)
                .getActionView().findViewById(R.id.switchList);

        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeListViewType(isChecked);
            }
        });
        return true;
    }

    private void changeListViewType(boolean showGrid){
        adapterList = new AdapterList(this, modelList, showGrid);

        if (showGrid){
            homeRv.setLayoutManager(new GridLayoutManager(this, 3));
            homeRv.setAdapter(adapterList);

        } else {
            homeRv.setLayoutManager(new LinearLayoutManager(this));
            homeRv.setAdapter(adapterList);
        }

    }

}