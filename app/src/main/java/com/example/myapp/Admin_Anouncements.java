package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.myapp.Adapters.anouncement_adapter;
import com.example.myapp.Adapters.post_feed_adapter;
import com.example.myapp.Models.Anouncements;
import com.example.myapp.Models.post_feed;
import com.example.myapp.databinding.ActivityAdminAnouncementsBinding;
import com.example.myapp.databinding.ActivityAdminEventsBinding;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class Admin_Anouncements extends AppCompatActivity {
    private anouncement_adapter adapter;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    ActivityAdminAnouncementsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_admin_anouncements);



        binding = ActivityAdminAnouncementsBinding.inflate(getLayoutInflater());
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

                        startActivity(new Intent(getApplicationContext(), Admin_Events.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;

                    case R.id.nav_anouncements:

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
                startActivity(new Intent(Admin_Anouncements.this , Admin_add_Anouncements.class));
            }
        });

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