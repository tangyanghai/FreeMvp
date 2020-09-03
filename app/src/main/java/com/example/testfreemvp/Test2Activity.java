package com.example.testfreemvp;

import android.util.Log;
import android.widget.Toast;

import com.example.lib_mvp.annotation.MVPPresenter;

/**
 * <p>@author : tangyanghai</p>
 * <p>@time : 2020/9/2</p>
 * <p>@for : </p>
 * <p></p>
 */
public class Test2Activity extends TestActivity implements ITest2View, ITestView3 {

    @MVPPresenter
    Test2Presenter secondPresenter2;

    @MVPPresenter
    TestPresenter4 presenter4;

    @Override
    protected void onStart() {
        super.onStart();
        secondPresenter2.onResume();
        presenter4.onResume();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onResume1() {
        Log.e("===", "TestPresenter4正常");
    }

    @Override
    public void onSecondViewClicked() {
        Toast.makeText(this, "Test2Activity: 第二个调用成功", Toast.LENGTH_SHORT).show();
    }
}
