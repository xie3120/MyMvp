package com.example.administrator.mymvp.injector.module;

import com.dl7.recycler.adapter.BaseQuickAdapter;
import com.example.administrator.mymvp.adapter.ManageAdapter;
import com.example.administrator.mymvp.injector.PerActivity;
import com.example.administrator.mymvp.local.table.DaoSession;
import com.example.administrator.mymvp.module.channel.ChannelActivity;
import com.example.administrator.mymvp.module.channel.ChannelPresenter;
import com.example.administrator.mymvp.module.channel.IChannelPresenter;
import com.example.administrator.mymvp.rxbus.RxBus;

import dagger.Module;
import dagger.Provides;

/**
 * Created by long on 2016/8/31.
 * 管理
 */
@Module
public class ChannelModule {

    private final ChannelActivity mView;

    public ChannelModule(ChannelActivity view) {
        mView = view;
    }

    @Provides
    public BaseQuickAdapter provideManageAdapter() {
        return new ManageAdapter(mView);
    }

    @PerActivity
    @Provides
    public IChannelPresenter provideManagePresenter(DaoSession daoSession, RxBus rxBus) {
        return new ChannelPresenter(mView, daoSession.getNewsTypeInfoDao(), rxBus);
    }
}
