package com.smart.jetpack.ui.login;

import com.smart.jetpack.data.LoginDataSource;
import com.smart.jetpack.data.LoginRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * @author LinDingQiang
 * @time 2020/6/23 17:55
 * @email dingqiang.l@verifone.cn
 */
public class LoginViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(LoginRepository.getInstance(new LoginDataSource()));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
