package com.ppodgorski.postlist.view.postlist;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.ppodgorski.postlist.R;
import com.ppodgorski.postlist.model.Post;
import com.ppodgorski.postlist.view.base.BaseActivity;
import com.ppodgorski.postlist.view.postlist.recycler.PostClickListener;
import com.ppodgorski.postlist.view.postlist.recycler.PostListAdapter;
import com.ppodgorski.postlist.view.provider.IntentProvider;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PostListActivity extends BaseActivity implements PostListContract.View, PostClickListener {

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    @BindView(R.id.retry_button)
    Button mRetryButton;

    @BindView(R.id.post_list_recycler_view)
    RecyclerView mRecyclerView;

    @Inject
    PostListPresenter mPresenter;

    @Inject
    PostListAdapter mPostListAdapter;

    @Inject
    IntentProvider mIntentProvider;

    @OnClick(R.id.retry_button)
    public void retryButtonClicked() {
        mRetryButton.setVisibility(View.GONE);
        mPresenter.getPosts();
    }

    @Override
    public void onPostClicked(Post post) {
        startActivity(mIntentProvider.postDetailsIntent(this, post.getId()));
    }

    @Override
    public void showPosts(List<Post> posts) {
        mRecyclerView.setVisibility(View.VISIBLE);
        mPostListAdapter.setPosts(posts);
    }

    @Override
    public void hidePosts() {
        mRecyclerView.setVisibility(View.GONE);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupLayout();
    }

    @Override
    public void onStart() {
        mPresenter.takeView(this);
        mPresenter.getPosts();
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.dropView();
    }

    private void setupLayout() {
        setContentView(R.layout.activity_post_list);
        ButterKnife.bind(this);
        setupRecycler();
    }

    private void setupRecycler() {
        mRecyclerView.setAdapter(mPostListAdapter);
    }

}
