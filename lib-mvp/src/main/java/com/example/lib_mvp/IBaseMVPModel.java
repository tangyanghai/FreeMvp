package com.example.lib_mvp;

/**
 * <p>@author : tangyanghai</p>
 * <p>@time : 2020/9/2</p>
 * <p>@for : </p>
 * <p></p>
 */
public interface IBaseMVPModel {
    void onResume();

    void onPause();

    void onStop();

    void onDestroy();
}
