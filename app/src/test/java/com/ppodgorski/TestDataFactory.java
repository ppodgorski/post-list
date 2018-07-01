package com.ppodgorski;

import com.ppodgorski.postlist.model.Post;

public class TestDataFactory {

    public static Post makePost() {
        return Post.builder()
                .id(0)
                .body("Body")
                .title("Title")
                .userId(0)
                .build();
    }
}
