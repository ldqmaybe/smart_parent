package com.smart.jetpack.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smart.jetpack.R;
import com.smart.jetpack.work.MyWorker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

public class MineFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("TAG", "onCreateView: MineFragment");
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss", Locale.getDefault());
        final Data time = new Data.Builder()
                .putString("time", simpleDateFormat.format(new Date()))
                .build();

        Log.i("TAG", "onViewCreated: work start ...");
        OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(MyWorker.class)
                .setInputData(time)
                .build();
        WorkManager.getInstance(getActivity()).enqueue(request);

        WorkManager.getInstance(getActivity())
                .getWorkInfoByIdLiveData(request.getId())
                .observe(getViewLifecycleOwner(), new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(WorkInfo workInfo) {
                        Log.i("TAG", "onChanged: id = " + workInfo.getId().toString()
                                + "\n outputTime = " + workInfo.getOutputData().getString("outputTime")
                                + "\n mRunAttemptCount = " + workInfo.getRunAttemptCount()
                                + "\n mState = " + workInfo.getState().name()
                        );
                    }
                });


        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyyMMdd hh:mm:ss", Locale.getDefault());
        final Data time1 = new Data.Builder()
                .putString("time", simpleDateFormat1.format(new Date()))
                .build();

        PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(MyWorker.class, 3, TimeUnit.SECONDS)
                .setInputData(time1)
                .build();

        WorkManager.getInstance(getActivity()).enqueue(periodicWorkRequest);

        WorkManager.getInstance(getActivity())
                .getWorkInfoByIdLiveData(periodicWorkRequest.getId())
                .observe(getViewLifecycleOwner(), new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(WorkInfo workInfo) {
                        Log.i("TAG", "onChanged: id = " + workInfo.getId().toString()
                                + "\n outputTime = " + workInfo.getOutputData().getString("outputTime")
                                + "\n mRunAttemptCount = " + workInfo.getRunAttemptCount()
                                + "\n mState = " + workInfo.getState().name()
                        );
                    }
                });


    }
}