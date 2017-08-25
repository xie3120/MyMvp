package com.example.administrator.mymvp.module.news.special;

import com.example.administrator.mymvp.adapter.item.SpecialItem;
import com.example.administrator.mymvp.api.bean.SpecialInfo;
import com.example.administrator.mymvp.module.base.IBaseView;

import java.util.List;

/**
 * Created by Administrator on 2017/8/22 0022.
 */

public interface ISpecialView extends IBaseView {
    /**
     * 显示数据
     * @param specialItems 新闻
     */
    void loadData(List<SpecialItem> specialItems);

    /**
     * 添加头部
     * @param specialBean
     */
    void loadBanner(SpecialInfo specialBean);
}
