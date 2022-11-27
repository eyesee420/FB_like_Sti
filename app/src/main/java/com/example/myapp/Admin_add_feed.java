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

import java.util.ArrayList;
import java.util.HashMap;

public class Admin_add_feed extends AppCompatActivity {
    private Uri ImageUri;
    ArrayList ImageList = new ArrayList();
    ArrayList urlStrings;
    private int upload_count = 0;


    ImagePicker imagePicker;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    StorageReference reference = FirebaseStorage.getInstance().getReference();

    post_feed post_feed;

    ArrayList<Uri> uri = new ArrayList<>();

    private static final int PICK_IMAGE = 1;
    grid_images_adapter adapter;
    private static final String TAG = "Admin_add_feed";
    private  static  final int read_permission = 101;


    ActivityAdminAddFeedBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_admin_add_feed);
        binding = ActivityAdminAddFeedBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        adapter = new grid_images_adapter(uri);
        binding.recyclerview.setLayoutManager(new GridLayoutManager(Admin_add_feed.this,4));
        binding.recyclerview.setAdapter(adapter);

//        if(ContextCompat.checkSelfPermission(Admin_add_feed.this, Manifest.permission.READ_EXTERNAL_STORAGE)
//        != PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(Admin_add_feed.this, new String[]
//                            {Manifest.permission.READ_EXTERNAL_STORAGE},read_permission);
//        }


//        binding.updloadImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//            }
//        });
        binding.btnChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                startActivityForResult(intent, PICK_IMAGE);

            }
        });

        binding.btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                urlStrings = new ArrayList<>();
//                progressDialog.show();
//                alert.setText("If Loading Takes to long press button again");
                StorageReference ImageFolder = FirebaseStorage.getInstance().getReference().child("ImageFolder");

                for (upload_count = 0; upload_count < ImageList.size(); upload_count++) {

                    Uri IndividualImage = (Uri) ImageList.get(upload_count);
                    final StorageReference ImageName = ImageFolder.child("Images" +
                            IndividualImage.getLastPathSegment());

                    ImageName.putFile(IndividualImage).addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    ImageName.getDownloadUrl().addOnSuccessListener(
                                            new OnSuccessListener<Uri>() {
                                                @Override
                                                public void onSuccess(Uri uri) {
                                                    urlStrings.add(String.valueOf(uri));



                                                    if (urlStrings.size() == ImageList.size()){
                                                        storeLink(urlStrings);
                                                    }

                                                }
                                            }
                                    );
                                }
                            }
                    );


                }


            }
        });



    }

    private void storeLink(ArrayList urlStrings) {

        HashMap<String, String> hashMap = new HashMap<>();

        for (int i = 0; i <urlStrings.size() ; i++) {
            hashMap.put("ImgLink"+i, String.valueOf(urlStrings.get(i)));


            post_feed.setImage_uri( String.valueOf(urlStrings.get(i)));

        }

        post_feed = new post_feed(post_feed.getImage_uri());

        db.collection("post").document("asd").set(post_feed)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Admin_add_feed.this, "Successfully Uplosded", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Admin_add_feed.this, "failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

       // progressDialog.dismiss();
       // alert.setText("Uploaded Successfully");
        Toast.makeText(this, "Uploaded Successfully", Toast.LENGTH_SHORT).show();
       // uploaderBtn.setVisibility(View.GONE);

        ImageList.clear();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE) {
            if (resultCode == RESULT_OK) {


                if (data.getClipData() != null) {

                    int countClipData = data.getClipData().getItemCount();
                    int currentImageSlect = 0;

                    while (currentImageSlect < countClipData) {

                        ImageUri = data.getClipData().getItemAt(currentImageSlect).getUri();
                        ImageList.add(ImageUri);
                        currentImageSlect = currentImageSlect + 1;
                    }

//                    alert.setVisibility(View.VISIBLE);
//                    alert.setText("You have selected" + ImageList.size() + "Images");
//                    chooserBtn.setVisibility(View.GONE);


                } else {
                    Toast.makeText(this, "Please Select Multiple Images", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }
}
