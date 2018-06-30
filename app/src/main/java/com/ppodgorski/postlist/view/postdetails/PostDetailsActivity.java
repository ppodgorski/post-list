package com.ppodgorski.postlist.view.postdetails;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.ppodgorski.postlist.R;
import com.ppodgorski.postlist.model.Comment;
import com.ppodgorski.postlist.model.Post;
import com.ppodgorski.postlist.model.User;
import com.ppodgorski.postlist.view.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class PostDetailsActivity extends BaseActivity implements PostDetailsContract.View {

    @BindView(R.id.post_details_container)
    NestedScrollView mDetailsContainer;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    @BindView(R.id.retry_button)
    Button mRetryButton;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Inject
    PostDetailsPresenter mPresenter;

    @OnClick(R.id.retry_button)
    public void retryButtonClicked() {
        mRetryButton.setVisibility(View.GONE);
        mPresenter.getData();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupLayout();
    }

    @Override
    public void onStart() {
        mPresenter.takeView(this);
        mPresenter.getData();
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.dropView();
    }

    private void setupLayout() {
        setContentView(R.layout.activity_post_details);
        ButterKnife.bind(this);
        setupToolbar(mToolbar);
    }

    @Override
    public void setupPostViews(Post post) {

    }

    @Override
    public void setupUserViews(User user) {

    }

    @Override
    public void setupCommentsViews(List<Comment> comments) {
        mDetailsContainer.setVisibility(View.GONE);
    }

    @Override
    public void hideDetailsContainer() {
        mDetailsContainer.setVisibility(View.GONE);
    }

    @Override
    public void showDetailsContainer() {
        mDetailsContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoadingIndicator() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingIndicator() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        mRetryButton.setVisibility(View.VISIBLE);
        showNetworkError();
    }
}
