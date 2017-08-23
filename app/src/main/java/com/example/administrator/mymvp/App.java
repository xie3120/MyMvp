package com.example.administrator.mymvp;

import android.app.Application;
import android.content.Context;

import com.example.administrator.mymvp.injector.component.ApplicationComponent;
import com.example.administrator.mymvp.injector.component.DaggerApplicationComponent;
import com.example.administrator.mymvp.injector.module.AppliacationModule;
import com.example.administrator.mymvp.local.table.DaoMaster;
import com.example.administrator.mymvp.local.table.DaoSession;
import com.example.administrator.mymvp.rxbus.RxBus;
import com.example.administrator.mymvp.utils.ToastUtils;

import org.greenrobot.greendao.database.Database;

/**
 * Created by Administrator on 2017/8/17 0017.
 */

public class App extends Application {


    private static final String DB_NAME = "news-db";

    private static ApplicationComponent sAppComponent;
    private static Context sContext;
    private DaoSession mDaoSession;
    // 因为下载那边需要用，这里在外面实例化在通过 ApplicationModule 设置
    private RxBus mRxBus = new RxBus();

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        //RetrofitService.init();
        _initDatabase();
        _initInjector();
        _initConfig();
    }

    private void _initConfig() {
        ToastUtils.init(this);
    }

    private void _initDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,DB_NAME);
        Database database = helper.getWritableDb();
        mDaoSession = new DaoMaster(database).newSession();
    }

    /**
     * 初始化注射器
     */
    private void _initInjector() {
        // 这里不做注入操作，只提供一些全局单例数据
                sAppComponent = DaggerApplicationComponent.builder()
                .appliacationModule(new AppliacationModule(this, mRxBus, mDaoSession))
                .build();
    }

    public static ApplicationComponent getAppComponent() {
        return sAppComponent;
    }
    public static Context getContext() {
        return sContext;
    }
}
