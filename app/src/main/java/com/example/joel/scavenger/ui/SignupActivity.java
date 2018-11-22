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

public class SignupActivity extends AppCompatActivity {

    @BindView(R.id.toLoginLink) TextView toLoginLink;
    @BindView(R.id.signUpButton) Button signUpButton;
    @BindView(R.id.emailField) EditText inputEmail;
    @BindView(R.id.password) EditText inputPassword;
    @BindView(R.id.registerHeading) TextView registerHeading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        ButterKnife.bind(this);

        Typeface varelaRound = Typeface.createFromAsset(getAssets(), "fonts/VarelaRound-Regular.ttf");
        registerHeading.setTypeface(varelaRound);
        toLoginLink.setTypeface(varelaRound);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString();

                if(email.isEmpty()){
                    Toast.makeText(SignupActivity.this, "Please enter email", Toast.LENGTH_LONG).show();
                }else if(password.isEmpty()){
                    Toast.makeText(SignupActivity.this, "Please enter password", Toast.LENGTH_LONG).show();
                }else if(password.length() < 6){
                    Toast.makeText(SignupActivity.this, "Password too short, enter minimum 6 characters", Toast.LENGTH_LONG).show();
                }else {
                    Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        toLoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
