package com.smart.jetpack.data.database;

import com.smart.jetpack.data.model.UserEntity;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

/**
 * @author LinDingQiang
 * @time 2020/6/24 10:00
 * @email dingqiang.l@verifone.cn
 */
@Dao
interface UserDao {
    @Insert
    void insert(UserEntity userEntity);

    @Delete
    void delete(UserEntity userEntity);

    @Query("select * from UserEntity where account = :account")
    UserEntity queryUserByAccount(String account);

    @Query("select * from UserEntity")
    LiveData<List<UserEntity>> queryAll();
}
