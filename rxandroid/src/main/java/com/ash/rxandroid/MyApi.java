package com.ash.rxandroid;



import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MyApi {

    @GET("posts")
    Observable<List<Pojo>> getPost();
}
