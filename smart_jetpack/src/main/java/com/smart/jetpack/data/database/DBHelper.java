package com.smart.jetpack.data.database;

import android.content.Context;

import com.smart.jetpack.data.model.UserEntity;

import java.util.List;

import androidx.lifecycle.LiveData;

/**
 * @author LinDingQiang
 * @time 2020/6/24 10:14
 * @email dingqiang.l@verifone.cn
 */
public class DBHelper {
    private static UserDao userDao;

    private DBHelper() {
    }

    public static synchronized void init(Context context) {
        userDao = AppDataBase.getInstance(context).userDao();
    }

    public static void insertUser(UserEntity userEntity) {
        userDao.insert(userEntity);
    }

    public static UserEntity queryUserByAccount(String account) {
        return userDao.queryUserByAccount(account);
    }

    public static LiveData<List<UserEntity>> queryAll() {
        return userDao.queryAll();
    }
}
