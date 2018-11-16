package com.example.joel.scavenger.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

    @BindView(R.id.listView) ListView mListView;

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
                        String[] eventNames = new String[events.size()];

                        for(int i = 0; i < eventNames.length; i++){
                            eventNames[i] = events.get(i).getName();
                        }

                        ArrayAdapter adapter = new ArrayAdapter(EventsActivity.this, android.R.layout.simple_list_item_1, eventNames);
                        mListView.setAdapter(adapter);
                    }
                });
            }
        });
    }
}
