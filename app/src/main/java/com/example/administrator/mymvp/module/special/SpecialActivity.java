package com.example.administrator.mymvp.module.special;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.administrator.mymvp.R;
import com.example.administrator.mymvp.module.base.BaseSwipeBackActivity;
import com.example.administrator.mymvp.module.base.IBasePresenter;
import com.example.administrator.mymvp.widget.EmptyLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/22 0022.
 */

public class SpecialActivity extends BaseSwipeBackActivity<IBasePresenter> {

    private static final String SPECIAL_KEY = "SpecialKey";
    @BindView(R.id.rv_news_list)
    RecyclerView mRvNewsList;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.empty_layout)
    EmptyLayout mEmptyLayout;
    @BindView(R.id.fab_coping)
    FloatingActionButton mFabCoping;

    private String mSpecialId;

    public static void launch(Context context, String newsId) {
        Intent intent = new Intent(context, SpecialActivity.class);
        intent.putExtra(SPECIAL_KEY, newsId);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.slide_right_entry, R.anim.hold);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_special;

    }

    @Override
    protected void initInjector() {
        mSpecialId = getIntent().getStringExtra(SPECIAL_KEY);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }
}
