package com.example.testfreemvp;

import android.util.Log;

import com.example.lib_mvp.IBaseMVPModel;

/**
 * <p>@author : tangyanghai</p>
 * <p>@time : 2020/9/2</p>
 * <p>@for : </p>
 * <p></p>
 */
public class MainModel implements IBaseMVPModel {
    public void loadView(){
        Log.e("===",getClass().getName()+Thread.currentThread() .getStackTrace()[2].getMethodName());
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        Log.e("===",getClass().getName()+Thread.currentThread() .getStackTrace()[2].getMethodName());
    }
}
