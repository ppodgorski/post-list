package com.ppodgorski.postlist.view.postdetails;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.ppodgorski.postlist.R;
import com.ppodgorski.postlist.view.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PostDetailsActivity extends BaseActivity implements PostDetailsContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Inject
    PostDetailsPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupLayout();
    }

    @Override
    public void onStart() {
        mPresenter.takeView(this);
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
        setupToolbar(mToolbar);
    }

}
