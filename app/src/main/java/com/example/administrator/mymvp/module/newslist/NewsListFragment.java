package com.example.administrator.mymvp.module.newslist;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.daimajia.slider.library.SliderLayout;
import com.example.administrator.mymvp.R;
import com.example.administrator.mymvp.adapter.item.NewsMultiItem;
import com.example.administrator.mymvp.api.bean.NewsInfo;
import com.example.administrator.mymvp.module.base.BaseFragment;
import com.example.administrator.mymvp.module.base.IBasePresenter;

import java.util.List;

/**
 * Created by Administrator on 2017/8/21 0021.
 */

public class NewsListFragment extends BaseFragment<IBasePresenter> implements INewsListView{

    private static final String NEWS_TYPE_KEY = "NewsTypeKey";
    private String mNewsId;

    private SliderLayout mAdSlider;

    public static NewsListFragment newInstance(String newsId) {
        NewsListFragment fragment = new NewsListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(NEWS_TYPE_KEY, newsId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mNewsId = getArguments().getString(NEWS_TYPE_KEY);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdSlider != null) {
            mAdSlider.startAutoCycle();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAdSlider != null) {
            mAdSlider.stopAutoCycle();
        }
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_news_list;
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

    @Override
    public void loadData(List<NewsMultiItem> data) {

    }

    @Override
    public void loadMoreData(List<NewsMultiItem> data) {

    }

    @Override
    public void loadNoData() {

    }

    @Override
    public void loadAdData(NewsInfo newsBean) {

    }
}
