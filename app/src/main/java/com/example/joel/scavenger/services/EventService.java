package com.example.joel.scavenger.services;


import com.example.joel.scavenger.Constants;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

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
}
