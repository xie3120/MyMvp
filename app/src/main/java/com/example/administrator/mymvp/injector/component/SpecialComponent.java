package com.example.administrator.mymvp.injector.component;

import com.example.administrator.mymvp.injector.PerActivity;
import com.example.administrator.mymvp.injector.module.SpecialModule;
import com.example.administrator.mymvp.module.news.special.SpecialActivity;

import dagger.Component;

/**
 * Created by Administrator on 2017/8/25 0025.
 */
@PerActivity
@Component(modules = SpecialModule.class)
public interface SpecialComponent {
    void inject(SpecialActivity activity);
}
