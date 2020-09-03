package com.example.testfreemvp;

import com.example.lib_mvp.BaseMVPPresenter;

/**
 * <p>@author : tangyanghai</p>
 * <p>@time : 2020/9/2</p>
 * <p>@for : </p>
 * <p></p>
 */
public class Test2Presenter extends BaseMVPPresenter<ITest2View> {
    public void onResume(){
        getView().onResume();
    }
}
