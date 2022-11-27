package com.example.myapp;

import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class grid_images_adapter extends RecyclerView.Adapter<grid_images_adapter.ViewHolder> {
    private ArrayList<Uri> uriArrayList;

    public grid_images_adapter(ArrayList<Uri>uriArrayList){
        this.uriArrayList = uriArrayList;
    }

    @NonNull
    @Override
    public grid_images_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custom_image,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull grid_images_adapter.ViewHolder holder, int position) {

        holder.imageView.setImageURI(uriArrayList.get(position));

    }

    @Override
    public int getItemCount() {
        return uriArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
        }
    }
}
