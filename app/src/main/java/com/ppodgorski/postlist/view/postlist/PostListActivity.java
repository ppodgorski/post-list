package com.ppodgorski.postlist.view.postlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ppodgorski.postlist.R;
import com.ppodgorski.postlist.model.Post;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public class PostListActivity extends DaggerAppCompatActivity implements PostListContract.View {

    @Inject
    PostListPresenter mPresenter;

    @Override
    public void showPosts(List<Post> posts) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupLayout();
    }

    @Override
    public void onResume() {
        mPresenter.takeView(this);
        mPresenter.getPosts();
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.dropView();
    }

    private void setupLayout() {
        setContentView(R.layout.activity_post_list);
        ButterKnife.bind(this);
    }


}
