package com.example.administrator.mymvp.injector.module;

import com.dl7.recycler.adapter.BaseQuickAdapter;
import com.example.administrator.mymvp.adapter.SpecialAdapter;
import com.example.administrator.mymvp.injector.PerActivity;
import com.example.administrator.mymvp.module.base.IBasePresenter;
import com.example.administrator.mymvp.module.news.special.SpecialActivity;
import com.example.administrator.mymvp.module.news.special.SpecialPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/8/25 0025.
 */
@Module
public class SpecialModule {

    private final SpecialActivity mView;
    private final String mSpecialId;

    public SpecialModule(SpecialActivity view, String specialId) {
        mView = view;
        mSpecialId = specialId;
    }

    @PerActivity
    @Provides
    public IBasePresenter provideSpecialPresent() {
        return new SpecialPresenter(mView, mSpecialId);
    }

    @PerActivity
    @Provides
    public BaseQuickAdapter provideSpecialAdapter() {
        return new SpecialAdapter(mView);
    }

}
