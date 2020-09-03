package com.example.testfreemvp;

import com.example.lib_mvp.BaseMVPPresenter;

/**
 * <p>@author : tangyanghai</p>
 * <p>@time : 2020/9/2</p>
 * <p>@for : </p>
 * <p></p>
 */
public class TestPresenter3 extends BaseMVPPresenter<ITestView3> {
    public void onResume() {
        getView().onResume1();
    }
}
