package com.smart.jetpack.utils;

import android.util.Patterns;

/**
 * @author LinDingQiang
 * @time 2020/6/24 16:57
 * @email dingqiang.l@verifone.cn
 */
public class StringUtil {
    private StringUtil() {
    }

    public static boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    public static boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 3;
    }
}
