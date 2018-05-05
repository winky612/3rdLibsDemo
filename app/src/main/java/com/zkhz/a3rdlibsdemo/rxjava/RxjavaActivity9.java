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

import java.io.BufferedReader;
import java.io.FileReader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RxjavaActivity9 extends AppCompatActivity {

    private static final String TAG = "RxjavaActivity9";
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
    @BindView(R.id.btn_day06)
    Button btnDay06;

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

                //上游得知下游处理能力
                rxJavaDay09();
                break;
            case R.id.btn_day02:

                rxJava();
                break;
            case R.id.btn_day03:

                rxJava2();
                break;
            case R.id.btn_day04:

                rxJava3();
                break;
            case R.id.btn_day05:

                request();

                break;
            case R.id.btn_day06:

                rxRead();

                try {
                    Thread.sleep(10000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;


        }
    }

    /**
     *
     * Demo---这个例子是读取一个文本文件，需要一行一行读取，然后处理并输出，如果文本文件很大的时候，比如几十M的时候，全部先读入内存肯定不是明智的做法，因此我们可以一边读取一边处理
     */
    private void rxRead() {

        practice1();


    }

    /**
     * 在某一些场景下，可以在发送事件前先判断当前的requested的值是否大于0，若等于0则说明下游处理不过来了，则需要等待
     */
    private void practice1() {

        Flowable.create(new FlowableOnSubscribe<String>() {

            @Override
            public void subscribe(FlowableEmitter<String> e) throws Exception {

                FileReader reader = new FileReader("test.txt");
                BufferedReader bufferedReader = new BufferedReader(reader);

                String str;

                while ((str = bufferedReader.readLine()) != null && e.isCancelled()) {
                    while (e.requested() == 0) {
                        if (e.isCancelled()) {
                            break;
                        }
                    }

                    e.onNext(str);
                }

                bufferedReader.close();
                reader.close();

                e.onComplete();

            }
        }, BackpressureStrategy.ERROR)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onSubscribe(Subscription s) {

                        mSubscription = s;
                        s.request(1);
                    }

                    @Override
                    public void onNext(String s) {

                        Log.d(TAG, "onNext: " + s);

                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    private void request() {

        mSubscription.request(96);//请求96个事件
    }


    /**
     * 异步
     * <p>
     * 一开始上游的requested的值是128，那这128个事件发送完了不就不能继续发送了吗？
     * 刚刚说了，设置上游requested的值的这个!!"内部调用"会在"合适的时候"!!自动触发，那到底什么时候是合适的时候呢？是发完128个事件才去调用吗？还是发送了一半才去调用呢？
     * <p>
     * <p>
     * 结论----当"下游"每消费96个事件便会自动触发内部的request()去设置"上游"的requested的值啊！没错，就是这样，而这个新的值就是96。
     */
    private void rxJava3() {

        Flowable.create(new FlowableOnSubscribe<Integer>() {

            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {

                Log.d(TAG, "First requested = " + e.requested());
                boolean flag;

                //首先仍然是个无限循环发事件，但是是有条件的，只有当上游的requested != 0的时候才会发事件????????
                for (int i = 0; ; i++) {
                    flag = false;
                    while (e.requested() == 0) {
                        if (!flag) {
                            Log.d(TAG, "subscribe: Oh no! I can't emit value!");
                            flag = true;
                        }
                    }

                    e.onNext(i);
                    Log.d(TAG, "emit: " + i + "requested = " + e.requested());

                }


            }
        }, BackpressureStrategy.ERROR)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {

                        Log.d(TAG, "onSubscribe");
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

                        Log.d(TAG, "onComplete");

                    }
                });


    }


    /**
     * 异步订阅
     * <p>
     * why?---当上下游工作在不同的线程里时，每一个线程里都有一个requested，而我们调用request（1000）时，实际上改变的是下游主线程中的requested，
     * 而上游中的requested的值是由RxJava内部调用request(n)去设置的，这个调用会在合适的时候自动触发。
     * 因此即使下游没有调用request()，上游也能发送128个事件，这也可以解释之前我们为什么说Flowable中默认的水缸大小是128，其实就是这里设置的。
     */
    private void rxJava2() {

        Flowable.create(new FlowableOnSubscribe<Integer>() {

            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {

                Log.d(TAG, "current requested: " + e.requested());

            }
        }, BackpressureStrategy.ERROR)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {

                        Log.d(TAG, "onSubscribe");
                        mSubscription = s;
                        s.request(1000);//无论下游有没有request()方法,运行结果都显示 能处理的事件为128个!!

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
     * 同步订阅
     * <p>
     * 那什么时候做减法呢？--------当然是发送事件啦!!!!
     * 下游调用request(n) 告诉上游它的处理能力，上游每发送一个next事件之后，requested就减一，
     * 注意是next事件，complete和error事件不会消耗requested，
     * 当减到0时，则代表下游没有处理能力了，这个时候你如果继续发送事件，会发生什么后果呢？当然是MissingBackpressureException啦
     */
    private void rxJava() {

        Flowable.create(new FlowableOnSubscribe<Integer>() {

            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {

                Log.d(TAG, "before emit, requested = " + e.requested());

                Log.d(TAG, "emit: 1 ");
                e.onNext(1);
                Log.d(TAG, "after emit 1, requested = " + e.requested());
                Log.d(TAG, "emit: 1 ");

                Log.d(TAG, "emit: 2 ");
                e.onNext(2);
                Log.d(TAG, "after emit 2, requested = " + e.requested());
                Log.d(TAG, "emit: 2 ");

                Log.d(TAG, "emit: 3 ");
                e.onNext(3);
                Log.d(TAG, "after emit 3, requested = " + e.requested());
                Log.d(TAG, "emit: 3 ");

                e.onNext(1);
                Log.d(TAG, "after emit 1, requested = " + e.requested());

            }
        }, BackpressureStrategy.ERROR)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {

                        mSubscription = s;
                        s.request(10);

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
     * 在我们前两节中的例子中，我们口中声称的响应式拉并没有完全体现出来.
     * 虽然我们在下游中是每次处理掉了一个事件之后才调用request(1)去请求下一个事件，也就是说叶问的确是在打死了一个鬼子之后才继续打下一个鬼子，
     * 可是上游呢？上游真的是每次当下游请求一个才拿出一个吗？并不是
     * 也就是说小日本并没有等叶问打死一个才拿出一个，而是一开始就拿出了所有的鬼子，这些鬼子从一开始就在这儿排队等着被打死
     * <p>
     * 那么上游要根据下游的处理能力正确的去发送事件，那么上游是不是应该知道下游的处理能力是多少啊，对吧，不然，一个巴掌拍不响啊，这种事情得你情我愿才行。
     * 那么上游从哪里得知下游的处理能力呢??我们来看看上游最重要的部分，肯定就是FlowableEmitter了啊，我们就是通过它来发送事件的啊
     * <p>
     * <p>
     * 同步:
     */
    private void rxJavaDay09() {

        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {

                Log.d(TAG, "current requested: " + e.requested());//当前外部请求的数量

            }
        }, BackpressureStrategy.ERROR)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {

                        mSubscription = s;
                        s.request(10); //我要打十个
                        s.request(100); //那要是下游多次请求呢？比如 再给我一百个！----->下游会先调用了request(10), 然后又调用了request(100),做了加法  下游不请求,则requested值为0

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
}
