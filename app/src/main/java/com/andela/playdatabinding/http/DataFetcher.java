package com.andela.playdatabinding.http;


import com.andela.playdatabinding.model.User;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;


public interface DataFetcher {
    @GET("posts")
    Observable<List<User>> fetchDataFromApi();
}
