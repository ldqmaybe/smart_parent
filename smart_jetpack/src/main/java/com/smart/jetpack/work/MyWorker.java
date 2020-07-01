package com.smart.jetpack.work;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

/**
 * @author LinDingQiang
 * @time 2020/6/29 11:05
 * @email dingqiang.l@verifone.cn
 */
public class MyWorker extends Worker {

    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        String time = getInputData().getString("time");
        Log.i("TAG", "doWork: time = " +time);
        Data outputTime = new Data.Builder().putString("outputTime", time).build();
        return Worker.Result.success(outputTime);
    }
}
