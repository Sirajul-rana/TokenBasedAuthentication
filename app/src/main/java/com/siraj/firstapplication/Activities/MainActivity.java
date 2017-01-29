package com.siraj.firstapplication.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.siraj.firstapplication.ApiClient;
import com.siraj.firstapplication.Interfaces.LogEasyApi;
import com.siraj.firstapplication.Models.TokenRequset;
import com.siraj.firstapplication.Models.TokenResponse;
import com.siraj.firstapplication.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String BASE_URL = "http://192.168.0.103:9810";
    public static final String MyPREFERENCES = "MyPrefs" ;
    private String username;
    private String password;
    private Button btnLogin;
    private TextView username_text, password_text;
    LogEasyApi api;
    SharedPreferences preferences;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username_text = (TextView)findViewById(R.id.username);
        password_text = (TextView)findViewById(R.id.password);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        preferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        btnLogin.setOnClickListener(this);

        api = ApiClient.getClient().create(LogEasyApi.class);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
            {
                TokenRequset tokenRequset = new TokenRequset();

                //tokenRequset.setUsername(username_text.getText().toString());
                //tokenRequset.setPassword(password_text.getText().toString());
                //tokenRequset.setGrant_type("password");
                //Toast.makeText(getApplicationContext(), tokenRequset.getUsername()+tokenRequset.getPassword()+tokenRequset.getGrant_type(), Toast.LENGTH_SHORT).show();

                Call<TokenResponse> tokenResponse = api.getAccessToken(username_text.getText().toString(), password_text.getText().toString(), "password");

                tokenResponse.enqueue(new Callback<TokenResponse>() {
                    @Override
                    public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                        int statusCode = response.code();
                        if (response.isSuccessful()){
                            Log.d("LoginActivity", "onResponse: "+ response.body().getAccessToken());
                            TokenResponse response1 = response.body();
                            onFoundit(response1);
                        }
                        else{
                            Log.d("LoginActivity", "onResponse: "+ response.errorBody());
                        }
                    }

                    @Override
                    public void onFailure(Call<TokenResponse> call, Throwable t) {
                        Log.d("LoginActivity", "onFailure: "+ t.getMessage());
                    }
                });
            }
        }
    }

    public void onFoundit(TokenResponse response){
        Log.d("LoginActivity", "Called");
        if (response != null){
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("accessToken", response.getAccessToken());
            editor.putString("tokenType", response.getTokenType());
            editor.putString("expireIn", response.getExpiresIn().toString());
            editor.putString("userName", response.getUserName());
            editor.commit();
            intent = new Intent(MainActivity.this, UserActivity.class);
            startActivity(intent);
        }
        else{
            Log.d("LoginActivity", "Totally fucked up");
        }

    }
}
