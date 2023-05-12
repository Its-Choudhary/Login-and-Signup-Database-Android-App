package com.example.login_page;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    private EditText mEditTextId;
    private EditText mEditTextPassword;
    private EditText mEditTextConformPassword;
    private DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mEditTextId = findViewById(R.id.usernameEditText);
        mEditTextPassword = findViewById(R.id.passwordEditText);
        mEditTextConformPassword = findViewById(R.id.confirmPasswordEditText);

        mDatabaseHelper = new DatabaseHelper(this);

        Button signUpButton = findViewById(R.id.signupButton);

        signUpButton.setOnClickListener(this::onClick);

        @SuppressLint("WrongViewCast") Button loginButton = findViewById(R.id.loginTextView);
        loginButton.setOnClickListener(v -> {
            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }

    private void onClick(View v) {
        String id = mEditTextId.getText().toString();
        String password = mEditTextPassword.getText().toString();
        String confirmPassword = ((EditText) findViewById(R.id.confirmPasswordEditText)).getText().toString();

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        mDatabaseHelper.addUser(id, password);

        Toast.makeText(this, "User added to database", Toast.LENGTH_SHORT).show();
    }

}
