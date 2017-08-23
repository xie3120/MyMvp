package com.example.administrator.mymvp.injector.component;

import android.content.Context;

import com.example.administrator.mymvp.injector.PerActivity;
import com.example.administrator.mymvp.injector.module.ActivityModule;
import com.example.administrator.mymvp.injector.module.AppliacationModule;
import com.example.administrator.mymvp.local.table.DaoSession;
import com.example.administrator.mymvp.rxbus.RxBus;

import dagger.Component;

/**
 * Created by Administrator on 2017/8/17 0017.
 */

@PerActivity
@Component(modules = AppliacationModule.class)
public interface ApplicationComponent {
    Context getContext();
    RxBus getRxBus();
    DaoSession getDaoSession();
}
