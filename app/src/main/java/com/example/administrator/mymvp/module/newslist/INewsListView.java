package com.example.administrator.mymvp.module.newslist;

import com.example.administrator.mymvp.adapter.item.NewsMultiItem;
import com.example.administrator.mymvp.api.bean.NewsInfo;
import com.example.administrator.mymvp.module.base.ILoadDataView;

import java.util.List;

/**
 * Created by Administrator on 2017/8/22 0022.
 * 新闻接口
 */

public interface INewsListView extends ILoadDataView<List<NewsMultiItem>>{

    /**
     * 加载广告数据
     * @param newsBean 新闻
     */
    void loadAdData(NewsInfo newsBean);
}
