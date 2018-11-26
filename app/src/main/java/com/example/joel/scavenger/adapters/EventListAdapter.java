package com.example.joel.scavenger.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joel.scavenger.R;
import com.example.joel.scavenger.models.Event;
import com.example.joel.scavenger.ui.EventDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

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
    public EventListAdapter.EventViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.event_list_item, viewGroup, false);

        EventViewHolder viewHolder = new EventViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EventListAdapter.EventViewHolder holder, int i){
        holder.bindEvent(events.get(i));
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class EventViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.eventImageView) ImageView eventImageView;
        @BindView(R.id.eventNameTextView) TextView eventNameTextView;
        @BindView(R.id.statusTextView) TextView statusTextView;
        @BindView(R.id.startTimeTextView) TextView startTimeTextView;
        @BindView(R.id.endTimeTextView) TextView endTimeTextView;
        @BindView(R.id.timeZoneTextView) TextView timeZoneTextView;
        private Context context;

        public EventViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int itemPosition = getLayoutPosition();
                    Intent intent = new Intent(context, EventDetailActivity.class);
                    intent.putExtra("position", itemPosition);
                    intent.putExtra("events", Parcels.wrap(events));
                    context.startActivity(intent);
                }
            });
        }

        public void bindEvent(Event event){
            Picasso.with(context).load(event.getLogoUrl()).into(eventImageView);
            eventNameTextView.setText(event.getName());
            statusTextView.setText(event.getStatus());
            startTimeTextView.setText("Start: " + event.getStartTime());
            endTimeTextView.setText("End: " + event.getEndTime());
            timeZoneTextView.setText("Timezone: " + event.getTimeZone());
        }
    }
}
