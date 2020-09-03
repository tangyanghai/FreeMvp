package com.example.testfreemvp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.lib_mvp.annotation.MVPPresenter;

/**
 * <p>@author : tangyanghai</p>
 * <p>@time : 2020/8/27</p>
 * <p>@for : </p>
 * <p></p>
 */
public class TestActivity extends BaseActivity implements ISecondView {

    @MVPPresenter
    SecondPresenter secondPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondPresenter.loadSecondView(TestActivity.this);
            }
        });

         findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TestActivity.this,Test2Activity.class));
            }
        });
    }

    @Override
    public void onSecondViewClicked() {
        Toast.makeText(this, "TestActivity: 第二个调用成功", Toast.LENGTH_SHORT).show();
    }
}
