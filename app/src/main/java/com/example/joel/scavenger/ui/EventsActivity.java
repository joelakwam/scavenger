package com.example.joel.scavenger.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.joel.scavenger.models.Event;
import com.example.joel.scavenger.services.EventService;
import com.example.joel.scavenger.R;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class EventsActivity extends AppCompatActivity {

    private static final String TAG = EventsActivity.class.getSimpleName();
    public ArrayList<Event> events = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        getEvents(location);
    }

    private void getEvents(String location){
        final EventService eventService = new EventService();
        eventService.findEvents(location, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response){
                try{
                    String jsonData = response.body().string();
                    Log.v(TAG, jsonData);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
    }
}
