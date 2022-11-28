package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.myapp.Adapters.post_feed_adapter;
import com.example.myapp.Models.post_feed;
import com.example.myapp.databinding.ActivityAdminHomeBinding;
import com.example.myapp.databinding.ActivityStudentEventsBottomNavBinding;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class Admin_home extends AppCompatActivity {
    private post_feed_adapter adapter;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
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
                        startActivity(new Intent(getApplicationContext(), Admin_Events.class));
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


        setUpRecyclerView();

        binding.addfeedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Admin_home.this ,Admin_add_feed.class));
            }
        });
    }

    private void setUpRecyclerView() {


        Query query = db.collection("post");

        //  Query query1 =db.collection("shops and products").getFirestore().collectionGroup("my shops");

        FirestoreRecyclerOptions<post_feed> options = new FirestoreRecyclerOptions.Builder<post_feed>()
                .setQuery(query, post_feed.class).build();



        adapter = new post_feed_adapter(options);
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