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
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
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

public class ListingActivity extends AppCompatActivity implements View.OnClickListener {

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private ImageView imageView;
    private EditText txtImageName;
    private EditText txtImageDescription;
    private Spinner txtImageCategory;
    private Uri imgUri;
    private static final int CAMERA_REQUEST_CODE = 1;
    public static final String FB_STORAGE_PATH = "item/";
    public static final String FB_DATABASE_PATH = "item";
    public static final int REQUEST_CODE = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);

        mStorageRef = FirebaseStorage.getInstance().getReference();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference(FB_DATABASE_PATH); //.child(String.valueOf(txtImageCategory));

        txtImageName = (EditText)findViewById(R.id.etName);
        txtImageDescription = (EditText)findViewById(R.id.etDescription);
        txtImageCategory = (Spinner)findViewById(R.id.sCategory);
        imageView = (ImageView)findViewById(R.id.ivCamera);
        imageView.setOnClickListener(this);
        //So that user will be directed to the phone camera
        //Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //startActivityForResult(intent, CAMERA_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*if(requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK){

            Bitmap bitmap = (Bitmap)data.getExtras().get("data");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
            byte[] dataBAOS = baos.toByteArray();

            //to set the iv with the image captured by the phone camera
            imageView.setImageBitmap(bitmap);
        }*/
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null){
            imgUri = data.getData();

            try {
                Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(),imgUri);
                imageView.setImageBitmap(bm);
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
    public void bUpload_Click(View v){
        if (imgUri != null){
            final ProgressDialog dialog = new ProgressDialog(this);
            dialog.setTitle("Uploading items");
            dialog.show();

            //get the storage reference
            StorageReference ref = mStorageRef.child(FB_STORAGE_PATH + System.currentTimeMillis() +"."+getImageExt(imgUri));

            //add file to reference
            ref.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Item uploaded", Toast.LENGTH_SHORT).show();
                    Items imageUpload = new Items(txtImageName.getText().toString(), txtImageDescription.getText().toString(), txtImageCategory.getSelectedItem().toString(), taskSnapshot.getDownloadUrl().toString());

                    String category = txtImageCategory.getSelectedItem().toString();

                    //Save image info in database
                    String uploadId = mDatabaseRef.child(category).push().getKey();
                    mDatabaseRef.child(category).child(uploadId).setValue(imageUpload);


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
                            dialog.setMessage("Uploaded " + (int)progress+"%");
                        }
                    });
        } else {
            //Toast.makeText(getApplicationContext(), "Please select image", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClick(View v) {
        if(v == imageView){
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select image"), REQUEST_CODE);

        }
        }

}


