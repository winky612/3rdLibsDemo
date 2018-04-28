package com.zkhz.a3rdlibsdemo.rxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zkhz.a3rdlibsdemo.R;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/4/28 0028.
 */

public class RxjavaActivity6 extends AppCompatActivity {
    @BindView(R.id.btn_day01)
    Button btnDay01;
    @BindView(R.id.btn_day02)
    Button btnDay02;

    private static String TAG = "RxjavaActivity6";
    @BindView(R.id.btn_day03)
    Button btnDay03;
    @BindView(R.id.btn_day04)
    Button btnDay04;
    @BindView(R.id.btn_day05)
    Button btnDay05;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_day01, R.id.btn_day02, R.id.btn_day03, R.id.btn_day04, R.id.btn_day05})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_day01:

                rxJavaDay06();
                break;
            case R.id.btn_day02:
                rxJavaTest();
                break;
            case R.id.btn_day03:

                rxJavaTest2();
                break;
            case R.id.btn_day04:

                rxJavaZipSolve();
                break;
            case R.id.btn_day05:
                rxJavaZipSolve2();
                break;


        }
    }


    //解决oom---法1
    //上游发送的所有事件都放到水缸里了, 所以瞬间水缸就满了, 那我们可以只放我们需要的事件到水缸里呀, 只放一部分数据到水缸里, 这样不就不会溢出来了吗
    //filter
    private void rxJavaDay06() {

        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {

                for (int i = 0; ; i++) {

                    e.onNext(i);
                }
            }
        })
                .subscribeOn(Schedulers.io())
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {

                        //只允许能被10整除的事件通过
                        //通过减少进入水缸的事件数量的确可以缓解上下游流速不均衡的问题, 但是力度还不够
                        return integer % 10 == 0;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {

                        Log.d(TAG, "accept: " + integer);
                    }
                });


    }


    /**
     * 解决oom---法2
     * sample操作符---每隔指定的时间就从上游中取出一个事件发送给下游
     * 虽然上游仍然一直在不停的发事件, 但是我们只是每隔一定时间取一个放进水缸里, 并没有全部放进水缸里
     * <p>
     * 总结:这两种方法归根到底其实就是减少放进水缸的事件的数量, 是以数量取胜, 但是这个方法有个缺点, 就是丢失了大部分的事件.
     */
    private void rxJavaTest() {

        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {

                for (int i = 0; ; i++) {

                    e.onNext(i);

                }
            }
        })
                .subscribeOn(Schedulers.io())
                .sample(2, TimeUnit.SECONDS)//sample取样
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {

                        Log.d(TAG, "accept: " + integer);
                    }
                });

    }


    /**
     * 换一个角度来思考, 既然上游发送事件的速度太快, 那我们就适当减慢发送事件的速度, 从速度上取胜
     * <p>
     * 上游通过适当的延时, 不但减缓了事件进入水缸的速度, 也可以让下游有充足的时间从水缸里取出事件来处理 ,
     * 这样一来, 就不至于导致大量的事件涌进水缸, 也就不会OOM啦
     * <p>
     * 总结:本节中的治理的办法就两种:
     * 一是从数量上进行治理, 减少发送进水缸里的事件
     * 二是从速度上进行治理, 减缓事件发送进水缸的速度
     */
    private void rxJavaTest2() {

        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {

                for (int i = 0; ; i++) {

                    e.onNext(i);
                    Thread.sleep(2000);//每次发送完事件延时2秒
                }
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {

                        Log.d(TAG, "accept: " + integer);
                    }
                });
    }


    /**
     * 解决上节zip的oom------法1:sample
     */
    private void rxJavaZipSolve() {

        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {

                for (int i = 0; ; i++) {

                    e.onNext(i);
                }
            }
        })
                .subscribeOn(Schedulers.io())
                .sample(2, TimeUnit.SECONDS);

        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {

                e.onNext("A");
            }
        }).subscribeOn(Schedulers.io());


        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {

                return integer + s;
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {

                        Log.d(TAG, "accept: " + s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        Log.w(TAG, "accept: throwable ", throwable);
                    }
                });
    }


    /**
     * 解决上节zip的oom------法2:减缓发送事件速度
     */
    private void rxJavaZipSolve2() {

        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {

                for (int i = 0; ; i++) {

                    e.onNext(i);
                    Thread.sleep(2000);

                }
            }
        }).subscribeOn(Schedulers.io());

        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {

                e.onNext("A");
            }
        }).subscribeOn(Schedulers.io());

        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {

                return integer + s;
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {

                        Log.d(TAG, "accept: " + s);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        Log.w(TAG, "accept: throwable", throwable);
                    }
                });

    }
}
