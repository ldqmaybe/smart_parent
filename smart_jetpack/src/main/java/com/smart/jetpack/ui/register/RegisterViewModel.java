package com.smart.jetpack.ui.register;

import com.smart.jetpack.R;
import com.smart.jetpack.data.RegisterRepository;
import com.smart.jetpack.data.Result;
import com.smart.jetpack.data.model.UserEntity;
import com.smart.jetpack.utils.StringUtil;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author LinDingQiang
 * @time 2020/6/24 15:12
 * @email dingqiang.l@verifone.cn
 */
public class RegisterViewModel extends ViewModel {
    private RegisterRepository repository;
    private MutableLiveData<RegisterResult> registerResult = new MutableLiveData<RegisterResult>();
    private MutableLiveData<RegisterFormState> registerFormState = new MutableLiveData<>();
    public RegisterViewModel() {
        repository = RegisterRepository.getInstance();
    }

    public LiveData<RegisterResult> register(String account, String password) {
        Result<UserEntity> userEntityResult = repository.register(account, password);
        if (userEntityResult instanceof Result.Success) {
            UserEntity userEntity = ((Result.Success<UserEntity>) userEntityResult).getData();
            registerResult.setValue(new RegisterResult<>(true, userEntity));
        } else if (userEntityResult instanceof Result.Error) {
            Exception error = ((Result.Error) userEntityResult).getError();
            registerResult.setValue(new RegisterResult<>(false, error.getMessage()));
        }
        return registerResult;
    }

    public LiveData<RegisterFormState> registerDataChanged(String account, String password) {
        if (!StringUtil.isUserNameValid(account)) {
            registerFormState.setValue(new RegisterFormState(R.string.invalid_password, null));
        } else if (!StringUtil.isPasswordValid(password)) {
            registerFormState.setValue(new RegisterFormState(null, R.string.invalid_password));
        } else {
            registerFormState.setValue(new RegisterFormState(true));
        }
        return registerFormState;
    }
}
