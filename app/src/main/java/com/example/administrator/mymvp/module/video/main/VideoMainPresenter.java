package com.example.administrator.mymvp.module.video.main;

import com.example.administrator.mymvp.api.RetrofitService;
import com.example.administrator.mymvp.api.bean.LiveListItemBean;
import com.example.administrator.mymvp.module.base.IBasePresenter;
import com.orhanobut.logger.Logger;

import java.util.List;

import rx.Subscriber;
import rx.functions.Action0;

/**
 * Created by Administrator on 2017/8/25 0025.
 */

public class VideoMainPresenter implements IBasePresenter {

    private IVideoMainView mView;
    //int offset, int limit, String live_type, String game_type
    private int mOffset , mLimit;
    private String mLive_type , mGame_type;

    public VideoMainPresenter(IVideoMainView view, int offset, int limit, String live_type, String game_type) {
        mView = view;
        mOffset = offset;
        mLimit = limit;
        mLive_type = live_type;
        mGame_type = game_type;
    }

    @Override
    public void getData(boolean isRefresh) {
        RetrofitService.getLiveList(mOffset,mLimit,mLive_type,mGame_type)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mView.showLoading();
                    }
                })
                .compose(mView.<List<LiveListItemBean>>bindToLife())
                .subscribe(new Subscriber<List<LiveListItemBean>>() {
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
                    public void onNext(List<LiveListItemBean> liveListItemBeen) {
                        mView.loadData(liveListItemBeen);
                    }
                });

    }

    @Override
    public void getMoreData() {

    }
}
