package com.smart.jetpack.data;

import com.smart.jetpack.data.database.DBHelper;
import com.smart.jetpack.data.model.UserEntity;
import com.smart.jetpack.exception.CustomException;

import java.io.IOException;

/**
 * @author LinDingQiang
 * @time 2020/6/24 15:17
 * @email dingqiang.l@verifone.cn
 */
public class RegisterRepository {
    private static RegisterRepository repository;
    private RegisterDataSource dataSource;

    public static RegisterRepository getInstance() {
        if (repository == null) {
            repository = new RegisterRepository();
        }
        return repository;
    }

    public Result<UserEntity> register(String account, String password) {
        try {
            UserEntity userEntity = DBHelper.queryUserByAccount(account);
            if (userEntity != null) {
                return new Result.Error(new CustomException("用户已存在"));
            }
            userEntity = new UserEntity(account, password);
            DBHelper.insertUser(userEntity);
            return new Result.Success<>(userEntity);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

}
