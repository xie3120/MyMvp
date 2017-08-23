package com.example.administrator.mymvp.adapter.item;

import android.support.annotation.IntDef;

import com.dl7.recycler.entity.MultiItemEntity;
import com.example.administrator.mymvp.api.bean.NewsInfo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Administrator on 2017/8/22 0022.
 */

public class NewsMultiItem extends MultiItemEntity {

    public static final int ITEM_TYPE_NORMAL = 1;
    public static final int ITEM_TYPE_PHOTO_SET = 2;

    private NewsInfo mNewsBean;

    public NewsMultiItem(@NewsItemType int itemType, NewsInfo newsBean) {
        super(itemType);
        mNewsBean = newsBean;
    }

    public NewsInfo getNewsBean() {
        return mNewsBean;
    }

    public NewsMultiItem(int itemType) {
        super(itemType);
    }

    public void setNewsBean(NewsInfo newsBean) {
        mNewsBean = newsBean;
    }

    @Override
    public void setItemType(@NewsItemType int itemType) {
        super.setItemType(itemType);
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ITEM_TYPE_NORMAL, ITEM_TYPE_PHOTO_SET})
    public @interface NewsItemType {}
}
