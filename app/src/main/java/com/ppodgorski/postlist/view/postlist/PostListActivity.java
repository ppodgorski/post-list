package com.ppodgorski.postlist.view.postlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ppodgorski.postlist.R;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public class PostListActivity extends DaggerAppCompatActivity implements PostListContract.View {

    @Inject
    PostListPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list);
        ButterKnife.bind(this);
    }

    @Override
    public void onResume() {
        mPresenter.takeView(this);
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.dropView();
    }

}
