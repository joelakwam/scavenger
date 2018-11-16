package com.example.joel.scavenger.models;

public class Event{
    private String name;
    private String description;
    private String url;
    private String startTime;
    private String endTime;
    private String status;
    private String currency;

    public Event(String name, String description, String url, String startTime, String endTime, String status, String currency){
        this.name = name;
        this.description = description;
        this.url = url;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.currency = currency;
    }
}
