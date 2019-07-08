package com.example.tarena.bmobdemo.app;

import android.app.Application;

import cn.bmob.v3.Bmob;

/**
 * Created by tarena on 2017/6/29.
 */

public class MyApp extends Application{

    public static MyApp CONTEXT;

    @Override
    public void onCreate() {
        super.onCreate();
        CONTEXT = this;
        //772f0d5bcb45dfd29579c82dd05510aa
        Bmob.initialize(this, "73e737a9d1aa149e036406bf923b50bf");
    }
}
