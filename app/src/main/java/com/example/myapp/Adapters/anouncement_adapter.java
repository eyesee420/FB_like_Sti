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
            what = itemView.findViewById(R.id.what);
            when = itemView.findViewById(R.id.when);
            how = itemView.findViewById(R.id.how);
            date_time = itemView.findViewById(R.id.date_time);

        }

        public void bind(Anouncements model) {

            Glide.with(context.getApplicationContext())
                    .load(model.getImage_uri())
                    .into(image_view);

            what.setText("WHAT ? " +model.getWhat());
            when.setText("HOW ? " +model.getWhen());
            how.setText("WHERE ?"+model.getHow());
            date_time.setText(model.getDate_time());


        }
    }
}
