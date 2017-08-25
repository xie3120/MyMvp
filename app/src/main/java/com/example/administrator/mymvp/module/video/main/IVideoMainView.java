package com.example.administrator.mymvp.module.video.main;

import com.example.administrator.mymvp.api.bean.LiveListItemBean;
import com.example.administrator.mymvp.module.base.IBaseView;

import java.util.List;

/**
 * Created by Administrator on 2017/8/25 0025.
 * 视频主界面
 */

public interface IVideoMainView extends IBaseView{

    /**
     * 显示数据
     * @param listItemBeanList 视频集合
     */
    void loadData(List<LiveListItemBean> listItemBeanList);
}
