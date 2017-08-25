package com.example.administrator.mymvp.module.home;

import android.Manifest;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;

import com.example.administrator.mymvp.R;
import com.example.administrator.mymvp.module.base.BaseActivity;
import com.example.administrator.mymvp.module.base.BaseFragment;
import com.example.administrator.mymvp.module.my.MyMainFragment;
import com.example.administrator.mymvp.module.news.main.NewsMainFragment;
import com.example.administrator.mymvp.module.video.main.VideoMainFragment;
import com.example.administrator.mymvp.utils.SnackbarUtils;
import com.example.administrator.mymvp.widget.FlexibleViewPager;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;
import rx.functions.Action1;

/**
 * ┌───┐   ┌───┬───┬───┬───┐ ┌───┬───┬───┬───┐ ┌───┬───┬───┬───┐ ┌───┬───┬───┐
 * │Esc│   │ F1│ F2│ F3│ F4│ │ F5│ F6│ F7│ F8│ │ F9│F10│F11│F12│ │P/S│S L│P/B│  ┌┐    ┌┐    ┌┐
 * └───┘   └───┴───┴───┴───┘ └───┴───┴───┴───┘ └───┴───┴───┴───┘ └───┴───┴───┘  └┘    └┘    └┘
 * ┌───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───────┐ ┌───┬───┬───┐ ┌───┬───┬───┬───┐
 * │~ `│! 1│@ 2│# 3│$ 4│% 5│^ 6│& 7│* 8│( 9│) 0│_ -│+ =│ BacSp │ │Ins│Hom│PUp│ │N L│ / │ * │ - │
 * ├───┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─────┤ ├───┼───┼───┤ ├───┼───┼───┼───┤
 * │ Tab │ Q │ W │ E │ R │ T │ Y │ U │ I │ O │ P │{ [│} ]│ | \ │ │Del│End│PDn│ │ 7 │ 8 │ 9 │   │
 * ├─────┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴─────┤ └───┴───┴───┘ ├───┼───┼───┤ + │
 * │ Caps │ A │ S │ D │ F │ G │ H │ J │ K │ L │: ;│" '│ Enter  │               │ 4 │ 5 │ 6 │   │
 * ├──────┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴────────┤     ┌───┐     ├───┼───┼───┼───┤
 * │ Shift  │ Z │ X │ C │ V │ B │ N │ M │< ,│> .│? /│  Shift   │     │ ↑ │     │ 1 │ 2 │ 3 │   │
 * ├─────┬──┴─┬─┴──┬┴───┴───┴───┴───┴───┴──┬┴───┼───┴┬────┬────┤ ┌───┼───┼───┐ ├───┴───┼───┤ E││
 * │ Ctrl│    │Alt │         Space         │ Alt│    │    │Ctrl│ │ ← │ ↓ │ → │ │   0   │ . │←─┘│
 * └─────┴────┴────┴───────────────────────┴────┴────┴────┴────┘ └───┴───┴───┘ └───────┴───┴───┘
 */

public class MainActivity extends BaseActivity {

    @BindView(R.id.fl_container)
    FlexibleViewPager mFlContainer;
    @BindView(R.id.tab)
    PageNavigationView mTab;

    // 本来想用这个来存储Fragment做切换，不过貌似fragment会被回收产生异常，估计内存占用太大
//    private SparseArray<Fragment> mSparseFragments = new SparseArray<>();
    private SparseArray<String> mSparseTags = new SparseArray<>();
    private List<BaseFragment> mFragments = new ArrayList<>();
    private String[] titles = {"News","Live","Info"};
    private NavigationController mController;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        mFlContainer.setCanScroll(false);

        mController = mTab.material()
                .addItem(R.drawable.ic_explore_normal, R.drawable.ic_explore_pressed, "News")
                .addItem(R.drawable.ic_live_normal, R.drawable.ic_live_pressed, "Live")
                .addItem(R.drawable.ic_user_normal, R.drawable.ic_user_pressed, "Info")
                .build();

        mFragments.add(new NewsMainFragment());
        mFragments.add(new VideoMainFragment());
        mFragments.add(new MyMainFragment());

        mFlContainer.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mController.getItemCount();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        });

        mController.setupWithViewPager(mFlContainer);
        _getPermission();
    }

    @Override
    protected void updateViews(boolean isRefresh) {
        mController.addTabItemSelectedListener(new OnTabItemSelectedListener() {
            @Override
            public void onSelected(int index, int old) {
                //选中时触发 mFlContainer.setCurrentItem(index);
            }

            @Override
            public void onRepeat(int index) {
                //重复选中时触发
            }
        });
    }


    private void _getPermission() {
        new RxPermissions(this).request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean granted) {
                        if (granted) {
                           /* final File dir = new File(FileDownloader.getDownloadDir());
                            if (!dir.exists() || !dir.isDirectory()) {
                                dir.delete();
                                dir.mkdirs();
                            }*/
                        } else {
                            SnackbarUtils.showSnackbar(MainActivity.this, "获取读写文件权限失败", true);
                        }
                    }
                });
    }
}
