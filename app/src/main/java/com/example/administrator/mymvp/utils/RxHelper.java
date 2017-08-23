package com.example.administrator.mymvp.utils;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/8/17 0017.
 */

public class RxHelper {

    public RxHelper() {
    }

    /**
     * 倒计时
     * @param time
     * @return
     */
    public static Observable<Integer> countdown(int time){
        if (time < 0){
            time = 0;
        }

        final int countTime = time;
        //interval操作符来间隔执行.
        /*2、map函数只有一个参数，参数一般是Func1，Func1的<I,O>I,O模版分别为输入和输出值的类型，实现Func1的call方法对I类型进行处理后返回O类型数据
        3、flatMap函数也只有一个参数，也是Func1,Func1的<I,O>I,O模版分别为输入和输出值的类型，实现Func1的call方法对I类型进行处理后返回O类型数据，不过这里O为Observable类型*/

        /*take操作符和interval操作符联合使用，由于一旦interval计时开始除了解绑就无法停止，所以只有在onNext方法中计算一旦释放到10秒的时候再进行解绑从而终结该计时。
        但使用take操作符就简单很多了，它的意思是只释放前N项，和计时配合使用的话，就可以不用担心计时会一直执行，10秒过后它会自动结束。*/
        return Observable.interval(0,1, TimeUnit.SECONDS)
                .map(new Func1<Long, Integer>() {
                    @Override
                    public Integer call(Long increaseTime) {
                        return countTime - increaseTime.intValue();
                    }
                }).take(countTime + 1)//因为数字不能小于0
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());

    }
}
