package com.ppodgorski.postlist.view.postlist.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ppodgorski.postlist.R;
import com.ppodgorski.postlist.model.Post;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PostListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.item_post_title)
    TextView mTitleTextView;

    @BindView(R.id.item_post_body)
    TextView mBodyTextView;

    private final PostClickListener mPostClickListener;
    private Post mPost;

    public PostListViewHolder(View view, PostClickListener postClickListener) {
        super(view);
        ButterKnife.bind(this, view);
        mPostClickListener = postClickListener;
    }

    @OnClick(R.id.item_post_view)
    public void onPostClick() {
        mPostClickListener.onPostClicked(mPost);
    }

    public void bind(Post post) {
        mPost = post;
        setup();
    }

    private void setup() {
        mTitleTextView.setText(mPost.getTitle());
        mBodyTextView.setText(mPost.getBody());
    }

}
