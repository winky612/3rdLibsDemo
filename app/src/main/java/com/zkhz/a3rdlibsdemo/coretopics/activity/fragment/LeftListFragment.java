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
 * 通过fragment与 Activity 共享事件。执行此操作的一个好方法是，在fragment内定义一个回调接口，并要求宿主 Activity 实现它。
 * 当 Activity 通过该接口收到回调时，可以根据需要与布局中的其他片段共享这些信息。
 * 例如，如果一个新闻应用的 Activity 有两个fragment — 一个用于显示文章列表（fragment A），另一个用于显示文章（fragment B）— 那么fragment A 必须在列表项被选定后告知 Activity，以便它告知fragment B 显示该文章
 */


/**
 * Demo分析(配合layout中的注释看):
 * 用户点击列表项时可能会出现两种行为：系统可能会创建并显示一个新片段，从而在同一Activity 中显示详细信息（将片段添加到 FrameLayout），
 * 也可能会启动一个新 Activity（在该Activity中可显示fragment），具体取决于这两个布局中哪一个处于活动状态(也就设备是平板还是手机)。
 */

public class LeftListFragment extends ListFragment {

    private ListView listView;
    private onTitleSelectedListener listener;
    private String[] titles = {"title 1", "title 2", "title 3", "title 4"};
    boolean mDualPane;
    int mCurCheckPosition;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_leftlist, container, false);
        // 设置ListFragment默认的ListView
    }

    //Activity 的每个连续状态如何 决定fragment可以收到的回调方法。 例如，当 Activity 收到其 onCreate() 回调时，Activity 中的fragment只会收到 onActivityCreated() 回调。
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Populate list with our static array of titles.
        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, titles));


        // Check to see if we have a frame in which to embed the details fragment directly in the containing UI.
        View detailFrame = getActivity().findViewById(R.id.fg_right);
        mDualPane = detailFrame != null && detailFrame.getVisibility() == View.VISIBLE;

        if (savedInstanceState != null) {
            // Restore last state for checked position.
            mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
        }

        if (mDualPane) {
            // In dual-pane mode, the list view highlights the selected item.
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            // Make sure our UI is in the correct state.
            showDetails(mCurCheckPosition);

        }

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("curChoice", mCurCheckPosition);
    }

    // Container Activity must implement this interface
    public interface onTitleSelectedListener {
        void onTitleSelected(String str);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // 如果 Activity 未实现接口，则片段会引发 ClassCastException。
        // 实现时，listener 成员会保留对 Activity 的 onTitleSelectedListener 实现的引用，以便fragment A 可以通过调用 OnArticleSelectedListener 接口定义的方法与 Activity 共享事件。
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


//        listener.onTitleSelected(position + "");

        showDetails(position);

    }

    /**
     * Helper function to show the details of a selected item, either by
     * displaying a fragment in-place in the current UI, or starting a
     * whole new activity in which it is displayed.
     */
    private void showDetails(int index) {

        mCurCheckPosition = index;
        if (mDualPane) {
            // We can display everything in-place with fragments, so update the list to highlight the selected item and show the data.
            getListView().setItemChecked(index, true);
            // Check what fragment is currently shown, replace if needed.
            RightListFragment rightListFragment = (RightListFragment) getFragmentManager().findFragmentById(R.id.fg_right);

        }

    }
}
