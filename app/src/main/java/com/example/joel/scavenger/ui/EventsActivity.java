package com.example.joel.scavenger.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.joel.scavenger.adapters.EventListAdapter;
import com.example.joel.scavenger.models.Event;
import com.example.joel.scavenger.services.EventService;
import com.example.joel.scavenger.R;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class EventsActivity extends AppCompatActivity {
    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    private EventListAdapter adapter;
    private static final String TAG = EventsActivity.class.getSimpleName();
    public ArrayList<Event> events = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        ButterKnife.bind(this);

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
            public void onResponse(Call call, Response response) throws IOException{

                events = eventService.processResults(response);

                EventsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new EventListAdapter(getApplicationContext(), events);
                        recyclerView.setAdapter(adapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(EventsActivity.this);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }
}
