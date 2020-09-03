package com.example.testfreemvp;

import android.util.Log;

import com.example.lib_mvp.BaseMVPPresenter;
import com.example.lib_mvp.annotation.MVPModel;

/**
 * <p>@author : tangyanghai</p>
 * <p>@time : 2020/8/27</p>
 * <p>@for : </p>
 * <p></p>
 */
public class MainPresenter extends BaseMVPPresenter<MainView> {
    @MVPModel
    MainModel mainModel;

    void loadView() {
        mainModel.loadView();
        getView().showTest();
    }

    @Override
    public void detachView() {
        super.detachView();
        Log.e("===", "清除presenter完成");
    }
}
