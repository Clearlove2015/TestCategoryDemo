package com.helloword.zhangjianlong.testcategorydemo.ui;

import android.app.Application;
import android.content.Context;

/**
 * @author: zc
 * @Time: 2018/3/1 15:23
 * @Desc:
 */
public class MyApplication extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
