package com.zkhz.a3rdlibsdemo.coretopics.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.zkhz.a3rdlibsdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExampleFragmentActivity extends AppCompatActivity {
    @BindView(R.id.fl_container)
    FrameLayout flContainer;
    @BindView(R.id.fg2)
    Example2Fragment fg2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_fragment);
        ButterKnife.bind(this);

        loadFragment1();
        loadFragment2();

    }

    //可以通过两种方式向 Activity 布局添加片段：
    //法2.在 Activity 的布局文件内声明片段
    private void loadFragment2() {

        fg2 = (Example2Fragment) getSupportFragmentManager().findFragmentById(R.id.fg2);


    }

    //法1.通过编程方式将片段添加到某个现有 ViewGroup
    private void loadFragment1() {

        /**
         * ①
         * FragmentManager--管理您的 Activity 中的片段
         * 您可以使用 FragmentManager 执行的操作包括：
         *          通过 findFragmentById()（对于在 Activity 布局中提供 UI 的片段）或 findFragmentByTag()（对于提供或不提供 UI 的片段）获取 Activity 中存在的片段。
         *          通过 popBackStack()（模拟用户发出的返回命令）将片段从返回栈中弹出。
         *          通过 addOnBackStackChangedListener() 注册一个侦听返回栈变化的侦听器。
         */
        FragmentManager fragmentManager = getSupportFragmentManager();

        /**
         * ②
         * 您提交给 Activity 的每组更改都称为事务,想咋改?---可以调用add()、remove() 和 replace() 等方法为给定事务设置您想要执行的所有更改;
         *
         * 您也可以将每个事务保存到由 Activity 管理的返回栈内，从而让用户能够回退片段更改（类似于回退 Activity）。
         *
         */
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ExampleFragment exampleFragment = new ExampleFragment();

        //使用 add() 方法添加一个片段，指定要添加的片段以及将其插入哪个视图
        fragmentTransaction.add(R.id.fl_container, exampleFragment);

        /**
         * ③
         * 在您调用 commit() 之前，您可能想调用 addToBackStack()，以将事务添加到片段事务返回栈。 该返回栈由 Activity 管理，允许用户通过按返回按钮返回上一片段状态。
         *
         * 如果您向事务添加了多个更改（如又一个 add() 或 remove()），并且调用了 addToBackStack()，则在调用 commit() 前应用的所有更改都将作为单一事务添加到返回栈，并且返回按钮会将它们一并撤消。
         *
         * 如果您没有在执行移除片段的事务时调用 addToBackStack()，则事务提交时该片段会被销毁，用户将无法回退到该片段。
         * 不过，如果您在删除片段时调用了 addToBackStack()，则系统会停止该片段，并在用户回退时将其恢复。
         */
        fragmentTransaction.addToBackStack(null);

        /**
         * ④
         * 一旦您通过 FragmentTransaction 做出了更改，就必须调用 commit() 以使更改生效。
         * 调用 commit() 不会立即执行事务，而是在 Activity 的 UI 线程（“主”线程）可以执行该操作时再安排其在线程上运行。
         * 不过，如有必要，您也可以从 UI 线程调用 executePendingTransactions() 以立即执行 commit() 提交的事务。通常不必这样做，除非其他线程中的作业依赖该事务
         */
        fragmentTransaction.commit();
    }
}
