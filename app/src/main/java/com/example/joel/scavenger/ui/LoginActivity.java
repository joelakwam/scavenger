package com.example.joel.scavenger.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joel.scavenger.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity{
    @BindView(R.id.toRegisterLink) TextView toRegisterLink;
    @BindView(R.id.loginButton) Button loginButton;
    @BindView(R.id.emailField) EditText inputEmail;
    @BindView(R.id.password) EditText inputPassword;
    @BindView(R.id.loginHeading) TextView loginHeading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        Typeface varelaRound = Typeface.createFromAsset(getAssets(), "fonts/VarelaRound-Regular.ttf");
        loginHeading.setTypeface(varelaRound);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString();

                if(email.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Please enter email", Toast.LENGTH_LONG).show();
                }else if(password.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Please enter password", Toast.LENGTH_LONG).show();
                }else {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        toRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}
