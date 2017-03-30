package com.andela.playdatabinding.http;


import com.andela.playdatabinding.model.User;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

public class DataFetcherImpl implements DataFetcher {
    @Override
    public Observable<List<User>> fetchDataFromApi() {
        Retrofit retrofit = buildRetrofit();
        return retrofit.create(DataFetcher.class)
                .fetchDataFromApi();
    }

    private Retrofit buildRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
}
