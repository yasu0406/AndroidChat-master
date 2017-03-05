package com.firebase.androidchat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button mButton;
    private EditText mTitle;

    // TODO: change this to your own Firebase URL
    private static final String FIREBASE_URL = "https://message-dbcf6.firebaseio.com";

    private String mUsername;
    private Firebase mFirebaseRef;
    private ValueEventListener mConnectedListener;
    private ChatListAdapter mChatListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTitle = (EditText) findViewById(R.id.title);
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(this);

        mFirebaseRef = new Firebase(FIREBASE_URL).child("chat");

    }

    @Override
    public void onClick(View v){
        if(v == mButton){
            Map<String, Object> data = new HashMap<String, Object>();
            String title = mTitle.getText().toString();

            data.put("title", title);
            mFirebaseRef.push().setValue(data);

            Intent intent = new Intent(getApplicationContext(), ChatDetailActivity.class);
            startActivity(intent);
        }
    }
}
