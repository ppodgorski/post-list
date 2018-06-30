package com.ppodgorski.postlist.view.postdetails.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ppodgorski.postlist.R;
import com.ppodgorski.postlist.model.Comment;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsViewHolder> {

    @NonNull
    private List<Comment> mComments;

    @Inject
    public CommentsAdapter() {
        mComments = Collections.emptyList();
    }

    @NonNull
    @Override
    public CommentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_comment_layout, parent, false);
        return new CommentsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsViewHolder holder, int position) {
        holder.bind(mComments.get(position));
    }

    @Override
    public int getItemCount() {
        return mComments.size();
    }

    public void setComments(List<Comment> comments) {
        mComments = comments;
        notifyDataSetChanged();
    }

}
