package com.siraj.firstapplication.Interfaces;

import com.siraj.firstapplication.Models.TokenRequset;
import com.siraj.firstapplication.Models.TokenResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by siraj on 24-Jan-17.
 */

public interface LogEasyApi {
    @FormUrlEncoded
    @POST("/Token")
    Call<TokenResponse> getAccessToken(@Field("username") String username, @Field("password") String password, @Field("grant_type") String grant_type);
}
