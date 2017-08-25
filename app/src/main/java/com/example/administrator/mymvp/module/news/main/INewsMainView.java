package com.example.administrator.mymvp.module.news.main;

import com.example.administrator.mymvp.local.table.NewsTypeInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/8/21 0021.
 * 主页接口
 */

public interface INewsMainView {

    void loadData(List<NewsTypeInfo> checkList);
}
