package com.smart.jetpack;

import android.app.Application;

import com.smart.jetpack.data.database.DBHelper;

/**
 * @author LinDingQiang
 * @time 2020/6/24 10:31
 * @email dingqiang.l@verifone.cn
 */
public class SmartApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DBHelper.init(this);
    }
}
