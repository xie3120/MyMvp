package com.example.administrator.mymvp.module.base;

import rx.functions.Action1;

/**
 * Created by Administrator on 2017/8/21 0021.
 * RxBus Presenter
 */

public interface IRxBusPresenter extends IBasePresenter{

    <T> void registerRxBus(Class<T> eventType, Action1<T> action);

    void unregisterRxBus();
}
