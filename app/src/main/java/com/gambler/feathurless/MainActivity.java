package com.gambler.feathurless;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gambler.feathurless.models.Message;

import org.feathersjs.client.Feathers;
import org.feathersjs.client.service.FeathersService;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    MessagesAdapter mAdapter;
    FeathersService<Message> messageService;
    ArrayList<Message> mItems;
    private RecyclerView mRecyclerView;
    private Button sendButton;
    private EditText mEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initializeAdapter();
    }

    private void init() {
        mRecyclerView  = findViewById(R.id.my_recycler_view);
        sendButton  = findViewById(R.id.sendButton);
        mEditText  = findViewById(R.id.textbox);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = new Message();
                message.text = mEditText.getText().toString();

                messageService.create(message, new FeathersService.FeathersCallback<Message>() {
                    @Override
                    public void onSuccess(Message t) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mEditText.setText("");
                            }
                        });
                    }

                    @Override
                    public void onError(String errorMessage) {

                    }
                });
            }
        });

    }


    private void initializeAdapter() {
        mItems = new ArrayList<>();
        messageService = Feathers.getInstance().service("messages", Message.class);
        mAdapter = new MessagesAdapter(this, messageService, R.layout.item_message);
        mRecyclerView.setAdapter(mAdapter);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

    }
}
