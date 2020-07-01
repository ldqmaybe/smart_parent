package com.smart.jetpack.ui.login;

import com.smart.jetpack.R;
import com.smart.jetpack.data.LoginRepository;
import com.smart.jetpack.data.Result;
import com.smart.jetpack.data.model.UserEntity;
import com.smart.jetpack.utils.StringUtil;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private LoginRepository loginRepository;

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public LiveData<LoginResult> login(String username, String password) {
        Result<UserEntity> result = loginRepository.login(username, password);
        if (result instanceof Result.Success) {
            UserEntity data = ((Result.Success<UserEntity>) result).getData();
            loginResult.setValue(new LoginResult(new LoggedInUserView(data.getAccount())));
        } else {
            loginResult.setValue(new LoginResult(R.string.login_failed));
        }
        return loginResult;
    }

    public void loginDataChanged(String username, String password) {
        if (!StringUtil.isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!StringUtil.isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

}
