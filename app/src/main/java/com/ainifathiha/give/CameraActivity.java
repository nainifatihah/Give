package com.ainifathiha.give;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.ByteArrayOutputStream;

public class CameraActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton ibBack;
    private ImageView ivCamera;
    private Button bUpload;

    private static final int CAMERA_REQUEST_CODE = 1;

    private StorageReference storageReference;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        storageReference = FirebaseStorage.getInstance().getReference();

        ibBack = (ImageButton) findViewById(R.id.ibBack);
        ivCamera = (ImageView) findViewById(R.id.ivCamera);
        //bUpload = (Button) findViewById(R.id.bUpload);

        progressDialog = new ProgressDialog(this);

        ibBack.setOnClickListener(this);
        bUpload.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //GOOD JOB AINI!
        if(requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK){

            progressDialog.setMessage("Uploading Image ...");
            progressDialog.show();

            //get the camera image
            Bundle extras = data.getExtras();
            Bitmap bitmap = (Bitmap)data.getExtras().get("data");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
            byte[] dataBAOS = baos.toByteArray();

            //set the image into iv
            ivCamera.setImageBitmap(bitmap);

            //UPLOAD PICTURE TO FIREBASE
            //firebase storage folder where to put the images
            StorageReference filePath = FirebaseStorage.getInstance().getReferenceFromUrl("gs://give-130ca.appspot.com");

            //name of the image file
            StorageReference imagePath = filePath.child("Photos");

            //upload image
            UploadTask uploadTask = imagePath.putBytes(dataBAOS);
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), "Sending failed", Toast.LENGTH_SHORT).show();
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>(){
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressDialog.dismiss();
                }
            });
        }
    }

    @Override
    public void onClick(View v) {
        if(v == ibBack){
            finish();
            startActivity(new Intent(this, HomeActivity.class));
        }
        if(v == bUpload){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, CAMERA_REQUEST_CODE);
        }
    }
}
