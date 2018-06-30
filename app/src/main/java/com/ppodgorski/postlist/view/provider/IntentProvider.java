package com.ppodgorski.postlist.view.provider;

import android.content.Intent;

import com.ppodgorski.postlist.di.scope.ActivityScoped;
import com.ppodgorski.postlist.view.base.BaseActivity;
import com.ppodgorski.postlist.view.postdetails.PostDetailsActivity;

import javax.inject.Inject;

@ActivityScoped
public class IntentProvider {

    public static final String POST_ID = "POST_ID";

    @Inject
    public IntentProvider() {

    }

    public Intent postDetailsIntent(BaseActivity activity, Integer postId) {
        Intent intent = new Intent(activity, PostDetailsActivity.class);
        intent.putExtra(POST_ID, postId);

        return intent;
    }
}
