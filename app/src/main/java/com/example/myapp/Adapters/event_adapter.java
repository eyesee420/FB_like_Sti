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
import com.example.myapp.Models.Events;
import com.example.myapp.Models.post_feed;
import com.example.myapp.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class  event_adapter extends FirestoreRecyclerAdapter<Events, event_adapter.holder> {

    public event_adapter(@NonNull FirestoreRecyclerOptions<Events> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull holder holder, int i, @NonNull Events model) {
        holder.bind(model);
    }


    @NonNull
    @Override
    public event_adapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_events, parent, false);

        return  new event_adapter.holder(view);

    }

    public class holder extends RecyclerView.ViewHolder {
        Context context;
        ImageView image_view;
        TextView titte , Discription ,date_time;


        public holder(@NonNull View itemView) {
            super(itemView);

            context =itemView.getContext();
            image_view = itemView.findViewById(R.id.image_view);
            titte = itemView.findViewById(R.id.titte);
            Discription = itemView.findViewById(R.id.Discription);
            date_time = itemView.findViewById(R.id.date_time);

        }

        public void bind(Events model) {
            Glide.with(context.getApplicationContext())
                    .load(model.getImage_uri())
                    .into(image_view);

            titte.setText(model.getTittle());
            Discription.setText(model.getDescriptions());
            date_time.setText(model.getDate_time());


        }
    }
}
