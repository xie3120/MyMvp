package com.example.administrator.mymvp.injector.module;

import com.dl7.recycler.adapter.BaseQuickAdapter;
import com.example.administrator.mymvp.adapter.NewsMultiListAdapter;
import com.example.administrator.mymvp.injector.PerFragment;
import com.example.administrator.mymvp.module.base.IBasePresenter;
import com.example.administrator.mymvp.module.newslist.NewsListFragment;
import com.example.administrator.mymvp.module.newslist.NewsListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/8/22 0022.
 */
@Module
public class NewsListModule {

    private final NewsListFragment mNewsListView;
    private final String mNewsId;

    public NewsListModule(NewsListFragment view, String newsId) {
        this.mNewsListView = view;
        this.mNewsId = newsId;
    }

    @PerFragment
    @Provides
    public IBasePresenter providePresenter() {
        return new NewsListPresenter(mNewsListView, mNewsId);
    }

    @PerFragment
    @Provides
    public BaseQuickAdapter provideAdapter() {
        return new NewsMultiListAdapter(mNewsListView.getContext());
    }
}
