package com.smart.jetpack.ui.main;

import com.smart.jetpack.data.HomeRepository;
import com.smart.jetpack.data.database.DBHelper;
import com.smart.jetpack.data.model.UserEntity;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author LinDingQiang
 * @time 2020/6/28 14:57
 * @email dingqiang.l@verifone.cn
 */
public class HomeViewModel extends ViewModel {
    private MutableLiveData<String> liveDataTitle = new MutableLiveData<>();

    private HomeRepository repository;

    public HomeViewModel() {
        repository = HomeRepository.getInstance();
    }

    public LiveData<String> getTitle() {
        return liveDataTitle;
    }
    public void setTitle(String titleName) {
        liveDataTitle.setValue(titleName);
    }

    public LiveData<List<UserEntity>> getUsers() {
        return repository.getAllUsers();
    }

    public void addUser(UserEntity userEntity){
        DBHelper.insertUser(userEntity);
    }
}
