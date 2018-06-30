package com.ppodgorski.postlist.network;

import com.ppodgorski.postlist.model.Comment;
import com.ppodgorski.postlist.model.Post;
import com.ppodgorski.postlist.model.User;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("posts")
    Observable<List<Post>> getPosts();

    @GET("posts/{id}")
    Observable<Post> getPost(@Path("id") Integer id);

    @GET("posts/{id}/comments")
    Observable<List<Comment>> getPostComments(@Path("id") Integer postId);

    @GET("users/{id}")
    Observable<User> getUser(@Path("id") Integer id);

}
