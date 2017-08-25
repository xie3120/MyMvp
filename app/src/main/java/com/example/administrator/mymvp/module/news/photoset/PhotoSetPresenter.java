package com.example.administrator.mymvp.module.news.photoset;

import com.example.administrator.mymvp.api.RetrofitService;
import com.example.administrator.mymvp.api.bean.PhotoSetInfo;
import com.example.administrator.mymvp.module.base.IBasePresenter;
import com.orhanobut.logger.Logger;

import rx.Subscriber;
import rx.functions.Action0;

/**
 * Created by Administrator on 2017/8/25 0025.
 */

public class PhotoSetPresenter implements IBasePresenter {


    private final IPhotoSetView mView;
    private final String mPhotoSetId;

    public PhotoSetPresenter(IPhotoSetView view, String photoSetId) {
        mView = view;
        mPhotoSetId = photoSetId;
    }

    @Override
    public void getData(boolean isRefresh) {
        RetrofitService.getPhotoSet(mPhotoSetId)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mView.showLoading();
                    }
                })
                .compose(mView.<PhotoSetInfo>bindToLife())
                .subscribe(new Subscriber<PhotoSetInfo>() {
                    @Override
                    public void onCompleted() {
                        mView.hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e.toString());
                        mView.showNetError();
                    }

                    @Override
                    public void onNext(PhotoSetInfo photoSetBean) {
                        mView.loadData(photoSetBean);
                    }
                });
    }

    @Override
    public void getMoreData() {

    }
}
