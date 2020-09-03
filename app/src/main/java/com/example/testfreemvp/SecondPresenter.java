package com.example.testfreemvp;

import android.app.Activity;
import android.util.Log;

import com.example.lib_mvp.BaseMVPPresenter;
import com.example.lib_mvp.annotation.MVPModel;

/**
 * <p>@author : tangyanghai</p>
 * <p>@time : 2020/8/31</p>
 * <p>@for : </p>
 * <p></p>
 */
public class SecondPresenter extends BaseMVPPresenter<ISecondView> {

    @MVPModel
    SecondModel model;

    void loadSecondView(Activity act) {
        model.loadSecondView();
        ISecondView view = getView();
        view.onSecondViewClicked();
    }

    @Override
    public void detachView() {
        super.detachView();
        Log.e("===", "清除第二个presenter完成");
    }
}
