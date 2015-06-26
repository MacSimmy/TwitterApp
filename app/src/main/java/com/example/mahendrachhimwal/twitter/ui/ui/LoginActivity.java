package com.example.mahendrachhimwal.twitter.ui.ui;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mahendrachhimwal.twitter.R;

public class LoginActivity extends AppCompatActivity {

    private static final String APP_SHARED_PREF = "mahendra_twitter";
    private static final String KEY_USERNAME = "username";
    private Button btn_login;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private String username;
    private String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences prefs = getSharedPreferences(APP_SHARED_PREF, MODE_PRIVATE);
        String savedUsername = prefs.getString(KEY_USERNAME, null);
        if (savedUsername != null) {
            Intent intent = new Intent(LoginActivity.this, TweetListActivity.class);
            startActivity(intent);
        }
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                SharedPreferences prefs = getSharedPreferences(APP_SHARED_PREF, MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(KEY_USERNAME, username);
                //Code for extracting password value and saving it in the SharedPreference
                editor.commit();
                Intent intent = new Intent(LoginActivity.this, TweetListActivity.class);
                startActivity(intent);
            }
        });
        usernameEditText = (EditText) findViewById(R.id.fld_username);
        username = usernameEditText.getText().toString();
        passwordEditText = (EditText) findViewById(R.id.fld_pwd);
        password = passwordEditText.getText().toString();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
}
