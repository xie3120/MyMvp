package com.example.administrator.mymvp.injector.component;

import com.example.administrator.mymvp.injector.PerActivity;
import com.example.administrator.mymvp.injector.module.PhotoSetModule;
import com.example.administrator.mymvp.module.news.photoset.PhotoSetActivity;

import dagger.Component;

/**
 * Created by Administrator on 2017/8/25 0025.
 * 图集 Component
 */
@PerActivity
@Component(modules = PhotoSetModule.class)
public interface PhotoSetComponent {
    void inject(PhotoSetActivity activity);
}
