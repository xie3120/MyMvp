package com.example.administrator.mymvp.module.news.newslist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.daimajia.slider.library.SliderLayout;
import com.dl7.recycler.adapter.BaseQuickAdapter;
import com.dl7.recycler.helper.RecyclerViewHelper;
import com.dl7.recycler.listener.OnRequestDataListener;
import com.example.administrator.mymvp.R;
import com.example.administrator.mymvp.adapter.item.NewsMultiItem;
import com.example.administrator.mymvp.api.bean.NewsInfo;
import com.example.administrator.mymvp.injector.component.DaggerNewsListComponent;
import com.example.administrator.mymvp.injector.module.NewsListModule;
import com.example.administrator.mymvp.module.base.BaseFragment;
import com.example.administrator.mymvp.module.base.IBasePresenter;
import com.example.administrator.mymvp.utils.SliderHelper;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInRightAnimationAdapter;

/**
 * Created by Administrator on 2017/8/21 0021.
 */

public class NewsListFragment extends BaseFragment<IBasePresenter> implements INewsListView{

    private static final String NEWS_TYPE_KEY = "NewsTypeKey";
    private String mNewsId;


    @BindView(R.id.rv_news_list)
    RecyclerView mRvNewsList;

    private SliderLayout mAdSlider;
    @Inject
    BaseQuickAdapter mAdapter;

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
        DaggerNewsListComponent.builder()
                .applicationComponent(getAppComponent())
                .newsListModule(new NewsListModule(this, mNewsId))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {
        SlideInRightAnimationAdapter animAdapter = new SlideInRightAnimationAdapter(mAdapter);
        RecyclerViewHelper.initRecyclerViewV(mContext, mRvNewsList, true, new AlphaInAnimationAdapter(animAdapter));
        mAdapter.setRequestDataListener(new OnRequestDataListener() {
            @Override
            public void onLoadMore() {
                mPresenter.getMoreData();
            }
        });
    }

    @Override
    protected void updateViews(boolean isRefresh) {
            mPresenter.getData(isRefresh);
    }

    @Override
    public void loadData(List<NewsMultiItem> data) {
        mAdapter.updateItems(data);
    }

    @Override
    public void loadMoreData(List<NewsMultiItem> data) {
        mAdapter.loadComplete();
        mAdapter.addItems(data);
    }

    @Override
    public void loadNoData() {
        mAdapter.loadAbnormal();
    }

    @Override
    public void loadAdData(NewsInfo newsBean) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.head_news_list, null);
        mAdSlider = (SliderLayout) view.findViewById(R.id.slider_ads);
        SliderHelper.initAdSlider(mContext, mAdSlider, newsBean);
        mAdapter.addHeaderView(view);
    }
}
