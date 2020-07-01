package com.smart.jetpack.data.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * @author LinDingQiang
 * @time 2020/6/23 17:03
 * @email dingqiang.l@verifone.cn
 */
@Entity()
public class UserEntity {
    @PrimaryKey(autoGenerate = true)
    private long uid;

    private String account;
    private String password;

    public UserEntity() {
    }

    @Ignore
    public UserEntity(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
