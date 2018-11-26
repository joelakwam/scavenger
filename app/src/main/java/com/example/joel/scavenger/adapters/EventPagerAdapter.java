package com.example.joel.scavenger.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.joel.scavenger.models.Event;
import com.example.joel.scavenger.ui.EventDetailFragment;

import java.util.ArrayList;

public class EventPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Event> events;

    public EventPagerAdapter(FragmentManager fragmentManager, ArrayList<Event> events){
        super(fragmentManager);
        this.events = events;
    }

    @Override
    public Fragment getItem(int position){
        return EventDetailFragment.newInstance(events.get(position));
    }

    @Override
    public int getCount(){
        return events.size();
    }

    @Override
    public CharSequence getPageTitle(int position){
        return events.get(position).getName();
    }
}
