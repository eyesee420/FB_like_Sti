package com.example.myapp.Adapters;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapp.Models.post_feed;
import com.example.myapp.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class post_feed_adapter extends FirestoreRecyclerAdapter<post_feed, post_feed_adapter.holder> {

    public post_feed_adapter(@NonNull FirestoreRecyclerOptions<post_feed> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull post_feed_adapter.holder holder, int position, @NonNull post_feed model) {
        holder.bind(model);

    }

    @NonNull
    @Override
    public post_feed_adapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_feed, parent, false);

        return new post_feed_adapter.holder(view);
    }

    public class holder extends RecyclerView.ViewHolder {
        Context context;
        ImageView image_view_pic, comment , send;
        EditText comment_text;
        TextView post_text ,date_time;


        public holder(@NonNull View itemView) {
            super(itemView);
            context =itemView.getContext();
            image_view_pic = itemView.findViewById(R.id.image_view_pic);
            post_text = itemView.findViewById(R.id.post_text);
            date_time = itemView.findViewById(R.id.date_time);
            comment = itemView.findViewById(R.id.comment);
            send = itemView.findViewById(R.id.send);
            comment_text = itemView.findViewById(R.id.comment_text);

            comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    send.setVisibility(View.VISIBLE);
                    comment_text.setVisibility(View.VISIBLE);
                }
            });

        }


        public void bind(post_feed model) {
            Glide.with(context.getApplicationContext())
                    .load(model.getImage_uri())
                    .into(image_view_pic);
            post_text.setText(model.getPost_text());
            date_time.setText(model.getDatetime());

        }
    }
}
