package com.example.administrator.mymvp.module.news;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.administrator.mymvp.R;
import com.example.administrator.mymvp.adapter.ViewPagerAdapter;
import com.example.administrator.mymvp.injector.component.DaggerNewsMainComponent;
import com.example.administrator.mymvp.injector.module.NewsMainModule;
import com.example.administrator.mymvp.local.table.NewsTypeInfo;
import com.example.administrator.mymvp.module.base.BaseFragment;
import com.example.administrator.mymvp.module.base.IRxBusPresenter;
import com.example.administrator.mymvp.module.newslist.NewsListFragment;
import com.example.administrator.mymvp.rxbus.event.ChannelEvent;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/8/21 0021.
 */

public class NewsMainFragment extends BaseFragment<IRxBusPresenter> implements INewsMainView {
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @Inject
    ViewPagerAdapter mPagerAdapter;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_news_main;
    }

    @Override
    protected void initInjector() {
        DaggerNewsMainComponent.builder()
                .applicationComponent(getAppComponent())
                .newsMainModule(new NewsMainModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {
        initToolBar(mToolBar,true,"新闻");
        setHasOptionsMenu(true);//onCreate()期间调用setHasOptionsMenu()告知Options Menu fragment要添加菜单项
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mPresenter.registerRxBus(ChannelEvent.class, new Action1<ChannelEvent>() {
            @Override
            public void call(ChannelEvent channelEvent) {
                _handleChannelEvent(channelEvent);
            }
        });
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    public void loadData(List<NewsTypeInfo> checkList) {

    }

    /**
     * 处理频道事件
     * @param channelEvent
     */
    private void _handleChannelEvent(ChannelEvent channelEvent) {
        switch (channelEvent.eventType) {
            case ChannelEvent.ADD_EVENT:
                mPagerAdapter.addItem(NewsListFragment.newInstance(channelEvent.newsInfo.getTypeId()), channelEvent.newsInfo.getName());
                break;
            case ChannelEvent.DEL_EVENT:
                // 如果是删除操作直接切换第一项，不然容易出现加载到不存在的Fragment
                mViewPager.setCurrentItem(0);
                mPagerAdapter.delItem(channelEvent.newsInfo.getName());
                break;
            case ChannelEvent.SWAP_EVENT:
                mPagerAdapter.swapItems(channelEvent.fromPos, channelEvent.toPos);
                break;
        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_channel, menu);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unregisterRxBus();
    }


}
