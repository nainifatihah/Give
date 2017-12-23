package com.ainifathiha.give;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView avatar;
    EditText userName;
    EditText userLocation;
    Button buttonUpdate;
    Button buttonLogOut;
    private Uri imgUri;
    private static final int CAMERA_REQUEST_CODE = 1;
    public static final int REQUEST_CODE = 1234;
    public static final String FB_STORAGE_PATH = "user/";
    public static final String FB_DATABASE_PATH = "user";
    DatabaseReference userDatabase;
    StorageReference userStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        userStorage = FirebaseStorage.getInstance().getReference();
        userDatabase = FirebaseDatabase.getInstance().getReference(FB_DATABASE_PATH);

        avatar = (ImageView) findViewById(R.id.ivAvatar);
        userName = (EditText) findViewById(R.id.etUsername);
        userLocation = (EditText) findViewById(R.id.etLocation);
        buttonUpdate = (Button) findViewById(R.id.bUpdate);
        buttonLogOut= (Button) findViewById(R.id.bLogOut);

        avatar.setOnClickListener(this);
        buttonUpdate.setOnClickListener(this);
        buttonLogOut.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null){
            imgUri = data.getData();

            try {
                Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(),imgUri);
                avatar.setImageBitmap(bm);
            } catch (FileNotFoundException e){
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }

        }
    }

    public String getImageExt(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    @SuppressWarnings("VisibleForTests")
    private void addUser(){

        if (imgUri != null){
            final ProgressDialog dialog = new ProgressDialog(this);
            dialog.setTitle("Updating profile");
            dialog.show();

            //get the storage reference
            StorageReference ref = userStorage.child(FB_STORAGE_PATH + System.currentTimeMillis() +"."+getImageExt(imgUri));

            //add file to reference
            ref.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Profile updated", Toast.LENGTH_SHORT).show();
                    Users userData = new Users(userName.getText().toString(), userLocation.getText().toString(), taskSnapshot.getDownloadUrl().toString());

                    //Save image info in database
                    String userId = userDatabase.push().getKey();
                    userDatabase.child(userId).setValue(userData);


                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            dialog.dismiss();
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {

                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            dialog.setMessage("Updated " + (int)progress+"%");
                        }
                    });
        } else {
            //Toast.makeText(getApplicationContext(), "Please select image", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        if (v == avatar){
            //Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //startActivityForResult(intent, CAMERA_REQUEST_CODE);
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select image"), REQUEST_CODE);
        }
        if (v == buttonUpdate){
            addUser();
            startActivity(new Intent(this, ProfileActivity.class));
        }
        if (v == buttonLogOut){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}
