package com.smart.jetpack.data;

import com.smart.jetpack.data.model.UserEntity;
import com.smart.jetpack.data.database.DBHelper;
import com.smart.jetpack.exception.CustomException;

import java.io.IOException;

public class LoginDataSource {

    public Result<UserEntity> login(String account, String password) {
        try {
            UserEntity userEntity = DBHelper.queryUserByAccount(account);
            if (userEntity == null) {
                return new Result.Error(new CustomException("用户不存在"));
            }
            if (!account.equals(userEntity.getAccount()) || !password.equals(userEntity.getPassword())) {
                return new Result.Error(new CustomException("账号或密码不匹配"));
            }
            return new Result.Success<>(userEntity);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
    }
}
