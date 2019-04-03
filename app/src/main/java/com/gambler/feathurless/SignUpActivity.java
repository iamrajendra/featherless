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

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = SignUpActivity.class.getSimpleName();
    private EditText mEditTextEmail;
    private EditText mEditTextPassword;
    private Button mButtonSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();
    }

    private void init() {
        mEditTextEmail = findViewById(R.id.email_et);
        mEditTextPassword = findViewById(R.id.password_et);
        mButtonSend = findViewById(R.id.sign_up);
        mButtonSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final User user = new User();
        user.email = mEditTextEmail.getText().toString();
        user.password =  mEditTextPassword.getText().toString();

        Feathers.getInstance().service("users", User.class).create(user, new FeathersService.FeathersCallback<User>() {
            @Override
            public void onSuccess(User newUser) {
                Feathers.getInstance().authenticate(user.email, user.password, new FeathersService.FeathersCallback<AuthResponse<User>>() {

                    @Override
                    public void onSuccess(AuthResponse<User> t) {
                        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(String errorMessage) {
                        Log.d("LoginActivity", "authenticate:onError | " + errorMessage);
                    }
                }, User.class);
            }

            @Override
            public void onError(String errorMessage) {
                Log.d("LoginActivity", "create:onError | " + errorMessage);
            }
        });
    }
}
