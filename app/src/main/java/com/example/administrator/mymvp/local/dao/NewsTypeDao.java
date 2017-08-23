package com.example.administrator.mymvp.local.dao;

import android.content.Context;


import com.example.administrator.mymvp.local.table.DaoSession;
import com.example.administrator.mymvp.local.table.NewsTypeInfo;
import com.example.administrator.mymvp.local.table.NewsTypeInfoDao;
import com.example.administrator.mymvp.utils.AssetsHelper;
import com.example.administrator.mymvp.utils.GsonHelper;

import java.util.List;

/**
 * Created by long on 2016/8/31.
 * 新闻分类数据访问
 */
public class NewsTypeDao {

    // 所有栏目
    private static List<NewsTypeInfo> sAllChannels;


    private NewsTypeDao() {
    }

    /**
     * 更新本地数据，如果数据库新闻列表栏目为 0 则添加头 3 个栏目
     * @param context
     * @param daoSession
     */
    public static void updateLocalData(Context context, DaoSession daoSession) {
        sAllChannels = GsonHelper.convertEntities(AssetsHelper.readData(context, "NewsChannel"), NewsTypeInfo.class);
        NewsTypeInfoDao beanDao = daoSession.getNewsTypeInfoDao();
        if (beanDao.count() == 0) {
            beanDao.insertInTx(sAllChannels.subList(0, 3));
        }
    }

    /**
     * 获取所有栏目
     * @return
     */
    public static List<NewsTypeInfo> getAllChannels() {
        return sAllChannels;
    }
}
