package com.example.joel.scavenger.models;

import org.parceler.Parcel;

@Parcel
public class Event{
    private String name;
    private String description;
    private String url;
    private String startTime;
    private String endTime;
    private String timeZone;
    private String status;
    private String currency;
    private String logoUrl;

    public Event(){}

    public Event(String name, String description, String url, String startTime, String endTime, String timeZone,String status, String currency, String logoUrl){
        this.name = name;
        this.description = description;
        this.url = url;
        this.startTime = startTime;
        this.endTime = endTime;
        this.timeZone = timeZone;
        this.status = status;
        this.currency = currency;
        this.logoUrl = logoUrl;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public String getUrl(){
        return url;
    }

    public String getStartTime(){
        return startTime;
    }

    public String getEndTime(){
        return endTime;
    }

    public String getTimeZone(){
        return timeZone;
    }

    public String getStatus(){
        return status;
    }

    public String getCurrency(){
        return currency;
    }

    public String getLogoUrl(){
        return logoUrl;
    }
}
