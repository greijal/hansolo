package com.b2w.sw.client;


import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


public class SWApi {

    private SWApi() {
    }

    public static SWClient build() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://swapi.co/api/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        return retrofit.create(SWClient.class);
    }
}
