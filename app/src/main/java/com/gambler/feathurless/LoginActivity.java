package com.gambler.feathurless;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gambler.feathurless.models.User;

import org.feathersjs.client.Feathers;
import org.feathersjs.client.plugins.authentication.AuthResponse;
import org.feathersjs.client.service.FeathersService;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = LoginActivity.class.getSimpleName();
    private EditText mEditTextEmail;
    private EditText mEditTextPassword;
    private Button mButtonSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        mEditTextEmail = findViewById(R.id.email_et);
        mEditTextPassword = findViewById(R.id.password_et);
        mButtonSend = findViewById(R.id.login_btn);
        mButtonSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String username = mEditTextEmail.getText().toString();
        String password = mEditTextPassword.getText().toString();
        Feathers.getInstance().authenticate(username, password, new FeathersService.FeathersCallback<AuthResponse<User>>() {
            @Override
            public void onSuccess(AuthResponse<User> t) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onError(String errorMessage) {
                Log.e(TAG, "onError: " + errorMessage);

            }
        }, User.class);
    }
}
