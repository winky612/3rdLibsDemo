package com.zkhz.a3rdlibsdemo.coretopics.configchanges;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zkhz.a3rdlibsdemo.R;


/**
 * 您可能会遇到这种情况：重启应用并恢复大量数据不仅成本高昂，而且给用户留下糟糕的使用体验。
 * 在这种情况下，您有两个其他选择：
 * ①在配置变更期间保留对象:允许 Activity 在配置变更时重启，但是要将有状态对象传递给 Activity 的新实例。
 * ②自行处理配置变更:阻止系统在某些配置变更期间重启 Activity，但要在配置确实发生变化时接收回调，这样，您就能够根据需要手动更新 Activity。
 *   注：自行处理配置变更可能导致备用资源的使用更为困难，因为系统不会为您自动应用这些资源。
 *   只能在您必须避免 Activity 因配置变更而重启这一万般无奈的情况下，才考虑采用自行处理配置变更这种方法，而且对于大多数应用并不建议使用此方法。
 */


/**
 * 在配置变更期间保留对象
 *
 * 如果 Activity 因配置变更而重启，则可通过保留 Fragment 来减轻重新初始化 Activity 的负担。此片段可能包含对您要保留的有状态对象的引用。
 * 当 Android 系统因配置变更而关闭 Activity 时，不会销毁您已标记为要保留的 Activity 的片段。 您可以将此类片段添加到 Activity 以保留有状态的对象。
 * <p>
 * 要在运行时配置变更期间将有状态的对象保留在片段中，请执行以下操作：
 * ①扩展 Fragment 类并声明对有状态对象的引用。
 * ②在创建片段后调用 setRetainInstance(boolean)。
 * ③将片段添加到 Activity。
 * ④重启 Activity 后，使用 FragmentManager 检索片段。
 */


/**
 * 在此示例中，onCreate() 添加了一个片段或恢复了对它的引用。
 * 此外，onCreate() 还将有状态的对象存储在片段实例内部。
 * onDestroy() 对所保留的片段实例内的有状态对象进行更新。
 */
public class ConfigChangeActivity extends AppCompatActivity {

    private RetainedFragment dataFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find the retained fragment on activity restarts
        FragmentManager fragmentManager = getFragmentManager();
        dataFragment = (RetainedFragment) fragmentManager.findFragmentByTag("data");

        // create the fragment and data the first time
        if (dataFragment == null) {

            dataFragment = new RetainedFragment();
            //在与此FragmentManager关联的碎片上启动一系列编辑操作。
            fragmentManager.beginTransaction().add(dataFragment, "data").commit();
            // load the data from the web
            dataFragment.setData(loadMyData());

        }

        // the data is available in dataFragment.getData()


    }

    private MyDataObject loadMyData() {

        return new MyDataObject();//具体以实际应用为准
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // store the data in the fragment
        dataFragment.setData(collectMyLoadedData());

    }

    private MyDataObject collectMyLoadedData() {

        return new MyDataObject();//具体以实际应用为准
    }
}
