package com.ppodgorski.postlist.view.postdetails;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ppodgorski.postlist.R;
import com.ppodgorski.postlist.model.Comment;
import com.ppodgorski.postlist.model.Post;
import com.ppodgorski.postlist.model.User;
import com.ppodgorski.postlist.view.base.BaseActivity;
import com.ppodgorski.postlist.view.postdetails.recycler.CommentsAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class PostDetailsActivity extends BaseActivity implements PostDetailsContract.View {

    @BindView(R.id.post_details_comments_text)
    TextView mCommentsTextView;

    @BindView(R.id.post_details_title)
    TextView mPostTitleTextView;

    @BindView(R.id.post_details_user_name)
    TextView mUserNameTextView;

    @BindView(R.id.post_details_body)
    TextView mPostBodyTextView;

    @BindView(R.id.post_comments_recycler_view)
    RecyclerView mCommentsRecyclerView;

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

    @Inject
    CommentsAdapter mCommentsAdapter;

    @OnClick(R.id.retry_button)
    public void retryButtonClicked() {
        mRetryButton.setVisibility(View.GONE);
        mPresenter.getData();
    }

    @Override
    public void setupPostViews(Post post) {
        mPostTitleTextView.setText(post.getTitle());
        mPostBodyTextView.setText(post.getBody());
    }

    @Override
    public void setupUserViews(User user) {
        mUserNameTextView.setText(user.getName());
    }

    @Override
    public void setupCommentsViews(List<Comment> comments) {
        mCommentsTextView.setText(getCommentsText(comments.size()));
        mCommentsAdapter.setComments(comments);
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupLayout();
        mPresenter.takeView(this);
        mPresenter.getData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.dropView();
    }

    private void setupLayout() {
        setContentView(R.layout.activity_post_details);
        ButterKnife.bind(this);
        setupToolbar(mToolbar);
        setupRecycler();
    }

    private void setupRecycler() {
        mCommentsRecyclerView.setAdapter(mCommentsAdapter);
        mCommentsRecyclerView.setNestedScrollingEnabled(false);
    }

    private String getCommentsText(Integer size) {
        return new StringBuilder()
                .append(getString(R.string.comments))
                .append(" ")
                .append("(")
                .append(size)
                .append(")")
                .toString();
    }


}
