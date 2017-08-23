package com.example.administrator.mymvp.adapter;

import android.content.Context;

import com.dl7.recycler.adapter.BaseQuickAdapter;
import com.dl7.recycler.adapter.BaseViewHolder;
import com.example.administrator.mymvp.R;
import com.example.administrator.mymvp.local.table.NewsTypeInfo;

import java.util.List;

/**
 * Created by long on 2016/8/31.
 * 管理界面适配器
 */
public class ManageAdapter extends BaseQuickAdapter<NewsTypeInfo> {

    public ManageAdapter(Context context) {
        super(context);
    }

    public ManageAdapter(Context context, List<NewsTypeInfo> data) {
        super(context, data);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.adapter_manage;
    }

    @Override
    protected void convert(BaseViewHolder holder, NewsTypeInfo item) {
        holder.setText(R.id.tv_channel_name, item.getName());
    }
}
