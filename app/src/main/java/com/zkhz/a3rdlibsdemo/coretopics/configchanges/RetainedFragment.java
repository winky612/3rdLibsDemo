package com.zkhz.a3rdlibsdemo.coretopics.configchanges;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;


/**
 * 注意：尽管您可以存储任何对象，但是切勿传递与 Activity 绑定的对象，
 * 例如，Drawable、Adapter、View 或其他任何与 Context 关联的对象。
 * 否则，它将泄漏原始 Activity 实例的所有视图和资源（泄漏资源意味着应用将继续持有这些资源，但是无法对其进行垃圾回收，因此可能会丢失大量内存）
 */

public class RetainedFragment extends Fragment {

    // data object we want to retain
    private MyDataObject data;

    // this method is only called once for this fragment
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // retain this fragment
        setRetainInstance(true);
    }

    public void setData(MyDataObject data) {
        this.data = data;
    }

    public MyDataObject getData() {
        return data;
    }
}
