package com.smart.jetpack.data;

import com.smart.jetpack.data.database.DBHelper;
import com.smart.jetpack.data.model.UserEntity;

import java.util.List;

import androidx.lifecycle.LiveData;

/**
 * @author LinDingQiang
 * @time 2020/6/28 14:58
 * @email dingqiang.l@verifone.cn
 */
public class HomeRepository {

    private HomeRepository(){}
    private static  final class SingleHolder{
        private static  final  HomeRepository REPOSITORY = new HomeRepository();
    }
    public static HomeRepository getInstance(){
        return SingleHolder.REPOSITORY;
    }

    public LiveData<List<UserEntity>> getAllUsers(){
        return DBHelper.queryAll();
    }
}
