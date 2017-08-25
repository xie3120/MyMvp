package com.example.administrator.mymvp.injector.component;


import com.example.administrator.mymvp.injector.PerActivity;
import com.example.administrator.mymvp.injector.module.ChannelModule;
import com.example.administrator.mymvp.module.news.channel.ChannelActivity;

import dagger.Component;

/**
 * Created by long on 2016/8/31.
 * 管理 Component
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ChannelModule.class)
public interface ManageComponent {
    void inject(ChannelActivity activity);
}
