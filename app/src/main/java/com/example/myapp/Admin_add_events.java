package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import com.example.myapp.Models.Events;
import com.example.myapp.databinding.ActivityAdminAddEventsBinding;
import com.example.myapp.databinding.ActivityStudentHomeBottomNavBinding;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Admin_add_events extends AppCompatActivity {
    Uri uri;
    ImagePicker imagePicker;
    ActivityAdminAddEventsBinding binding;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    StorageReference reference = FirebaseStorage.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_events);

        binding = ActivityAdminAddEventsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        binding.btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagePicker.Companion.with(Admin_add_events.this)
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
                    Toast.makeText(Admin_add_events.this, "Please select image", Toast.LENGTH_SHORT).show();
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

                      String tittle=   binding.tittle.getText().toString().trim();
                      String discription = binding.Discription.getText().toString().trim();


                        String event_Id = db.collection("Events").document().getId();

                        Events events_model = new Events (uri.toString(),tittle,discription,mytime,event_Id);


                      //  FirebaseUser userid = mAuth.getCurrentUser();
                        // String idd = db.collection("shop and products").document(userid.getUid()).collection("my shops").getId();
                        db.collection("Events").document(event_Id)
                                .set(events_model).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                    }
                                });


                        //progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(Admin_add_events.this, "uploaded", Toast.LENGTH_SHORT).show();

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