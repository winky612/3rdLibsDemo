package com.zkhz.a3rdlibsdemo.rxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zkhz.a3rdlibsdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2018/4/24 0024.
 */

public class RxjavaActivity extends AppCompatActivity {
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

    private String TAG = "RxjavaActivity";

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
                rxjava_day01();
                break;
            case R.id.btn_day02:
                break;
            case R.id.btn_day03:
                break;
            case R.id.btn_day04:
                break;
            case R.id.btn_day05:
                break;
        }
    }

    private void rxjava_day01() {

        //1--创建被观察者Observable(上游 事件产生者)   发送一个,接收一个
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {

                //ObservableEmitter： Emitter是发射器的意思,可以分别发出next事件、complete事件和error事件

                Log.d(TAG, "emit 1 ");
                e.onNext(1);

                Log.d(TAG, "emit 2 ");
                e.onNext(2);

                Log.d(TAG, "emit 3 ");
                e.onNext(3);

                //当上游发送了一个onComplete后, 上游onComplete之后的事件将会继续发送, 而下游收到onComplete事件之后将不再继续接收事件
                Log.d(TAG, "emit complete ");
                e.onComplete();

                Log.d(TAG, "emit 4 ");
                e.onNext(4);

            }
        });

        //2--创建一个观察者Observer(下游 事件接收者)
        Observer<Integer> observer = new Observer<Integer>() {

            private int i;
            private Disposable disposable;

            @Override
            public void onSubscribe(Disposable d) {

                //当调用它的dispose()方法时, 它就会将两根管道(上下游事件)切断, 从而导致下游收不到事件.但并不会导致上游不再继续发送事件, 上游会继续发送剩余的事件.
                Log.d(TAG, "onSubscribe: ");
                disposable=d;
            }

            @Override
            public void onNext(Integer value) {

                //这里面是 上游每发送1次事件 下游就会执行一遍onNext()方法,知道上游发送onComplete()或者onError()则停止
                Log.d(TAG, "onNext: " + value);
                i++;
                Log.d(TAG, "i: "+i);
                if (i==2){
                    Log.d(TAG, "dispose:");
                    disposable.dispose();//下游收到第二个事件之后, 切断水管
                    Log.d(TAG, "isDisposed: "+disposable.isDisposed());
                }


            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");

            }

            @Override
            public void onComplete() {

                Log.d(TAG, "onComplete: ");

            }
        };

        //3--建立连接
        //注意: 只有当上游和下游建立连接之后, 上游才会开始发送事件. 也就是调用了subscribe() 方法之后才开始发送事件.
        observable.subscribe(observer);


    }
}
