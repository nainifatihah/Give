package com.ainifathiha.give;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Search2Activity extends AppCompatActivity implements View.OnClickListener {

    private Button bBBCategory;
    private ImageButton ibHome;
    private ImageButton ibCamera;
    private ImageButton ibChat;
    private ImageButton ibProfile;
    private DatabaseReference mDatabaseRef;
    private DatabaseReference nDatabaseRef;
    private DatabaseReference oDatabaseRef;
    private DatabaseReference pDatabaseRef;
    private DatabaseReference qDatabaseRef;
    private DatabaseReference rDatabaseRef;
    private ListView lv;
    private List<Items> itemsList;
    private ItemList adapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search2);
        itemsList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.lvItem);

        //Show progress dialog during list image loading
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading items ...");
        progressDialog.show();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference(ListingActivity.FB_DATABASE_PATH).child("Books");
        nDatabaseRef = FirebaseDatabase.getInstance().getReference(ListingActivity.FB_DATABASE_PATH).child("Stationery");
        oDatabaseRef = FirebaseDatabase.getInstance().getReference(ListingActivity.FB_DATABASE_PATH).child("Clothing");
        pDatabaseRef = FirebaseDatabase.getInstance().getReference(ListingActivity.FB_DATABASE_PATH).child("Health And Beauty");
        qDatabaseRef = FirebaseDatabase.getInstance().getReference(ListingActivity.FB_DATABASE_PATH).child("Electronics And Gadgets");
        rDatabaseRef = FirebaseDatabase.getInstance().getReference(ListingActivity.FB_DATABASE_PATH).child("Cooking Utensils");

        bBBCategory = (Button) findViewById(R.id.bBBCategory);
        ibHome = (ImageButton) findViewById(R.id.ibHome);
        ibCamera = (ImageButton) findViewById(R.id.ibCamera);
        ibChat = (ImageButton) findViewById(R.id.ibChat);
        ibProfile = (ImageButton) findViewById(R.id.ibProfile);

        bBBCategory.setOnClickListener(this);
        ibHome.setOnClickListener(this);
        ibCamera.setOnClickListener(this);
        ibChat.setOnClickListener(this);
        ibProfile.setOnClickListener(this);

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog.dismiss();

                //Fetch image data from firebase database
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    //ImageUpload class require default constructor
                    Items img = snapshot.getValue(Items.class);
                    itemsList.add(img);
                }

                //Init adapter
                adapter = new ItemList(Search2Activity.this, R.layout.activity_item_list, itemsList);
                //Set adapter for listview
                lv.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                progressDialog.dismiss();
            }
        });

        nDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog.dismiss();

                //Fetch image data from firebase database
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    //ImageUpload class require default constructor
                    Items img = snapshot.getValue(Items.class);
                    itemsList.add(img);
                }

                //Init adapter
                adapter = new ItemList(Search2Activity.this, R.layout.activity_item_list, itemsList);
                //Set adapter for listview
                lv.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                progressDialog.dismiss();
            }
        });

        oDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog.dismiss();

                //Fetch image data from firebase database
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    //ImageUpload class require default constructor
                    Items img = snapshot.getValue(Items.class);
                    itemsList.add(img);
                }

                //Init adapter
                adapter = new ItemList(Search2Activity.this, R.layout.activity_item_list, itemsList);
                //Set adapter for listview
                lv.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                progressDialog.dismiss();
            }
        });

        pDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog.dismiss();

                //Fetch image data from firebase database
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    //ImageUpload class require default constructor
                    Items img = snapshot.getValue(Items.class);
                    itemsList.add(img);
                }

                //Init adapter
                adapter = new ItemList(Search2Activity.this, R.layout.activity_item_list, itemsList);
                //Set adapter for listview
                lv.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                progressDialog.dismiss();
            }
        });

        qDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog.dismiss();

                //Fetch image data from firebase database
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    //ImageUpload class require default constructor
                    Items img = snapshot.getValue(Items.class);
                    itemsList.add(img);
                }

                //Init adapter
                adapter = new ItemList(Search2Activity.this, R.layout.activity_item_list, itemsList);
                //Set adapter for listview
                lv.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                progressDialog.dismiss();
            }
        });

        rDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog.dismiss();

                //Fetch image data from firebase database
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    //ImageUpload class require default constructor
                    Items img = snapshot.getValue(Items.class);
                    itemsList.add(img);
                }

                //Init adapter
                adapter = new ItemList(Search2Activity.this, R.layout.activity_item_list, itemsList);
                //Set adapter for listview
                lv.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                progressDialog.dismiss();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v == bBBCategory){
            startActivity(new Intent(this, Search1Activity.class));
        }
        if(v == ibHome){
            startActivity(new Intent(this, HomeActivity.class));
        }
        if(v == ibCamera){
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

