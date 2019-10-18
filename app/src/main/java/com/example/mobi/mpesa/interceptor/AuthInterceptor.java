package com.example.mobi.mpesa.interceptor;

import androidx.annotation.NonNull;

import com.google.android.gms.common.api.Response;

import java.io.IOException;

public class AuthInterceptor implements Interceptor {

    private String mAuthToken;

    public AuthInterceptor(String authToken) {
        mAuthToken = authToken;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request  = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer " + mAuthToken)
                .build();
        return chain.proceed(request);
    }
}