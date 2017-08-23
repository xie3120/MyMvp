package com.example.administrator.mymvp.module.base;

/**
 * Created by Administrator on 2017/8/16 0016.
 * 基础的Presenter
 */

public interface IBasePresenter {

    /**
     * 获取网络数据，更新界面
     */
    void getData();


    /**
     * 加载更多数据
     */
    void getMoreData();
}
