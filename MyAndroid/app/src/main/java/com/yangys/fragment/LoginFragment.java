package com.yangys.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yangys.activity.MainActivity;
import com.yangys.model.LoginDetailData;
import com.yangys.model.LoginType;
import com.yangys.mvp.LoginContract;
import com.yangys.utils.TextUtil;

import example.com.myandroid.R;

/**
 * Created by yangys on 2018/9/3.
 */

public class LoginFragment extends Fragment implements LoginContract.View{

    private TextInputEditText editUserName;
    private TextInputEditText editPassword;
    private AppCompatButton btnLogin;
    private TextView linkSignUp;
    private LoginContract.Presenter presenter;

    private static final String TAG = "LoginFragment";

    public static LoginFragment getInstance(){

        return new LoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.login_fragment_layout,container,false);
        initView(view);


        btnLogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                String name = editUserName.getText().toString();
                String pwd = editPassword.getText().toString();
                if(checkValid(name,pwd)){
                    presenter.login(name,pwd, LoginType.TYPE_LOGIN);
                }

            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.unSubscribe();
    }

    @Override
    public void initView(View view) {
        editUserName = view.findViewById(R.id.edit_username);
        editPassword = view.findViewById(R.id.edit_password);
        btnLogin = view.findViewById(R.id.btn_login);
        linkSignUp = view.findViewById(R.id.text_link_signup);
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showLoginError(String errorMsg) {

    }

    @Override
    public void saveUserToPreference(LoginDetailData loginDetailData) {
        Log.i(TAG,"saveUserToPreference : "+loginDetailData.getUsername());
        //登陆成功执行此逻辑
        Intent intent = new Intent(getContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getActivity().startActivity(intent);

    }

    @Override
    public boolean isActive() {
        return isAdded() && isResumed();
    }


    private boolean checkValid(String username, String password){
        boolean isValid = false;
        if (TextUtil.isInvalid(username)|| TextUtil.isInvalid(password)){
            Snackbar.make(linkSignUp,getString(R.string.input_error),Snackbar.LENGTH_SHORT).show();
        }else {
            isValid = true;
        }
        return isValid;
    }
}
