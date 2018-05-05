package com.zkhz.a3rdlibsdemo.rxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zkhz.a3rdlibsdemo.R;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RxjavaActivity8 extends AppCompatActivity {

    private static final String TAG = "RxjavaActivity8";

    @BindView(R.id.btn_day01)
    Button btnDay01;
    @BindView(R.id.btn_day02)
    Button btnDay02;
    @BindView(R.id.btn_day03)
    Button btnDay03;
    @BindView(R.id.btn_day04)
    Button btnDay04;
    @BindView(R.id.btn_day05)
    Button btnDay05;

    private Subscription mSubscription;

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

                rxJava();
                break;
            case R.id.btn_day02:

                rxJavaDrop();

                break;
            case R.id.btn_day03:

                rxJavaLatest();

                break;
            case R.id.btn_day04:

                rxJavaInterval();

                break;
            case R.id.btn_day05:

                request();

                break;
        }
    }

    /**
     * 有些朋友要问了, 这些FLowable是我自己创建的, 所以我可以选择策略, 那面对有些FLowable并不是我自己创建的, 该怎么办呢?
     * 比如RxJava中的interval操作符, 这个操作符并不是我们自己创建的
     *
     * 虽然不是我们自己创建的, 但是RxJava给我们提供了其他的方法:
       onBackpressureBuffer()
       onBackpressureDrop()
       onBackpressureLatest()
     *
     * interval操作符发送Long型的事件, 从0开始, 每隔指定的时间就把数字加1并发送出来, 在这个例子里, 我们让它每隔1毫秒就发送一次事件, 在下游延时1秒去接收处理,
     */
    private void rxJavaInterval() {

        Flowable.interval(1, TimeUnit.MICROSECONDS)
                .onBackpressureDrop() //加上背压策略
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onSubscribe(Subscription s) {

                        Log.d(TAG, "onSubscribe: ");
                        mSubscription=s;
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(Long aLong) {

                        Log.d(TAG, "onNext: "+aLong);
                        try {
                            Thread.sleep(1000);//延时1秒
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(Throwable t) {

                        Log.w(TAG, "onError: ",t );

                    }

                    @Override
                    public void onComplete() {

                        Log.d(TAG, "onComplete: ");

                    }
                });


    }

    private void request() {
        mSubscription.request(128);
    }


    /**
     * 想想看我们之前学习Observable的时候说到的如何解决上游发送事件太快的, 有一招叫从数量上取胜,
     * 同样的FLowable中也有这种方法, 对应的就是BackpressureStrategy.DROP和BackpressureStrategy.LATEST这两种策略
     * <p>
     * Drop就是直接把存不下的事件丢弃,Latest就是只保留最新的事件
     *
     * 上游发送无数事件,Latest看上去好像和Drop差不多啊, Latest也首先保存了0-127这128个事件, 等下游把这128个事件处理了之后才进行之后的处理, 光从这里没有看出有任何区别啊...
     *
     * 改为发送有限数量的时间看看,这里用1w试试
     *
     * 运行结果显示:除去前面128个事件, 与Drop不同, Latest总是能获取到最后最新的事件, 
     */
    private void rxJavaDrop() {

        Flowable.create(new FlowableOnSubscribe<Integer>() {

            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {

                for (int i = 0; i<100000; i++) {//只发1w个事件

                    e.onNext(i);

                }

            }
        }, BackpressureStrategy.DROP)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {

                        Log.d(TAG, "onSubscribe");
                        mSubscription = s;
                        s.request(128);
                    }

                    @Override
                    public void onNext(Integer integer) {

                        Log.d(TAG, "onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable t) {

                        Log.w(TAG, "onError: ", t);

                    }

                    @Override
                    public void onComplete() {

                        Log.d(TAG, "onComplete");

                    }
                });

    }

    private void rxJavaLatest() {

        Flowable.create(new FlowableOnSubscribe<Integer>() {

            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {

                for (int i = 0; i<100000; i++) {

                    e.onNext(i);
                }

            }
        }, BackpressureStrategy.LATEST)
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        Log.d(TAG, "onSubscribe");
                        mSubscription = s;
                        mSubscription.request(128);
                    }

                    @Override
                    public void onNext(Integer integer) {

                        Log.d(TAG, "onNext: " + integer);

                    }

                    @Override
                    public void onError(Throwable t) {

                        Log.w(TAG, "onError: ", t);

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete");
                    }
                });




    }

    /**
     * 发送128个事件没有问题是因为FLowable内部有一个大小为128的水缸, 超过128就会装满溢出来, 那既然你水缸这么小, 那我给你换一个大水缸如何?
     *
     * 这次我们直接让上游发送了1000个事件,下游仍然不调用request去请求, 与之前不同的是, 这次我们用的策略是BackpressureStrategy.BUFFER,
     * 这就是我们的新水缸啦, 这个水缸就比原来的水缸牛逼多了,它没有大小限制, 因此可以存放许许多多的事件.
     *
     * 不知道大家有没有发现, 换了水缸的FLowable和Observable好像是一样的嘛...不错,如果你像这样单纯的使用FLowable, 同样需要注意OOM的问题
     * 比如,发送上游发送无数条事件,就会OOM
     *
     */
    private void rxJava() {

        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {

                for (int i = 0; i < 1000; i++) {

                    Log.d(TAG, "emit: " + i);
                    e.onNext(i);
                }
            }
        }, BackpressureStrategy.BUFFER)//新水缸
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {

                        Log.d(TAG, "onSubscribe: ");
                        mSubscription = s;


                    }

                    @Override
                    public void onNext(Integer integer) {

                        Log.d(TAG, "onNext: " + integer);

                    }

                    @Override
                    public void onError(Throwable t) {

                        Log.w(TAG, "onError: ", t);

                    }

                    @Override
                    public void onComplete() {

                        Log.d(TAG, "onComplete: ");

                    }
                });


    }
}
