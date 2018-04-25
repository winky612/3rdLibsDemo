package com.zkhz.a3rdlibsdemo.mvpdemo.gitmvpdemo.main;

import java.util.List;

/**
 * Created by Administrator on 2018/4/25 0025.
 */

public class DemoPresenterImpl implements DemoPresenter {

    private DemoView view;
    private FindItemsInteractor findItemsInteractor;

    public DemoPresenterImpl(DemoView view, FindItemsInteractor findItemsInteractor) {
        this.view = view;
        this.findItemsInteractor = findItemsInteractor;
    }

    @Override
    public void onResume() {

        if (view!=null){
            view.showProgress();
        }

        findItemsInteractor.findItems(new FindItemsInteractor.OnFinishedListener() {
            @Override
            public void onFinished(List<String> items) {
                if (view!=null){
                    view.setItems(items);
                    view.hideProgress();
                }
            }
        });


    }

    @Override
    public void onDestroy() {

        view=null;

    }

    @Override
    public void onItemClick(int position) {

        if (view!=null){
            view.showMessage(String.format("Position %d Click",position+1));
        }



    }
}
