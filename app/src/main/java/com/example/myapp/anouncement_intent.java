package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.myapp.Models.Anouncements;
import com.example.myapp.databinding.ActivityAdminProfileBinding;
import com.example.myapp.databinding.ActivityAnouncementIntentBinding;

public class anouncement_intent extends AppCompatActivity {
    Anouncements models;
    ActivityAnouncementIntentBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_anouncement_intent);

        binding = ActivityAnouncementIntentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

         models = (Anouncements) getIntent().getSerializableExtra("models");


        Glide.with(getApplicationContext())
                .load(models.getImage_uri())
                .into(binding.imageView);

        binding.t1.setText(models.getWhat());

        binding.t2.setText(models.getHow());

        binding.t3.setText(models.getWhen());

        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });



    }
}