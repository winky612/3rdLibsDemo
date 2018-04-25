package com.zkhz.a3rdlibsdemo.mvpdemo.gitmvpdemo.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.zkhz.a3rdlibsdemo.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/25 0025.
 */

public class DemoActivity extends AppCompatActivity implements DemoView,AdapterView.OnItemClickListener {
    @BindView(R.id.list)
    ListView list;
    @BindView(R.id.progress)
    ProgressBar progress;

    private DemoPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter=new DemoPresenterImpl(this,new FindItemsInteractorImpl());


    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
        list.setVisibility(View.INVISIBLE);

    }

    @Override
    public void hideProgress() {

        progress.setVisibility(View.INVISIBLE);
        list.setVisibility(View.VISIBLE);

    }

    @Override
    public void setItems(List<String> items) {

        list.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,items));

    }

    @Override
    public void showMessage(String message) {

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        presenter.onItemClick(position);

    }
}
