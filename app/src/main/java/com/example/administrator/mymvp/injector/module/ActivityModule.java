package com.example.administrator.mymvp.injector.module;

import android.app.Activity;

import com.example.administrator.mymvp.injector.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/8/17 0017.
 */
@Module
public class ActivityModule {
    private final Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @PerActivity
    @Provides
    Activity getActivity(){
        return mActivity;
    }
}
