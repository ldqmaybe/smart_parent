package com.smart.jetpack.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.smart.jetpack.R;
import com.smart.jetpack.data.model.UserEntity;
import com.smart.jetpack.databinding.FragmentHomeBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

public class HomeFragment extends Fragment {
    private HomeViewModel viewModel;
    private FragmentHomeBinding mBinding;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        recyclerView = view.findViewById(R.id.rv);
        viewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        mBinding.setViewModel(viewModel);
        viewModel.setTitle("Home");

        viewModel.getTitle().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mBinding.tvTitle.setText(s);
            }
        });

        mBinding.rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        final BaseQuickAdapter<UserEntity, BaseViewHolder> adapter = new BaseQuickAdapter<UserEntity, BaseViewHolder>(R.layout.main_item) {
            @Override
            protected void convert(@NotNull BaseViewHolder holder, UserEntity userEntity) {
                holder.setText(R.id.tv_account, userEntity.getAccount());
            }
        };
        mBinding.rv.setAdapter(adapter);
        mBinding.setLisenter(this);

        viewModel.getUsers().observe(getViewLifecycleOwner(), new Observer<List<UserEntity>>() {
            @Override
            public void onChanged(List<UserEntity> userEntities) {
                adapter.setNewInstance(userEntities);
            }
        });

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                UserEntity userEntity = (UserEntity) adapter.getItem(position);
                viewModel.setTitle(userEntity.getAccount());
            }
        });

    }


    public void setOnClick(View view){
        UserEntity userEntity = new UserEntity();
        Random random = new Random();
      int acc=  random.nextInt(100000);
        userEntity.setAccount(acc+"");
        userEntity.setPassword("0000");
        Log.d("TAG",userEntity.toString());
        viewModel.addUser(userEntity);
    }

}