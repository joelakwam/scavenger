package com.example.joel.scavenger.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joel.scavenger.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity{
    @BindView(R.id.toRegisterLink) TextView toRegisterLink;
    @BindView(R.id.loginButton) Button loginButton;
    @BindView(R.id.emailField) EditText inputEmail;
    @BindView(R.id.password) EditText inputPassword;
    @BindView(R.id.loginHeading) TextView loginHeading;
    @BindView(R.id.progressBar) ProgressBar progressBar;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        auth = FirebaseAuth.getInstance();

        Typeface varelaRound = Typeface.createFromAsset(getAssets(), "fonts/VarelaRound-Regular.ttf");
        toRegisterLink.setTypeface(varelaRound);
        loginHeading.setTypeface(varelaRound);

        toRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                if(email.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Please enter email", Toast.LENGTH_LONG).show();
                }else if(password.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Please enter password", Toast.LENGTH_LONG).show();
                }else {
                    progressBar.setVisibility(View.VISIBLE);

                    //User Authentication
                    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);

                            if(!task.isSuccessful()){
                                if(password.length() < 6){
                                    inputPassword.setError("Password is too short!");
                                }else{
                                    Toast.makeText(LoginActivity.this, "Wrong Email/password", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                            }
                        }
                    });
                }
            }
        });
    }
}
