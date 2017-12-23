package com.ainifathiha.give;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SearchView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class Search1Activity extends AppCompatActivity implements View.OnClickListener {

    private Button bRSearches;
    private Button bBBCategory;
    private Button bEGadget;
    private Button bHBeauty;
    private Button bAccessories;
    private Button bCUtensils;
    private Button bBooks;
    private Button bClothings;
    private ImageButton ibHome;
    private ImageButton ibSearch;
    private ImageButton ibCamera;
    private ImageButton ibChat;
    private ImageButton ibProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search1);

        bRSearches = (Button) findViewById(R.id.bRSearches);
        bBBCategory = (Button) findViewById(R.id.bBBCategory);
        bEGadget = (Button) findViewById(R.id.bEGadget);
        bHBeauty = (Button) findViewById(R.id.bHBeauty);
        bAccessories = (Button) findViewById(R.id.bStationery);
        bCUtensils = (Button) findViewById(R.id.bCUtensils);
        bBooks = (Button) findViewById(R.id.bBooks);
        bClothings = (Button) findViewById(R.id.bClothings);
        ibHome = (ImageButton) findViewById(R.id.ibHome);
        ibSearch = (ImageButton) findViewById(R.id.ibSearch);
        ibCamera = (ImageButton) findViewById(R.id.ibCamera);
        ibChat = (ImageButton) findViewById(R.id.ibChat);
        ibProfile = (ImageButton) findViewById(R.id.ibProfile);

        bRSearches.setOnClickListener(this);
        bBBCategory.setOnClickListener(this);
        bEGadget.setOnClickListener(this);
        bHBeauty.setOnClickListener(this);
        bAccessories.setOnClickListener(this);
        bCUtensils.setOnClickListener(this);
        bBooks.setOnClickListener(this);
        bClothings.setOnClickListener(this);
        ibHome.setOnClickListener(this);
        ibSearch.setOnClickListener(this);
        ibCamera.setOnClickListener(this);
        ibChat.setOnClickListener(this);
        ibProfile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == bRSearches){
            startActivity(new Intent(this, Search2Activity.class));
        }
        if(v == bEGadget){
            startActivity(new Intent(this, ElectronicsGadgets.class));
        }
        if(v == bHBeauty){
            startActivity(new Intent(this, HealthBeauty.class));
        }
        if(v == bAccessories){
            startActivity(new Intent(this, Stationery.class));
        }
        if(v == bCUtensils){
            startActivity(new Intent(this, CookingUtensils.class));
        }
        if(v == bBooks){
            startActivity(new Intent(this, Books.class));
        }
        if(v == bClothings){
            startActivity(new Intent(this, Clothings.class));
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
