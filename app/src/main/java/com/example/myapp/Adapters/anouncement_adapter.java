package com.example.myapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapp.Models.Anouncements;
import com.example.myapp.Models.Events;
import com.example.myapp.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class anouncement_adapter extends FirestoreRecyclerAdapter<Anouncements, anouncement_adapter.holder> {
    public anouncement_adapter(@NonNull FirestoreRecyclerOptions<Anouncements> options) {
        super(options);
    }
    @Override
    protected void onBindViewHolder(@NonNull anouncement_adapter.holder holder, int i, @NonNull Anouncements model) {
        holder.bind(model);
    }
    @NonNull
    @Override
    public anouncement_adapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_anouncement, parent, false);
        return new anouncement_adapter.holder(view);
    }
    public class holder extends RecyclerView.ViewHolder {
        Context context;
        ImageView image_view;
        TextView what , when ,how, date_time;
        public holder(@NonNull View itemView) {
            super(itemView);
            context =itemView.getContext();
            image_view = itemView.findViewById(R.id.image_view);
            what = itemView.findViewById(R.id.announcement_what);
            when = itemView.findViewById(R.id.announcement_when);
            how = itemView.findViewById(R.id.announcement_how);
            date_time = itemView.findViewById(R.id.announcement_date_time);

        }
        public void bind(Anouncements model) {
            Glide.with(context.getApplicationContext())
                    .load(model.getImage_uri())
                    .into(image_view);
            what.setText(String.format("WHAT: %s", model.getWhat()));
            when.setText(String.format("WHEN: %s",model.getWhen()));
            how.setText(String.format("HOW: %s", model.getHow()));
            date_time.setText(String.format("Posted at %s", model.getDate_time()));
        }
    }
}
