package com.example.administrator.mymvp.injector.component;

import com.example.administrator.mymvp.injector.PerActivity;
import com.example.administrator.mymvp.injector.module.NewsArticleModule;
import com.example.administrator.mymvp.module.news.article.NewsArticleActivity;

import dagger.Component;

/**
 * Created by Administrator on 2017/8/25 0025.
 * 新闻详情componet
 */
@PerActivity
@Component(modules = NewsArticleModule.class)
public interface NewsArticleComponent {
    void inject(NewsArticleActivity activity);
}
