package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.myapp.Adapters.event_adapter;
import com.example.myapp.Adapters.post_feed_adapter;
import com.example.myapp.Models.Events;
import com.example.myapp.Models.post_feed;
import com.example.myapp.databinding.ActivityAdminEventsBinding;
import com.example.myapp.databinding.ActivityAdminHomeBinding;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class Admin_Events extends AppCompatActivity {
        private event_adapter adapter;
        private FirebaseFirestore db = FirebaseFirestore.getInstance();
    ActivityAdminEventsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_admin_events);

        binding = ActivityAdminEventsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        binding.bottomNavigationAdmin.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(), Admin_home.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;

                    case R.id.nav_events:

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

        setUpRecyclerView();

        binding.addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Admin_Events.this , Admin_add_events.class));
            }
        });
    }

    private void setUpRecyclerView() {

        Query query = db.collection("Events");

        //  Query query1 =db.collection("shops and products").getFirestore().collectionGroup("my shops");

        FirestoreRecyclerOptions<Events> options = new FirestoreRecyclerOptions.Builder<Events>()
                .setQuery(query, Events.class).build();



        adapter = new event_adapter(options);
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