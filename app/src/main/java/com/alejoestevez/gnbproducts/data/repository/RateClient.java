package com.alejoestevez.gnbproducts.data.repository;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RateClient {
    private static RateClient instance = null;
    private RateService rateService;
    private Retrofit retrofit;

    public RateClient() {
        OkHttpClient.Builder httpClient =
                new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .addHeader("Accept", "application/json")
                        .addHeader("Content-type", "application/json")
                        .build();
                return chain.proceed(request);
            }
        });

        retrofit = new Retrofit.Builder()
                .baseUrl(RateService.URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        rateService = retrofit.create(RateService.class);
    }

    public static RateClient getInstance() {
        if(instance == null) {
            instance = new RateClient();
        }
        return instance;
    }

    public RateService getRateService() {
        return rateService;
    }


}
