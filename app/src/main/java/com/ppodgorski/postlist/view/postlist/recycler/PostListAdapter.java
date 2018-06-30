package com.ppodgorski.postlist.view.postlist.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ppodgorski.postlist.R;
import com.ppodgorski.postlist.model.Post;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class PostListAdapter extends RecyclerView.Adapter<PostListViewHolder> {

    private final PostClickListener mPostClickListener;

    @NonNull
    private List<Post> mPosts;

    @Inject
    public PostListAdapter(PostClickListener postClickListener) {
        mPosts = Collections.emptyList();
        mPostClickListener = postClickListener;
    }

    @NonNull
    @Override
    public PostListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_post_layout, parent, false);
        return new PostListViewHolder(itemView, mPostClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PostListViewHolder holder, int position) {
        holder.bind(mPosts.get(position));
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    public void setPosts(List<Post> posts) {
        mPosts = posts;
        notifyDataSetChanged();
    }

}
