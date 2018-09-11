package com.yangys.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.yangys.data.login.LoginDataRemoteSource;
import com.yangys.data.login.LoginDataRepository;
import com.yangys.data.login.loginDataLocalSource;
import com.yangys.fragment.LoginFragment;
import com.yangys.mvp.presenter.LoginPresenter;

import example.com.myandroid.R;

/**
 * Created by ZH-ADMIN on 2018/9/3.
 */

public class LoginMainActivity extends AppCompatActivity{

    private LoginFragment mLoginFragment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main_layout);

        mLoginFragment = new LoginFragment();
        new LoginPresenter(mLoginFragment,
                LoginDataRepository.getInstance(LoginDataRemoteSource.getInstance(),
                        new loginDataLocalSource()));

        if(!mLoginFragment.isAdded()){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container,mLoginFragment,LoginFragment.class.getSimpleName())
                    .commit();
        }
    }

}
