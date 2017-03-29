package com.andela.playdatabinding.http;


import com.andela.playdatabinding.model.User;

import retrofit2.http.GET;
import rx.Observable;


public interface DataFetcher {
    @GET("posts")
    Observable<User> fetchDataFromApi();
}
