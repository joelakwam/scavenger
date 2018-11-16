package com.example.joel.scavenger.services;


import com.example.joel.scavenger.Constants;
import com.example.joel.scavenger.models.Event;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class EventService{

    public static void findEvents(String location, Callback callback){

        //Creating a client
        OkHttpClient client = new OkHttpClient.Builder().build();

        //Creating the URL to send the request to
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.QUERY_PARAMETER, location);
        urlBuilder.addQueryParameter(Constants.TOKEN, Constants.ACCESS_TOKEN);
        String url = urlBuilder.build().toString();

        //Creating a request to be sent
        Request request = new Request.Builder().url(url).build();

        //Creating a callback received from the API
        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    //Method that will return an array list of Event objects
    public static ArrayList<Event> processResults(Response response){

        //ArrayList to hold a list of the required information.
        ArrayList<Event> eventArrayList = new ArrayList<>();

        try{
            String jsonData = response.body().string();
            JSONObject eventsbriteJSON = new JSONObject(jsonData);
            JSONArray eventsJSON = eventsbriteJSON.getJSONArray("events");

            for(int i = 0; i < eventsJSON.length(); i++){
                JSONObject event = eventsJSON.getJSONObject(i);
                String name = event.getJSONObject("name").getString("text");
                String description = event.getJSONObject("description").getString("text");
                String url = event.getString("url");
                String startTime = event.getJSONObject("start").getString("local");
                String endTime = event.getJSONObject("end").getString("local");
                String timeZone = event.getJSONObject("start").getString("timezone");
                String status = event.getString("status");
                String currency = event.getString("currency");

                Event events = new Event(name, description, url, startTime, endTime, timeZone, status, currency);
                eventArrayList.add(events);
            }
        }catch(IOException e){
            e.printStackTrace();
        }catch(JSONException e){
            e.printStackTrace();
        }
        return eventArrayList;
    }
}
