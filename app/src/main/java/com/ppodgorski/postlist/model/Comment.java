package com.ppodgorski.postlist.model;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @SerializedName("postId")
    Integer postId;

    @SerializedName("id")
    Integer id;

    @SerializedName("name")
    String name;

    @SerializedName("email")
    String email;

    @SerializedName("body")
    String body;

}

