package com.example.administrator.mymvp.injector.component;

import com.example.administrator.mymvp.injector.PerFragment;
import com.example.administrator.mymvp.injector.module.NewsMainModule;
import com.example.administrator.mymvp.module.news.main.NewsMainFragment;

import dagger.Component;

/**
 * Created by Administrator on 2017/8/21 0021.
 * 主页 Component
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class,modules = NewsMainModule.class)
public interface NewsMainComponent {
    void inject(NewsMainFragment fragment);
}
