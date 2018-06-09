package com.zkhz.a3rdlibsdemo.coretopics.activity.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zkhz.a3rdlibsdemo.R;


/**
 * 与 Activity 通信----创建对 Activity 的事件回调
 * <p>
 * 通过片段与 Activity 共享事件。执行此操作的一个好方法是，在片段内定义一个回调接口，并要求宿主 Activity 实现它。
 * 当 Activity 通过该接口收到回调时，可以根据需要与布局中的其他片段共享这些信息。
 * 例如，如果一个新闻应用的 Activity 有两个片段 — 一个用于显示文章列表（片段 A），另一个用于显示文章（片段 B）— 那么片段 A 必须在列表项被选定后告知 Activity，以便它告知片段 B 显示该文章
 */

public class LeftListFragment extends ListFragment {

    private ListView listView;
    private onTitleSelectedListener listener;
    private String[] titles = {"title 1", "title 2", "title 3", "title 4"};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, titles));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_leftlist, container, false);
        // 设置ListFragment默认的ListView
    }

//    @Override
//    public void onViewCreated(View view, Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        // 设置ListFragment默认的ListView
//        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, titles));
//    }

    // Container Activity must implement this interface
    public interface onTitleSelectedListener {
//        void onTitleSelected(Uri articleUri);
        void onTitleSelected(String str);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // 如果 Activity 未实现接口，则片段会引发 ClassCastException。
        // 实现时，listener 成员会保留对 Activity 的 onTitleSelectedListener 实现的引用，以便片段 A 可以通过调用 OnArticleSelectedListener 接口定义的方法与 Activity 共享事件。
        // (联想----listener 通过 implements 牵住了实现它的那个activity,而listener是在fragment中定义的,也就等同于fragment通过listener 牵住了activity ---'风筝引线')
        try {
            listener = (onTitleSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnArticleSelectedListener");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);


        listener.onTitleSelected(position + "");


    }
}
