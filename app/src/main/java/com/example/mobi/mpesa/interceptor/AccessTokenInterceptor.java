package com.example.mobi.mpesa.interceptor;

import android.util.Base64;

import androidx.annotation.NonNull;

import com.example.mobi.BuildConfig;
import com.google.android.gms.common.api.Response;

import java.io.IOException;

public class AccessTokenInterceptor implements Interceptor {

    public AccessTokenInterceptor() {

    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

        String keys = BuildConfig.CONSUMER_KEY + ":" + BuildConfig.CONSUMER_SECRET;

        Request request = chain.request().newBuilder()
                .addHeader("Authorization", "Basic " + Base64.encodeToString(keys.getBytes(), Base64.NO_WRAP))
                .build();
        return chain.proceed(request);
    }
}
