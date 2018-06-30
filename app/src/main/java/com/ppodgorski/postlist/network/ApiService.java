package com.ppodgorski.postlist.network;

import com.ppodgorski.postlist.model.Post;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface ApiService {

    @GET("posts")
    Single<List<Post>> getPosts();

}
