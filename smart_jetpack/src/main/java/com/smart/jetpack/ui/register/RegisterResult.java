package com.smart.jetpack.ui.register;


import androidx.annotation.Nullable;

public class RegisterResult<T> {
    @Nullable
    private T data;
    @Nullable
    private boolean isSuccese;

    public RegisterResult(boolean isSuccese, T data) {
        this.isSuccese = isSuccese;
        this.data = data;
    }

    public RegisterResult(boolean isSuccese) {
        this.isSuccese = isSuccese;
    }

    @Nullable
    public T getData() {
        return data;
    }

    public void setData(@Nullable T data) {
        this.data = data;
    }

    public boolean isSuccese() {
        return isSuccese;
    }

    public void setSuccese(boolean succese) {
        isSuccese = succese;
    }
}
