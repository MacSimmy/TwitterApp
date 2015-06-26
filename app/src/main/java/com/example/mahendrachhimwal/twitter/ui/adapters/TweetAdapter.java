package com.example.mahendrachhimwal.twitter.ui.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.mahendrachhimwal.twitter.R;
import com.example.mahendrachhimwal.twitter.ui.models.Tweet;

import java.util.List;

/**
 * Created by mahendra.chhimwal on 26/06/2015.
 */
public class TweetAdapter extends ArrayAdapter {
    private LayoutInflater inflater;
    public TweetAdapter(Activity activity, List<Tweet>items){
        super(activity, R.layout.row_tweet, items);
        inflater = activity.getWindow().getLayoutInflater();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        return inflater.inflate(R.layout.row_tweet, parent, false);
    }
}
