package com.sabithpkcmnr.unsplashjson;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.ArrayList;

public class AdapterList extends RecyclerView.Adapter<AdapterList.MyViewHolder> {

    Context context;
    boolean isGridType;
    ArrayList<ModelList> modelList;

    public AdapterList(Context context, ArrayList<ModelList> modelList, boolean isGridType) {
        this.isGridType = isGridType;
        this.modelList = modelList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterList.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
        return new MyViewHolder(layoutView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AdapterList.MyViewHolder holder, int position) {

        if (!isGridType){
            holder.itemId.setText("id :" + modelList.get(position).getId());
            holder.itemWidth.setText("width :" + modelList.get(position).getWidth());
            holder.itemAuthor.setText("author :" + modelList.get(position).getAuthor());
            holder.itemHeight.setText("height :" + modelList.get(position).getHeight());
            holder.itemImageUrl.setText("url :" + modelList.get(position).getImageUrl());
            holder.itemDownloadUrl.setText("download url :" + modelList.get(position).getDownloadUrl());

        } else {
            holder.itemContents.setVisibility(View.GONE);
        }

        Glide.with(context)
                .load(modelList.get(position).getDownloadUrl())
                .override(250, 250)
                .transition(new DrawableTransitionOptions().crossFade())
                .placeholder(R.drawable.placeholder_image_rec)
                .into(holder.itemImage);

        setScaleAnimation(holder.itemView);
    }

    private void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f,
                1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(500);
        view.startAnimation(anim);
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView itemImage;
        LinearLayout itemContents;
        TextView itemId, itemAuthor, itemWidth, itemHeight, itemDownloadUrl, itemImageUrl;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemId = itemView.findViewById(R.id.itemId);
            itemImage = itemView.findViewById(R.id.itemImage);
            itemWidth = itemView.findViewById(R.id.itemWidth);
            itemAuthor = itemView.findViewById(R.id.itemAuthor);
            itemHeight = itemView.findViewById(R.id.itemHeight);
            itemImageUrl = itemView.findViewById(R.id.itemImageUrl);
            itemContents = itemView.findViewById(R.id.itemContents);
            itemDownloadUrl = itemView.findViewById(R.id.itemDownloadUrl);
        }
    }
}