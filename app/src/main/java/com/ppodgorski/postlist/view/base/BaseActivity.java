package com.ppodgorski.postlist.view.base;

import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;

import com.ppodgorski.postlist.R;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity {

    protected void showNetworkError() {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                R.string.network_error, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    protected void setupToolbar(android.support.v7.widget.Toolbar toolbar) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
