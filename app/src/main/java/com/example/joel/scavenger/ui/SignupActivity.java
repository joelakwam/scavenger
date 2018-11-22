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

public class SignupActivity extends AppCompatActivity {

    @BindView(R.id.toLoginLink) TextView toLoginLink;
    @BindView(R.id.signUpButton) Button signUpButton;
    @BindView(R.id.emailField) EditText inputEmail;
    @BindView(R.id.password) EditText inputPassword;
    @BindView(R.id.registerHeading) TextView registerHeading;
    @BindView(R.id.progressBar) ProgressBar progressBar;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        ButterKnife.bind(this);

        auth = FirebaseAuth.getInstance();

        //Setting custom font
        Typeface varelaRound = Typeface.createFromAsset(getAssets(), "fonts/VarelaRound-Regular.ttf");
        registerHeading.setTypeface(varelaRound);
        toLoginLink.setTypeface(varelaRound);

        //For navigating to LoginActivity when clicked
        toLoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        //OnClickListener on REGISTER button
        signUpButton.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View view) {

                //Holding user input
                final String email = inputEmail.getText().toString().trim();
                final String password = inputPassword.getText().toString().trim();

                //Form validation
                if (email.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Please enter email", Toast.LENGTH_LONG).show();
                } else if (password.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Please enter password", Toast.LENGTH_LONG).show();
                } else if (password.length() < 6) {
                    Toast.makeText(SignupActivity.this, "Password too short, enter minimum 6 characters", Toast.LENGTH_LONG).show();
                } else {

                    //Making progress bar visible
                    progressBar.setVisibility(View.VISIBLE);

                    //For creating a new user
                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(SignupActivity.this, "Authentication failed." + task.getException(), Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(SignupActivity.this, MainActivity.class));
                                finish();
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}
