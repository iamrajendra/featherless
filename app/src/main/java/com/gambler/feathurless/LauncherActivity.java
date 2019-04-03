package com.gambler.feathurless;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LauncherActivity extends AppCompatActivity implements View.OnClickListener {
    Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        findViewById(R.id.login_btn).setOnClickListener(this);
        findViewById(R.id.sign_up_btn).setOnClickListener(this);
        mIntent = new Intent();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                mIntent.setClass(getApplicationContext(), LoginActivity.class);
                break;
            case R.id.sign_up_btn:
                mIntent.setClass(getApplicationContext(), LoginActivity.class);
                break;
        }
        startActivity(mIntent);
    }
}
