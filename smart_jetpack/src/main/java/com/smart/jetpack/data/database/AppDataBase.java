package com.smart.jetpack.data.database;

import android.content.Context;

import com.smart.jetpack.data.model.UserEntity;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * @author LinDingQiang
 * @time 2020/6/24 9:52
 * @email dingqiang.l@verifone.cn
 */
@Database(entities = {UserEntity.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase appDataBase;

    public static AppDataBase getInstance(Context context) {
        if (appDataBase == null) {
            appDataBase = Room.databaseBuilder(context, AppDataBase.class, "smart_jetpack.db")
                    .allowMainThreadQueries()
                    .build();
        }
        return appDataBase;
    }

    public abstract UserDao userDao();
}
