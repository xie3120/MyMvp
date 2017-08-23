package com.example.administrator.mymvp.module.home;

import android.content.Intent;
import android.os.Bundle;

import com.example.administrator.mymvp.R;
import com.example.administrator.mymvp.module.base.BaseActivity;
import com.example.administrator.mymvp.utils.RxHelper;
import com.example.administrator.mymvp.widget.SimpleButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public class SplashActivity extends BaseActivity {


    @BindView(R.id.sb_skip)
    SimpleButton mSbSkip;

    private boolean mIsSkip = false;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void updateViews(boolean isRefresh) {
        RxHelper.countdown(5)
                .compose(this.<Integer>bindToLife())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        _doSkip();
                    }

                    @Override
                    public void onError(Throwable e) {
                        _doSkip();
                    }

                    @Override
                    public void onNext(Integer integer) {
                        mSbSkip.setText("跳过 " + integer);
                    }
                });

    }

    private void _doSkip() {
        if (!mIsSkip){
            mIsSkip = true;
            finish();
            startActivity(new Intent(SplashActivity.this,MainActivity.class));
            overridePendingTransition(R.anim.hold, R.anim.zoom_in_exit);
        }
    }

    @Override
    public void onBackPressed() {
        // 不响应后退键
        return;
    }

    @OnClick(R.id.sb_skip)
    public void onClick() {
        _doSkip();
    }


}
