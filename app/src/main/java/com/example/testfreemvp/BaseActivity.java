package com.example.testfreemvp;

import android.os.Bundle;

import com.example.lib_mvp.api.MVP;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * <p>@author : tangyanghai</p>
 * <p>@time : 2020/8/27</p>
 * <p>@for : </p>
 * <p></p>
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MVP.createPresenter(this);
    }

}
