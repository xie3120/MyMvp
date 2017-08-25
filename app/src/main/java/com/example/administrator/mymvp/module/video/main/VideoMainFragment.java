package com.example.administrator.mymvp.module.video.main;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.example.administrator.mymvp.App;
import com.example.administrator.mymvp.R;
import com.example.administrator.mymvp.module.base.BaseFragment;
import com.example.administrator.mymvp.module.base.IRxBusPresenter;
import com.example.administrator.mymvp.module.video.liveslist.LiveListFragment;

import net.lucode.hackware.magicindicator.MagicIndicator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/8/22 0022.
 */

public class VideoMainFragment extends BaseFragment<IRxBusPresenter> {

    private final List<String> typeIdList = new ArrayList<>();    //直播平台id
    private final List<String> typeNameList = new ArrayList<>();  //直播平台名字
    private final Integer[] logoArrays = new Integer[]{
            0,
            R.drawable.logo_douyu,
            R.drawable.logo_panda,
            R.drawable.logo_zhanqi,
            R.drawable.logo_yy,
            R.drawable.logo_longzhu,
            R.drawable.logo_quanmin,
            R.drawable.logo_cc,
            R.drawable.logo_huomao
    };
    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> titleList = new ArrayList<>();

    @BindView(R.id.magic_indicator)
    MagicIndicator mMagicIndicator;
    @BindView(R.id.tv_live_type)
    TextView mTvLiveType;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_live;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        //直播平台ID
        Collections.addAll(typeIdList, App.getContext().getResources().getStringArray(R.array.live_type_id));
        //直播平台名称
        Collections.addAll(typeNameList, App.getContext().getResources().getStringArray(R.array.live_type_name));
        //顶部栏标题
        titleList.add(getString(R.string.live_lol));
        titleList.add(getString(R.string.live_ow));
        titleList.add(getString(R.string.live_dota2));
        titleList.add(getString(R.string.live_hs));
        titleList.add(getString(R.string.live_csgo));
        fragmentList.add(LiveListFragment.newInstance(getString(R.string.game_type_lol)));
        fragmentList.add(LiveListFragment.newInstance(getString(R.string.game_type_ow)));
        fragmentList.add(LiveListFragment.newInstance(getString(R.string.game_type_dota2)));
        fragmentList.add(LiveListFragment.newInstance(getString(R.string.game_type_hs)));
        fragmentList.add(LiveListFragment.newInstance(getString(R.string.game_type_csgo)));


    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }
}
