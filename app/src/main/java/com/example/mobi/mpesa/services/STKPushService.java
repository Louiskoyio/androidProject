package com.example.mobi.mpesa.services;

import com.example.mobi.mpesa.model.AccessToken;
import com.example.mobi.mpesa.model.STKPush;

public interface STKPushService {
    @POST("mpesa/stkpush/v1/processrequest")
    Call<STKPush> sendPush(@Body STKPush stkPush);

    @GET("jobs/pending")
    Call<STKPush> getTasks();

    @GET("oauth/v1/generate?grant_type=client_credentials")
    Call<AccessToken> getAccessToken();
}