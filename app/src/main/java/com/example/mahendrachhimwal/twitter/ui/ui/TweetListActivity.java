package com.example.mahendrachhimwal.twitter.ui.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mahendrachhimwal.twitter.R;
import com.example.mahendrachhimwal.twitter.ui.adapters.TweetAdapter;
import com.example.mahendrachhimwal.twitter.ui.constants.LogConstants;
import com.example.mahendrachhimwal.twitter.ui.models.Tweet;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class TweetListActivity extends AppCompatActivity {

    private static final String TWEETS_CACHE_FILE = "tweet_cache.ser";
    private ListView tweetListView;
    private TweetAdapter tweetItemAdapter;
    List<Tweet>tweetsRead=new ArrayList<Tweet>();
    List<Tweet> tweetsWrite=new ArrayList<Tweet>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_list);
        tweetListView = (ListView) findViewById(R.id.tweetList);
        tweetListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onListItemClick(tweetListView, view, position, id);
            }
        });
        readTweetsFromCache();
        setUpTweetList();
        doCacheTweets();
        if(tweetsRead.isEmpty())
        {
            readTweetsFromCache();
        }
        tweetItemAdapter = new TweetAdapter(this, tweetsRead);
        tweetListView = (ListView) findViewById(R.id.tweetList);
        tweetListView.setAdapter(tweetItemAdapter);
    }

    private  void readTweetsFromCache(){
        try{
            FileInputStream fis = openFileInput(TWEETS_CACHE_FILE);
            ObjectInputStream ois = new ObjectInputStream(fis);
            tweetsRead = ( List<Tweet> ) ois.readObject();
            ois.close();
            fis.close();
        }catch (Exception e){
            Log.d(LogConstants.DEBUG," File not found for reading",e);
        }
    }

    private void doCacheTweets() {
        try {
            FileOutputStream fos = openFileOutput(TWEETS_CACHE_FILE, MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tweetsWrite);
            oos.close();
            fos.close();
            Log.d(LogConstants.DEBUG, "Successfully wrote tweets to the file.");
        } catch (Exception e) {
            Log.e(LogConstants.DEBUG, "Error writing tweets", e);
        } finally {
            try {                //close file handles

            } catch (Exception e) {
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tweet_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
       /* TextView textView = (TextView) v.findViewById(R.id.tweetTitle);
        textView.setText("Tweet Clicked");*/
        Intent intent = new Intent(this, TweetDetailActivity.class);
        startActivity(intent);
    }

    private void setUpTweetList() {
        for (int i = 0; i < 20; i++) {
            Tweet tweet = new Tweet();
            tweet.setTitle("A nice header for Tweet # " + i);
            tweet.setBody("Some random body text for the tweet # " + i);
            tweetsWrite .add(tweet);
        }
    }

/*
    private class AsyncFetchTweets  extends AsyncTask<Params,Progress,Result> {
        @Override
        protected Result doInBackground(Params... params) throws InterruptedException {
            Thread.sleep(5000);
            Progress progress_data;
            Result result;

            //while the long job getting done {
            //update progress_data
            //update result
            publishProgress(progress_data);
            //}

            return result;
        }

        protected void onProgressUpdate(Progress... progress_data) {
            //use progress_data to show progress on the screen
        }

        protected void onPostExecute(Result... result) {
            //use result obtained at the end of
        }
    }*/
}
