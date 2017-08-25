package com.example.administrator.mymvp.injector.module;

import com.example.administrator.mymvp.injector.PerActivity;
import com.example.administrator.mymvp.module.base.IBasePresenter;
import com.example.administrator.mymvp.module.news.article.NewsArticleActivity;
import com.example.administrator.mymvp.module.news.article.NewsArticlePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/8/25 0025.
 * 新闻详情module
 */
@Module
public class NewsArticleModule {
    private final String mNewsId;
    private final NewsArticleActivity mView;

    public NewsArticleModule(NewsArticleActivity view, String newsId) {
        mNewsId = newsId;
        mView = view;
    }

    @PerActivity
    @Provides
    public IBasePresenter providePresenter() {
        return new NewsArticlePresenter(mNewsId, mView);
    }
}
