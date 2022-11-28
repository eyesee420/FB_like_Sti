package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.myapp.Adapters.anouncement_adapter;
import com.example.myapp.Adapters.event_adapter;
import com.example.myapp.Models.Anouncements;
import com.example.myapp.Models.Events;
import com.example.myapp.databinding.ActivityStudentAnouncementsBottomNavBinding;
import com.example.myapp.databinding.ActivityStudentEventsBottomNavBinding;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class Student_Anouncements_bottom_nav extends AppCompatActivity {
    private anouncement_adapter adapter;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
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
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {


        Query query = db.collection("anouncements");

        //  Query query1 =db.collection("shops and products").getFirestore().collectionGroup("my shops");

        FirestoreRecyclerOptions<Anouncements> options = new FirestoreRecyclerOptions.Builder<Anouncements>()
                .setQuery(query, Anouncements.class).build();

        adapter = new anouncement_adapter(options);
        binding.recyclerview.setHasFixedSize(true);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.recyclerview.setAdapter(adapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}