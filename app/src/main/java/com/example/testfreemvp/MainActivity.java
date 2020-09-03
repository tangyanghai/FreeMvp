package com.example.testfreemvp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.lib_mvp.annotation.MVPPresenter;

public class MainActivity extends BaseActivity implements MainView {

    @MVPPresenter
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.loadView();
                startActivity(new Intent(MainActivity.this, TestActivity.class));
            }
        });
    }

    @Override
    public void showTest() {
        Log.e("===", "mainPresenter调用成功");
    }
}
