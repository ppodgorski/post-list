package com.ppodgorski.postlist.view.postdetails.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ppodgorski.postlist.R;
import com.ppodgorski.postlist.model.Comment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.item_comment_email)
    TextView mEmailTextView;

    @BindView(R.id.item_comment_body)
    TextView mBodyTextView;

    private Comment mComment;

    public CommentsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Comment comment) {
        mComment = comment;
        setup();
    }

    private void setup() {
        mEmailTextView.setText(mComment.getEmail());
        mBodyTextView.setText(mComment.getBody());
    }

}
