package com.example.administrator.mymvp.injector.component;

import com.example.administrator.mymvp.injector.PerFragment;
import com.example.administrator.mymvp.injector.module.NewsListModule;
import com.example.administrator.mymvp.module.newslist.NewsListFragment;

import dagger.Component;

import static android.os.Build.VERSION_CODES.N;

/**
 * Created by Administrator on 2017/8/22 0022.
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class,modules = NewsListModule.class)
public interface NewsListComponent {
    void inject(NewsListFragment fragment);
}
