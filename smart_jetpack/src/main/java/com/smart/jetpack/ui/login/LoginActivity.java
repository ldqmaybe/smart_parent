package com.smart.jetpack.ui.login;

import android.os.Bundle;

import com.smart.jetpack.R;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        /*
        在这里可能会有人会疑惑，用Bundle不是更简单些吗，Safe Args 传递方式不仅编码复杂，还要安装它的插件，不会太麻烦吗？既然是Google推荐，那当然是有道理的
        这里不再啰嗦，直接贴上官方说明
         */
    }
}
