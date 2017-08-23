package com.example.administrator.mymvp.injector.module;

import android.app.Application;
import android.content.Context;

import com.example.administrator.mymvp.App;
import com.example.administrator.mymvp.local.table.DaoSession;
import com.example.administrator.mymvp.rxbus.RxBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/8/17 0017.
 */

@Module
public class AppliacationModule {
    private final App mApplication;
    private final RxBus mRxBus;
    private final DaoSession mDaoSession;

    public AppliacationModule(App application, RxBus rxBus, DaoSession daoSession) {
        mApplication = application;
        mRxBus = rxBus;
        mDaoSession = daoSession;
    }


    @Provides
    @Singleton
    Context provideApplicationContext() {
        return mApplication.getApplicationContext();
    }

    @Provides
    @Singleton
    RxBus provideRxBus() {
        return mRxBus;
    }

    @Provides
    @Singleton
    DaoSession provideDaoSession() {
        return mDaoSession;
    }
}
