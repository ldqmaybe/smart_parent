package com.smart.jetpack.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.smart.jetpack.R;
import com.smart.jetpack.ui.login.LoginFragmentArgs;
import com.smart.jetpack.ui.main.MainActivity;
import com.smart.jetpack.widget.CustomTextWatcher;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class RegisterFragment extends Fragment {

    private RegisterViewModel viewModel;
    private EditText usernameEditText, passwordEditText;
    private ProgressBar loadingProgressBar;
    private Button btnRegister;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);
        usernameEditText = view.findViewById(R.id.username);
        passwordEditText = view.findViewById(R.id.password);
        btnRegister = view.findViewById(R.id.btn_register);
        loadingProgressBar = view.findViewById(R.id.loading);
        Bundle bundle = getArguments();
        if (bundle != null) {
//            String loginTag = bundle.getString("loginTag");
            String loginTag = LoginFragmentArgs.fromBundle(bundle).getLoginTag();
            usernameEditText.setText(loginTag);
        }

        usernameEditText.addTextChangedListener(mCustomTextWatcher);
        passwordEditText.addTextChangedListener(mCustomTextWatcher);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    register();
                }
                return false;
            }
        });

        view.findViewById(R.id.btn_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                register();
            }
        });
    }

    private void register() {
        viewModel.register(usernameEditText.getText().toString(), passwordEditText.getText().toString())
                .observe(getViewLifecycleOwner(), new Observer<RegisterResult>() {
                    @Override
                    public void onChanged(RegisterResult registerResult) {
                        loadingProgressBar.setVisibility(View.GONE);
                        if (!registerResult.isSuccese()) {
                            String data = (String) registerResult.getData();
                            Toast.makeText(getActivity(), "错误信息：" + data, Toast.LENGTH_SHORT).show();
                            return;
                        }
                        startActivity(new Intent(getActivity(), MainActivity.class));
                    }
                });
    }

    private CustomTextWatcher mCustomTextWatcher = new CustomTextWatcher() {
        @Override
        public void afterTextChanged(Editable editable) {
            viewModel.registerDataChanged(usernameEditText.getText().toString(), passwordEditText.getText().toString())
                    .observe(getViewLifecycleOwner(), new Observer<RegisterFormState>() {
                        @Override
                        public void onChanged(RegisterFormState formState) {
                            if (formState == null) {
                                return;
                            }
                            btnRegister.setEnabled(formState.isDataValid());
                            if (formState.getUsernameError() != null) {
                                usernameEditText.setError(getString(formState.getUsernameError()));
                            }
                            if (formState.getPasswordError() != null) {
                                passwordEditText.setError(getString(formState.getPasswordError()));
                            }
                        }
                    });
        }
    };
}