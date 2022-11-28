package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import com.example.myapp.Models.Events;
import com.example.myapp.Models.post_feed;
import com.example.myapp.databinding.ActivityAdminAddFeedBinding;
import com.example.myapp.databinding.ActivityAdminHomeBinding;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Admin_add_feed extends AppCompatActivity {
    ImagePicker imagePicker;
    Uri uri;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    StorageReference reference = FirebaseStorage.getInstance().getReference();
    ActivityAdminAddFeedBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_admin_add_feed);
        binding = ActivityAdminAddFeedBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        binding.btnChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagePicker.Companion.with(Admin_add_feed.this)
                        // .crop()                    //Crop image(Optional), Check Customization for more option
                        // .compress(1024)            //Final image size will be less than 1 MB(Optional)
                        //   .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });
        binding.btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (uri != null) {
                    // progressBar.setVisibility(View.VISIBLE);
                    uploadtofirebase(uri);

                } else {
                    Toast.makeText(Admin_add_feed.this, "Please select image", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void uploadtofirebase(Uri uri) {
        StorageReference fileRef = reference.child(System.currentTimeMillis() + "." + getFileExtension(uri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        Calendar calendar = Calendar.getInstance();
                        SimpleDateFormat currentDate = new SimpleDateFormat("dd/LLL/yyyy");
                        String date = currentDate.format(calendar.getTime());
                        SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm:ss a");
                        String time = currentTime.format(calendar.getTime());
                        String mytime = date + " " + time;

                        String post_text=  binding.postText.getText().toString().trim();

                        String post_id = db.collection("post").document().getId();

                        post_feed postFeed_model = new post_feed(uri.toString(),post_text,mytime,post_id);



                        //  FirebaseUser userid = mAuth.getCurrentUser();
                        // String idd = db.collection("shop and products").document(userid.getUid()).collection("my shops").getId();
                        db.collection("post").document(post_id)
                                .set(postFeed_model).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                    }
                                });


                        //progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(Admin_add_feed.this, "uploaded", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
    private String getFileExtension(Uri muri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(muri));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        uri = data.getData();
        binding.imageView.setImageURI(uri);


    }
}
