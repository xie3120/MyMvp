package com.example.administrator.mymvp.injector.module;

import com.example.administrator.mymvp.injector.PerActivity;
import com.example.administrator.mymvp.module.base.IBasePresenter;
import com.example.administrator.mymvp.module.news.photoset.PhotoSetActivity;
import com.example.administrator.mymvp.module.news.photoset.PhotoSetPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/8/25 0025.
 */
@Module
public class PhotoSetModule {
    private final PhotoSetActivity mView;
    private final String mPhotoSetId;

    public PhotoSetModule(PhotoSetActivity view, String photoSetId) {
        mView = view;
        mPhotoSetId = photoSetId;
    }

    @PerActivity
    @Provides
    public IBasePresenter providePhotoSetPresenter() {
        return new PhotoSetPresenter(mView, mPhotoSetId);
    }
}
