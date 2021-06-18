package com.dogfoot.insurancesystemapp.isApp.crossDomain.tech;

import com.dogfoot.insurancesystemapp.isApp.constants.Constant;
import com.dogfoot.insurancesystemapp.mainCrossDomain.tech.retrofit.MainNullOnEmptyConverterFactory;
import com.google.gson.GsonBuilder;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitTool {

    private static final String BASE_URL = Constant.URL;
    public static RestAPI getAPI(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .build()
                .create(RestAPI.class);
    }

    public static RestAPI getAPIWithNullConverter(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(new MainNullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .build()
                .create(RestAPI.class);
    }

    public static RestAPI getAPIWithAuthorizationToken(String token){
        Interceptor interceptor = chain -> {
            Request newRequest  = chain.request().newBuilder()
                    .addHeader("Authorization", token)
                    .build();
            return chain.proceed(newRequest);
        };
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create( new GsonBuilder().create()))
                .build()
                .create(RestAPI.class);
    }
}

