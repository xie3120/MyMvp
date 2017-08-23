package com.example.administrator.mymvp.adapter.item;

import com.dl7.recycler.entity.SectionEntity;
import com.example.administrator.mymvp.api.bean.NewsItemInfo;

public class SpecialItem extends SectionEntity<NewsItemInfo> {

    public SpecialItem(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public SpecialItem(NewsItemInfo newsItemBean) {
        super(newsItemBean);
    }
}

