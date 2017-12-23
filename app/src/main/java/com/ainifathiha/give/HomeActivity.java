package com.ainifathiha.give;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivGive1;
    private ImageButton ibHome;
    private ImageButton ibSearch;
    private ImageButton ibCamera;
    private ImageButton ibChat;
    private ImageButton ibProfile;
    //private static final int CAMERA_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ivGive1 = (ImageView) findViewById(R.id.ivGive1);
        ibHome = (ImageButton) findViewById(R.id.ibHome);
        ibSearch = (ImageButton) findViewById(R.id.ibSearch);
        ibCamera = (ImageButton) findViewById(R.id.ibCamera);
        ibChat = (ImageButton) findViewById(R.id.ibChat);
        ibProfile = (ImageButton) findViewById(R.id.ibProfile);

        ivGive1.setOnClickListener(this);
        ibHome.setOnClickListener(this);
        ibSearch.setOnClickListener(this);
        ibCamera.setOnClickListener(this);
        ibChat.setOnClickListener(this);
        ibProfile.setOnClickListener(this);

    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {

            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] dataBAOS = baos.toByteArray();

            //to set the iv with the image captured by the phone camera
            //imageView.setImageBitmap(bitmap);

            //startActivity(new Intent(this, ListingActivity.class));
        }
    }*/

    @Override
    public void onClick(View v) {
        if(v == ibSearch){
            startActivity(new Intent(this, Search1Activity.class));
        }
        if(v == ibCamera){
            //Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //startActivityForResult(intent, CAMERA_REQUEST_CODE);
            startActivity(new Intent(this, ListingActivity.class));
        }
        if(v == ibChat){
            startActivity(new Intent(this, ChatActivity.class));
        }
        if(v == ibProfile){
            startActivity(new Intent(this, ProfileActivity.class));
        }
    }
}
