package com.example.administrator.mymvp.module.my;

import com.example.administrator.mymvp.R;
import com.example.administrator.mymvp.module.base.BaseFragment;
import com.example.administrator.mymvp.module.base.IRxBusPresenter;

/**
 * Created by Administrator on 2017/8/22 0022.
 */

public class MyMainFragment extends BaseFragment<IRxBusPresenter> {


    @Override
    protected int attachLayoutRes() {
        return R.layout.test_my;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }
}
