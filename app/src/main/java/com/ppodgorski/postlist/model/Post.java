package com.ppodgorski.postlist.model;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @SerializedName("userId")
    Integer userId;

    @SerializedName("id")
    Integer id;

    @SerializedName("title")
    String title;

    @SerializedName("body")
    String body;

    public String getTitle() {
        return title.substring(0,1).toUpperCase() + title.substring(1);
    }

    public String getBody() {
        return body.substring(0,1).toUpperCase() + body.substring(1);
    }

}
