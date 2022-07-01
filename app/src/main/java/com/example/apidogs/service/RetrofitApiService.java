package com.example.apidogs.service;

import com.example.apidogs.entity.Message;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitApiService {
    //@GET("{random}")
    @GET("api/breeds/image/{random}")
    public Call<Message> getMessageRandom(@Path("random") String random);
}
