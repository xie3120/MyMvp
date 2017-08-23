package com.example.administrator.mymvp.module.channel;

import com.example.administrator.mymvp.local.dao.NewsTypeDao;
import com.example.administrator.mymvp.local.table.NewsTypeInfo;
import com.example.administrator.mymvp.local.table.NewsTypeInfoDao;
import com.example.administrator.mymvp.rxbus.RxBus;
import com.example.administrator.mymvp.rxbus.event.ChannelEvent;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/8/23 0023.
 */

public class ChannelPresenter implements IChannelPresenter<NewsTypeInfo> {

    private final IChannelView mView;
    private final NewsTypeInfoDao mDbDao;
    private final RxBus mRxBus;

    public ChannelPresenter(IChannelView view, NewsTypeInfoDao dbDao, RxBus rxBus) {
        mView = view;
        mDbDao = dbDao;
        mRxBus = rxBus;
    }

    @Override
    public void getData(boolean isRefresh) {
        // 从数据库获取
        final List<NewsTypeInfo> checkList = mDbDao.queryBuilder().list();
        final List<String> typeList = new ArrayList<>();
        for (NewsTypeInfo bean : checkList) {
            typeList.add(bean.getTypeId());
        }
        Observable.from(NewsTypeDao.getAllChannels())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //filter操作符是对源observable产生的结果自定义规则进行过滤，只有满足条件的结果才会提交给订阅者:
                .filter(new Func1<NewsTypeInfo, Boolean>() {
                    @Override
                    public Boolean call(NewsTypeInfo newsTypeInfo) {
                        return !typeList.contains(newsTypeInfo.getTypeId());
                    }
                })
                .toList()
                .subscribe(new Subscriber<List<NewsTypeInfo>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<NewsTypeInfo> newsTypeInfos) {
                        mView.loadData(checkList, newsTypeInfos);
                    }
                });
    }

    @Override
    public void getMoreData() {

    }

    @Override
    public void insert(final NewsTypeInfo data) {
        mDbDao.rx().insert(data)
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<NewsTypeInfo>() {
                    @Override
                    public void onCompleted() {
                        mRxBus.post(new ChannelEvent(ChannelEvent.ADD_EVENT,data));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(NewsTypeInfo newsTypeInfo) {
                        Logger.w(newsTypeInfo.toString());
                    }
                });
    }

    @Override
    public void delete(final NewsTypeInfo data) {
        mDbDao.rx().delete(data)
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Void>() {
                    @Override
                    public void onCompleted() {
                        mRxBus.post(new ChannelEvent(ChannelEvent.DEL_EVENT, data));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e.toString());
                    }

                    @Override
                    public void onNext(Void aVoid) {
                    }
                });
    }

    @Override
    public void update(List<NewsTypeInfo> list) {
        // 这做法不太妥当，而且列表在交互位置时可能产生很多无用的交互没处理掉，暂时没想到简便的方法来处理，以后有想法再改。
        Observable.from(list)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        // 清空数据库
                        mDbDao.deleteAll();
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<NewsTypeInfo>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e.toString());
                    }

                    @Override
                    public void onNext(NewsTypeInfo newsTypeBean) {
                        // 把ID清除再加入数据库会从新按列表顺序递增ID
                        newsTypeBean.setId(null);
                        mDbDao.save(newsTypeBean);
                    }
                });
    }

    @Override
    public void swap(int fromPos, int toPos) {
        mRxBus.post(new ChannelEvent(ChannelEvent.SWAP_EVENT, fromPos, toPos));
    }
}
