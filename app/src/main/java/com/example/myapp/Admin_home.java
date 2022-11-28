package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.myapp.databinding.ActivityAdminHomeBinding;
import com.example.myapp.databinding.ActivityStudentEventsBottomNavBinding;
import com.google.android.material.navigation.NavigationBarView;

public class Admin_home extends AppCompatActivity {

    ActivityAdminHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_admin_home);

        binding = ActivityAdminHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.bottomNavigationAdmin.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_home:

                        return true;

                    case R.id.nav_events:
                        startActivity(new Intent(getApplicationContext(), Admin_add_events.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;

                    case R.id.nav_anouncements:
                        startActivity(new Intent(getApplicationContext(), Admin_Anouncements.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;

                    case R.id.nav_profile:
                        startActivity(new Intent(getApplicationContext(), Admin_Profile.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;
                }
                return false;
            }
        });



        binding.addfeedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Admin_home.this ,Admin_add_feed.class));
            }
        });
    }
}