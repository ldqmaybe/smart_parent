package com.smart.jetpack.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.smart.jetpack.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

public class WelcomeFragment extends Fragment {
    private Button btnLogin, btnRegister;
    private NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        btnLogin = view.findViewById(R.id.btn_login);
//        btnRegister = view.findViewById(R.id.btn_register);
        navController = Navigation.findNavController(getView());
        final NavOptions navOptions = new NavOptions.Builder()
                .setEnterAnim(R.anim.common_slide_in_right)
                .setExitAnim(R.anim.common_slide_out_left)
                .setPopEnterAnim(R.anim.common_slide_in_left)
                .setPopExitAnim(R.anim.common_slide_out_right)
                .build();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.login, null, navOptions);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.register, null, navOptions);
            }
        });
    }
}