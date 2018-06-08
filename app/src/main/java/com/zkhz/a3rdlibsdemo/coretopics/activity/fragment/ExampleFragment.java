package com.zkhz.a3rdlibsdemo.coretopics.activity.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zkhz.a3rdlibsdemo.R;

/**
 * Fragment必须始终嵌入在 Activity 中，其生命周期直接受宿主 Activity 生命周期的影响.当 Activity 暂停时，其中的所有片段也会暂停；当 Activity 被销毁时，所有片段也会被销毁
 * 不过，当 Activity 正在运行（处于已恢复生命周期状态）时，您可以独立操纵每个片段，如添加或移除它们。
 * 当您执行此类片段事务时，您也可以将其添加到由 Activity 管理的返回栈 — Activity 中的每个返回栈条目都是一条已发生片段事务的记录。
 * 返回栈让用户可以通过按返回按钮撤消片段事务（后退）。
 *
 * 添加方式:①通过在 Activity 的布局文件中声明片段，将其作为 <fragment> 元素插入您的 Activity 布局中
 *        ②通过将其添加到某个现有 ViewGroup，利用应用代码进行插入。
 *
 *        不过，片段并非必须成为 Activity 布局的一部分；您还可以将没有自己 UI 的片段用作 Activity 的不可见工作线程。
 *
 * 片段的给定实例会 直接绑定 到包含它的 Activity。
 */
public class ExampleFragment extends Fragment{

    private TextView textView;

   //系统会在片段首次绘制其用户界面时调用此方法。 要想为您的片段绘制UI，您从此方法中返回的 View 必须是片段布局的根视图。如果片段未提供 UI，您可以返回 null。
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /**
         * inflate() 方法带有三个参数：
         * 您想要扩展的布局的资源 ID
         * 将作为扩展布局父项的 ViewGroup。传递 container 对系统向扩展布局的根视图（由其所属的父视图指定）应用布局参数具有重要意义；
         * 指示是否应该在扩展期间将扩展布局附加至 ViewGroup（第二个参数）的布尔值。（在本例中，其值为 false，因为系统已经将扩展布局插入 container — 传递 true 值会在最终布局中创建一个多余的视图组。）
         */
        return inflater.inflate(R.layout.fragment_example,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textView = view.findViewById(R.id.tv_fg);
    }
}
