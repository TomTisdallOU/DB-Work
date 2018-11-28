package com.example.dbproject;

import android.content.Context;
import android.support.annotation.NonNull;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class StandingsAdapter extends ArrayAdapter {
    public StandingsAdapter(Context context,  ArrayList<UserResults> results) {
        super(context, 0, results);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        UserResults userResults = (UserResults) getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.standings_row, parent, false);
        }

        TextView userTextView = convertView.findViewById(R.id.sr_personNameTextView);
        TextView pointsTextView = convertView.findViewById(R.id.sr_pointsTextView);

        userTextView.setText(userResults.getUserName());
        pointsTextView.setText(String.valueOf(userResults.getTotalPoints()));

        return convertView;



     //   return super.getView(position, convertView, parent);
    }
}
