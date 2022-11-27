package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.myapp.databinding.ActivityStudentAnouncementsBottomNavBinding;
import com.example.myapp.databinding.ActivityStudentEventsBottomNavBinding;
import com.google.android.material.navigation.NavigationBarView;

public class Student_Anouncements_bottom_nav extends AppCompatActivity {

    ActivityStudentAnouncementsBottomNavBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   setContentView(R.layout.activity_student_anouncements_bottom_nav);

        binding = ActivityStudentAnouncementsBottomNavBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.bottomNavigationUsers.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(), Student_Home_bottom_nav.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;

                    case R.id.nav_events:
                        startActivity(new Intent(getApplicationContext(), Student_Events_bottom_nav.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;

                    case R.id.nav_anouncements:
                        return true;

                    case R.id.nav_profile:
                        startActivity(new Intent(getApplicationContext(), Student_Profile_bottom_nav.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;
                }

                return false;
            }
        });
    }
}