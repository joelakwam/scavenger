package com.example.joel.scavenger.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joel.scavenger.R;
import com.example.joel.scavenger.models.Event;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventViewHolder> {
    private ArrayList<Event> events = new ArrayList<>();
    private Context context;

    public EventListAdapter(Context context, ArrayList<Event> events){
        this.context = context;
        this.events = events;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return null;
    }

    public class EventViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.eventImageView) ImageView eventImageView;
        @BindView(R.id.eventNameTextView) TextView eventNameTextView;
        private Context context;

        public EventViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this);
            context = itemView.getContext();
        }

        public void bindEvent(Event event){
            eventNameTextView.setText(event.getName());
        }
    }
}
