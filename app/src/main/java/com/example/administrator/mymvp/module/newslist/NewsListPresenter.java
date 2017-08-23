package com.example.administrator.mymvp.module.newslist;

import com.example.administrator.mymvp.module.base.IBasePresenter;

/**
 * Created by Administrator on 2017/8/22 0022.
 */

public class NewsListPresenter implements IBasePresenter {


    private INewsListView mView;
    private String mNewsId;

    private int mPage = 0;

    public NewsListPresenter(INewsListView view, String newsId) {
        this.mView = view;
        this.mNewsId = newsId;
    }


    @Override
    public void getData() {

    }

    @Override
    public void getMoreData() {

    }
}
