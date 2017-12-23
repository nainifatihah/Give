package com.ainifathiha.give;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView ivAvatar;
    private TextView tvUsername;
    private TextView tvLocation;
    private ImageButton ibHome;
    private ImageButton ibSearch;
    private ImageButton ibCamera;
    private ImageButton ibChat;
    private ImageButton ibEdit;
    public static final String FB_STORAGE_PATH = "user/";
    public static final String FB_DATABASE_PATH = "user";
    private DatabaseReference databaseUser;
    private DatabaseReference mDatabaseRef;
    private DatabaseReference nDatabaseRef;
    private DatabaseReference oDatabaseRef;
    private DatabaseReference pDatabaseRef;
    private DatabaseReference qDatabaseRef;
    private DatabaseReference rDatabaseRef;
    private GridView gv;
    private List<Items> itemsList2;
    private ItemList2 adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        itemsList2 = new ArrayList<>();
        gv = (GridView) findViewById(R.id.gView);

        databaseUser = FirebaseDatabase.getInstance().getReference(FB_DATABASE_PATH);

        ivAvatar = (ImageView) findViewById(R.id.ivAvatar);
        tvUsername = (TextView) findViewById(R.id.tvUsername);
        tvLocation = (TextView) findViewById(R.id.tvLocation);
        ibHome = (ImageButton) findViewById(R.id.ibHome);
        ibSearch = (ImageButton) findViewById(R.id.ibSearch);
        ibCamera = (ImageButton) findViewById(R.id.ibCamera);
        ibChat = (ImageButton) findViewById(R.id.ibChat);
        ibEdit = (ImageButton) findViewById(R.id.ibEdit);

        ibHome.setOnClickListener(this);
        ibSearch.setOnClickListener(this);
        ibCamera.setOnClickListener(this);
        ibChat.setOnClickListener(this);
        ibEdit.setOnClickListener(this);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference(ListingActivity.FB_DATABASE_PATH).child("Books");
        nDatabaseRef = FirebaseDatabase.getInstance().getReference(ListingActivity.FB_DATABASE_PATH).child("Stationery");
        oDatabaseRef = FirebaseDatabase.getInstance().getReference(ListingActivity.FB_DATABASE_PATH).child("Clothing");
        pDatabaseRef = FirebaseDatabase.getInstance().getReference(ListingActivity.FB_DATABASE_PATH).child("Health And Beauty");
        qDatabaseRef = FirebaseDatabase.getInstance().getReference(ListingActivity.FB_DATABASE_PATH).child("Electronics And Gadgets");
        rDatabaseRef = FirebaseDatabase.getInstance().getReference(ListingActivity.FB_DATABASE_PATH).child("Cooking Utensils");

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //progressDialog.dismiss();
                //Fetch image data from firebase database
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    //ImageUpload class require default constructor
                    Items img = snapshot.getValue(Items.class);
                    itemsList2.add(img);
                }
                //Init adapter
                adapter = new ItemList2(ProfileActivity.this, R.layout.activity_item, itemsList2);
                //Set adapter for listview
                gv.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                //progressDialog.dismiss();
            }
        });

        nDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //progressDialog.dismiss();
                //Fetch image data from firebase database
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    //ImageUpload class require default constructor
                    Items img = snapshot.getValue(Items.class);
                    itemsList2.add(img);
                }
                //Init adapter
                adapter = new ItemList2(ProfileActivity.this, R.layout.activity_item, itemsList2);
                //Set adapter for listview
                gv.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                //progressDialog.dismiss();
            }
        });

        oDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //progressDialog.dismiss();
                //Fetch image data from firebase database
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    //ImageUpload class require default constructor
                    Items img = snapshot.getValue(Items.class);
                    itemsList2.add(img);
                }
                //Init adapter
                adapter = new ItemList2(ProfileActivity.this, R.layout.activity_item, itemsList2);
                //Set adapter for listview
                gv.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                //progressDialog.dismiss();
            }
        });

        pDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //progressDialog.dismiss();
                //Fetch image data from firebase database
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    //ImageUpload class require default constructor
                    Items img = snapshot.getValue(Items.class);
                    itemsList2.add(img);
                }
                //Init adapter
                adapter = new ItemList2(ProfileActivity.this, R.layout.activity_item, itemsList2);
                //Set adapter for listview
                gv.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                //progressDialog.dismiss();
            }
        });

        qDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //progressDialog.dismiss();
                //Fetch image data from firebase database
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    //ImageUpload class require default constructor
                    Items img = snapshot.getValue(Items.class);
                    itemsList2.add(img);
                }
                //Init adapter
                adapter = new ItemList2(ProfileActivity.this, R.layout.activity_item, itemsList2);
                //Set adapter for listview
                gv.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                //progressDialog.dismiss();
            }
        });

        rDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //progressDialog.dismiss();
                //Fetch image data from firebase database
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    //ImageUpload class require default constructor
                    Items img = snapshot.getValue(Items.class);
                    itemsList2.add(img);
                }
                //Init adapter
                adapter = new ItemList2(ProfileActivity.this, R.layout.activity_item, itemsList2);
                //Set adapter for listview
                gv.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                //progressDialog.dismiss();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseUser.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()){
                    Users orang = itemSnapshot.getValue(Users.class);
                    tvUsername.setText(orang.getUsername());
                    tvLocation.setText(orang.getLocation());
                    Glide.with(ProfileActivity.this).load(orang.getUrl()).into(ivAvatar);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v == ibHome){
            startActivity(new Intent(this, HomeActivity.class));
        }
        if(v == ibSearch){
            startActivity(new Intent(this, Search1Activity.class));
        }
        if(v == ibCamera){
            startActivity(new Intent(this, ListingActivity.class));
        }
        if(v == ibChat){
            startActivity(new Intent(this, ChatActivity.class));
        }
        if(v == ibEdit){
            startActivity(new Intent(this, SettingActivity.class));
        }

    }
}
