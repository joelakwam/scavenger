package com.example.joel.scavenger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.searchField) EditText searchField;
    @BindView(R.id.searchButton) Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String location = searchField.getText().toString();

//                if(location.isEmpty()){
//                    Toast.makeText(MainActivity.this, "Please enter location", Toast.LENGTH_LONG).show();
//                }else{
//                    Intent intent = new Intent(MainActivity.this, EventsActivity.class);
//                    intent.putExtra("location", location);
//                    startActivity(intent);
//                }
            }
        });
    }
}
