package com.example.administrator.mymvp.injector.module;

import com.example.administrator.mymvp.adapter.ViewPagerAdapter;
import com.example.administrator.mymvp.injector.PerFragment;
import com.example.administrator.mymvp.local.table.DaoSession;
import com.example.administrator.mymvp.module.base.IRxBusPresenter;
import com.example.administrator.mymvp.module.news.main.NewsMainFragment;
import com.example.administrator.mymvp.module.news.main.NewsMainPresenter;
import com.example.administrator.mymvp.rxbus.RxBus;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/8/21 0021.
 */
@Module
public class NewsMainModule {
    private final NewsMainFragment mView;

    public NewsMainModule(NewsMainFragment view) {
        mView = view;
    }

    @PerFragment
    @Provides
    public IRxBusPresenter provideMainPresenter(DaoSession daoSession, RxBus rxBus) {
        return new NewsMainPresenter(mView, daoSession.getNewsTypeInfoDao(), rxBus);
    }

    @PerFragment
    @Provides
    public ViewPagerAdapter provideViewPagerAdapter() {
        return new ViewPagerAdapter(mView.getChildFragmentManager());
    }

}
