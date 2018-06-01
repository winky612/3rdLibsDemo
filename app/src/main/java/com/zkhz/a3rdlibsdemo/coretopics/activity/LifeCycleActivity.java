package com.zkhz.a3rdlibsdemo.coretopics.activity;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * 无论您选择在哪个构建事件中执行初始化操作，请确保使用相应的生命周期事件释放资源。
 * 如果您在ON_START事件之后初始化某些内容，请在ON_STOP事件之后释放或终止它。如果您在ON_RESUME事件之后进行初始化，请在ON_PAUSE事件之后释放
 */

public class LifeCycleActivity extends AppCompatActivity {


    static final String STATE_SCORE = "playerScore";
    static final String STATE_LEVEL = "playerLevel";
    int mCurrentLevel = 0;
    int mCurrentScore = 0;


    /**
     * @param savedInstanceState 它是一个包含活动先前保存状态的Bundle对象。 如果活动从未存在过，则Bundle对象的值为空。
     *                           默认情况下，系统使用Bundle实例状态来保存有关活动布局中每个View对象的信息（例如输入到EditText小部件中的文本值）。
     *                           因此，如果您的活动实例被销毁并重新创建，那么布局的状态将恢复到之前的状态，并且不需要您的代码。
     *                           但是，您的活动可能包含更多想要恢复的状态信息，例如跟踪用户活动进度的成员变量。
     *                           注意：为了使Android系统恢复活动中视图的状态，每个视图必须具有由android：id属性提供的唯一ID。
     *                           <p>
     *                           Bundle对象不适合保存多余的数据量，因为它需要在主线程上进行序列化并消耗系统进程内存。
     *                           要保留超过极少量的数据，您应该采用组合方法来保存数据，使用持久本地存储、onSaveInstanceState（）方法和ViewModel类，如保存UI状态所述。
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    /**
     * 为了保存活动的其他实例状态信息，必须重写onSaveInstanceState（）并将键值对添加到在您的活动意外销毁的事件中保存的Bundle对象。
     * 如果您重写onSaveInstanceState（），如果您希望默认实现保存视图层次结构的状态，则必须调用超类实现
     *
     * 当您的活动开始stop时，系统将调用onSaveInstanceState（）方法，以便您的活动可以将状态信息保存到实例状态包。
     * 此方法的默认实现可以保存有关活动视图层次结构状态的瞬态信息，例如EditText小部件中的文本或ListView小部件的滚动位置。
     *
     * 注意父类的位置
     *
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putInt(STATE_LEVEL, mCurrentLevel);
        outState.putInt(STATE_SCORE, mCurrentScore);

        super.onSaveInstanceState(outState);
    }

    /**
     * 在onStop（）方法中，应用程序应该释放或调整应用程序对用户不可见时不需要的资源。
     * 例如，您的应用可能会暂停动画或从细粒度切换到粗粒度位置更新。即使用户在多窗口模式下查看活动，使用onStop（）而不是onPause（）可确保与UI相关的工作继续进行。
     * <p>
     * 您还应该使用onStop（）执行相对CPU密集型关机操作。
     * 例如，当activity在foreground时,如果找不到更适合将信息保存到数据库的时，则可以在onStop（）期间执行此操作。
     * 以下示例显示了onStop（）的实现，它将草稿备注的内容保存到永久存储中
     */
    @Override
    protected void onStop() {
        super.onStop();

        // save the note's current draft, because the activity is stopping
        // and we want to be sure the current note progress isn't lost.
        ContentValues contentValues = new ContentValues();
        contentValues.put("", "");

        // do this update in background on an AsyncQueryHandler or equivalent
//        mAsyncQueryHandler.startUpdate()

    }


    /**
     * 当您的活动在之前被销毁后重新创建时，您可以从系统传递给您的活动的Bundle中恢复已保存的实例状态。
     * onCreate（）和onRestoreInstanceState（）回调方法都会收到包含实例状态信息的相同Bundle。但onCreate()记得判null
     * 除了onCreate（），您还可以选择实现onRestoreInstanceState（），系统是在onStart（）方法后调用该方法。
     * 只有存在已保存的恢复状态时，系统才会调用onRestoreInstanceState（），因此您不需要检查该Bundle是否为空：
     * @param savedInstanceState
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        mCurrentLevel = savedInstanceState.getInt(STATE_LEVEL);
        mCurrentScore = savedInstanceState.getInt(STATE_SCORE);



    }

    /**
     * 当活动进入停止状态时，与活动生命周期关联的任何生命周期感知组件都将收到ON_DESTROY事件。这是生命周期组件在销毁活动之前可以清理所需的任何内容的位置。
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
