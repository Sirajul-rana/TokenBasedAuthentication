package com.siraj.firstapplication.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.siraj.firstapplication.R;

import org.w3c.dom.Text;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {
    TextView accessToken, tokenType, expireIn, userName;
    Button logout;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        accessToken = (TextView)findViewById(R.id.accessToken);
        logout = (Button) findViewById(R.id.logout);
        tokenType = (TextView)findViewById(R.id.tokenType);
        expireIn = (TextView)findViewById(R.id.expireIn);
        userName = (TextView)findViewById(R.id.userName2);
        preferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);

        accessToken.setText(preferences.getString("userName", ""));
        logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.logout:
            {
                preferences.edit().remove(MainActivity.MyPREFERENCES).commit();
                this.finish();
            }
        }
    }
}
