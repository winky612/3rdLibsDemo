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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Flowable:
 * 之前我们所的上游和下游分别是Observable和Observer, 这次不一样的是上游变成了Flowable, 下游变成了Subscriber,
 * 但是水管之间的连接还是通过subscribe()
 */

public class RxjavaActivity7 extends AppCompatActivity {

    private static final String TAG = "RxjavaActivity7";
    @BindView(R.id.btn_day03)
    Button btnDay03;
    @BindView(R.id.btn_day04)
    Button btnDay04;
    @BindView(R.id.btn_day05)
    Button btnDay05;
    private Subscription mSubscription;

    @BindView(R.id.btn_day01)
    Button btnDay01;
    @BindView(R.id.btn_day02)
    Button btnDay02;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_day01, R.id.btn_day02,R.id.btn_day03,R.id.btn_day04,R.id.btn_day05})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_day01:

                rxJavaFlowable();
                break;
            case R.id.btn_day02:
                //异步--去掉request()方法
                rxJavaFlowable2();
                break;
            case R.id.btn_day03:

                //同步--去掉request()方法
                rxJavaFlowable3();
                break;
            case R.id.btn_day05:

                //咋知道flowable里默认有一个大小为128的水缸?
                request();
                break;


        }
    }



    /**
     * 同步---去掉s.request()---在上游发送第一个事件之后, 下游就抛出了一个著名的MissingBackpressureException异常,且发送了所有事件, 但下游没有收到任何其余的事件
     *
     * why?----下游没有调用request, 上游就认为下游没有处理事件的能力, 而这又是一个同步的订阅, 既然下游处理不了, 那上游不可能一直等待吧,
     * 如果是这样, 万一这两根水管工作在主线程里, 界面不就卡死了吗, 因此只能抛个异常来提醒我们.
     * 那如何解决这种情况呢, 很简单啦, 下游直接调用request(Long.MAX_VALUE)就行了, 或者根据上游发送事件的数量来request就行了, 比如这里request(3)就可以了.
     */
    private void rxJavaFlowable3() {

        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {

                Log.d(TAG, "emit 1 ");
                e.onNext(1);
                Log.d(TAG, "emit 2 ");
                e.onNext(2);
                Log.d(TAG, "emit 3");
                e.onNext(3);
                Log.d(TAG, "emit complete");
                e.onComplete();

            }
        },BackpressureStrategy.ERROR).subscribe(new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {

                s.request(Long.MAX_VALUE);

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

    /**
     * 异步---去掉s.request()---上游正确的发送了所有的事件, 但是下游一个事件也没有收到.
     * why?---这是因为在Flowable里默认有一个大小为128的水缸, 当上下游工作在不同的线程中时, 上游就会先把事件发送到这个水缸中,
     * 因此, 下游虽然没有调用request, 但是上游在水缸中保存着这些事件, 只有当下游调用request时, 才从水缸里取出事件发给下游.
     *
     * 为啥同步 异步运行结果有这样的差别?---因为Flowable在设计的时候采用了一种新的思路!!!"响应式拉取"!!!的方式来更好的解决上下游流速不均衡的问题,
     * 与我们之前所讲的控制数量和控制速度不太一样, 这种方式用通俗易懂的话来说就好比是叶问打鬼子, 我们把上游看成小日本, 把下游当作叶问,
     * 当调用Subscription.request(1)时, 叶问就说我要打一个! 然后小日本就拿出一个鬼子给叶问, 让他打,
     * 等叶问打死这个鬼子之后, 再次调用request(10), 叶问就又说我要打十个! 然后小日本又派出十个鬼子给叶问, 然后就在边上看热闹, 看叶问能不能打死十个鬼子,
     * 等叶问打死十个鬼子后再继续要鬼子接着打...
     *
     * 所以我们把request当做是一种能力, 当成下游处理事件的能力, 下游能处理几个就告诉上游我要几个, 这样只要上游根据下游的处理能力来决定发送多少事件, 就不会造成一窝蜂的发出一堆事件来, 从而导致OOM.
     * 这也就完美的解决之前我们所学到的两种方式的缺陷, 过滤事件会导致事件丢失, 减速又可能导致性能损失. 而这种方式既解决了事件丢失的问题, 又解决了速度的问题, 完美 !
     *
     * 比如这里需要注意的是, 只有当上游正确的实现了如何根据下游的处理能力来发送事件的时候, 才能达到这种效果,
     * 如果上游根本不管下游的处理能力, 一股脑的瞎发事件, 仍然会产生上下游流速不均衡的问题
     * 那么如何正确的去实现上游呢, 这里先卖个关子, 之后我们再来讲解.
     */
    private void rxJavaFlowable2() {

        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {

                Log.d(TAG, "emit 1 ");
                e.onNext(1);
                Log.d(TAG, "emit 2 ");
                e.onNext(2);
                Log.d(TAG, "emit 3");
                e.onNext(3);
                Log.d(TAG, "emit complete");
                e.onComplete();

            }
        }, BackpressureStrategy.ERROR)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {

                        Log.d(TAG, "onSubscribe: ");
                        mSubscription = s;//把Subscription保存起来

                        //s.request()干啥用的?没有这行代码会怎样?


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

    private void request() {

        mSubscription.request(1);
    }

    /**
     * 运行结果显示:在上游发送第一个事件之后, 下游就抛出了一个著名的MissingBackpressureException异常, 并且下游没有收到任何其余的事件,上游发送完了所有事件
     */
    private void rxJavaFlowable() {

        Flowable<Integer> upStream = Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {

                Log.d(TAG, "emit 1 ");
                e.onNext(1);
                Log.d(TAG, "emit 2 ");
                e.onNext(2);
                Log.d(TAG, "emit 3 ");
                e.onNext(3);
                Log.d(TAG, "emit ");


            }
        }, BackpressureStrategy.ERROR);//增加了一个参数
        //背压,也就是出现上下游流速不均衡的时候应该怎么处理的办法
        //BackpressureStrategy.ERROR这种方式, 这种方式会在出现上下游流速不均衡的时候直接抛出一个异常,这个异常就是著名的MissingBackpressureException

        Subscriber<Integer> downStream = new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {

                //调用Subscription.cancel()也可以切断水管
                s.request(Long.MAX_VALUE);//注意这句代码
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
        };

        upStream.subscribe(downStream);

    }
}
