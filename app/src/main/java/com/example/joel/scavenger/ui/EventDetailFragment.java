package com.example.joel.scavenger.ui;


import android.os.Bundle;
import android.os.Parcel;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joel.scavenger.R;
import com.example.joel.scavenger.models.Event;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;


public class EventDetailFragment extends Fragment {
    @BindView(R.id.eventImageView) ImageView imageLabel;
    @BindView(R.id.eventNameTextView) TextView nameLabel;
    @BindView(R.id.websiteUrl) TextView websiteUrl;
    @BindView(R.id.eventDescription) TextView eventDescription;

    private Event event;

    public static EventDetailFragment newInstance(Event event){
        EventDetailFragment eventDetailFragment = new EventDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("event", Parcels.wrap(event));
        eventDetailFragment.setArguments(args);
        return eventDetailFragment;
    }


    public EventDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        event = Parcels.unwrap(getArguments().getParcelable("event"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_detail, container, false);

        ButterKnife.bind(this, view);

        Picasso.with(view.getContext()).load(event.getLogoUrl()).into(imageLabel);

        nameLabel.setText(event.getName());
        websiteUrl.setText(event.getUrl());
        eventDescription.setText(event.getDescription());

        return view;
    }

}
