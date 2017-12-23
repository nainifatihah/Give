package com.ainifathiha.give;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.text.format.DateFormat;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton ibHome;
    private ImageButton ibSearch;
    private ImageButton ibCamera;
    private ImageButton ibChat;
    private ImageButton ibProfile;
    private FirebaseListAdapter<ChatMessage> adapter;
    RelativeLayout activity_chats;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        ibHome = (ImageButton) findViewById(R.id.ibHome);
        ibSearch = (ImageButton) findViewById(R.id.ibSearch);
        ibCamera = (ImageButton) findViewById(R.id.ibCamera);
        ibChat = (ImageButton) findViewById(R.id.ibChat);
        ibProfile = (ImageButton) findViewById(R.id.ibProfile);

        ibHome.setOnClickListener(this);
        ibSearch.setOnClickListener(this);
        ibCamera.setOnClickListener(this);
        ibChat.setOnClickListener(this);
        ibProfile.setOnClickListener(this);


        activity_chats = (RelativeLayout)findViewById(R.id.activity_chat);
        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText input = (EditText)findViewById(R.id.input);
                FirebaseDatabase.getInstance().getReference().push().setValue(new ChatMessage(input.getText().toString(),
                        FirebaseAuth.getInstance().getCurrentUser().getEmail()));
                input.setText("");
            }
        });
        //load content
        //displayChatMessage();
    }

    /*private void displayChatMessage() {
        ListView listOfMessage = (ListView)findViewById(R.id.list_of_message);
        adapter = new FirebaseListAdapter<ChatMessage>(this,ChatMessage.class,R.layout.list_item,FirebaseDatabase.getInstance().getReference())
        {
            @Override
            protected void populateView(View v, ChatMessage model, int position) {
                //Get reference to the views of list_item.xml
                TextView messageText,messageUser,messageTime;
                messageText = (TextView) v.findViewById(R.id.message_text);
                messageUser = (TextView) v.findViewById(R.id.message_user);
                messageTime = (TextView) v.findViewById(R.id.message_time);

                messageText.setText(model.getMessageText());
                messageUser.setText(model.getMessageUser());
                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",model.getMessageTime()));
            }
        };
        listOfMessage.setAdapter(adapter);
    }*/

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
        if(v == ibProfile){
            startActivity(new Intent(this, ProfileActivity.class));
        }
    }
}
