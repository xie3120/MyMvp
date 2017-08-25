package com.example.administrator.mymvp.module.video.liveslist;

import android.os.Bundle;

import com.example.administrator.mymvp.module.base.BaseFragment;
import com.example.administrator.mymvp.module.base.IBasePresenter;
import com.example.administrator.mymvp.module.news.newslist.NewsListFragment;

/**
 * Created by Administrator on 2017/8/25 0025.
 */

public class LiveListFragment extends BaseFragment<IBasePresenter> {

    private static final String NEWS_TYPE_KEY = "NewsTypeKey";

    public static NewsListFragment newInstance(String newsId) {
        NewsListFragment fragment = new NewsListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(NEWS_TYPE_KEY, newsId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int attachLayoutRes() {
        return 0;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }
}
