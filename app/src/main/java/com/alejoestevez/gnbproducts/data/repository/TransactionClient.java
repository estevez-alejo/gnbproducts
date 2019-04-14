package com.alejoestevez.gnbproducts.data.repository;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TransactionClient {
    private static TransactionClient instance = null;
    private TransactionService transactionService;
    private Retrofit retrofit;

    public TransactionClient() {
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
                .baseUrl(TransactionService.URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        transactionService = retrofit.create(TransactionService.class);
    }

    public static TransactionClient getInstance() {
        if (instance == null) {
            instance = new TransactionClient();
        }
        return instance;
    }

    public TransactionService getTransactionService() {
        return transactionService;
    }
}
