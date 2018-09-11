package com.yangys.mvp;

import android.support.annotation.NonNull;

import com.yangys.model.LoginDetailData;
import com.yangys.model.LoginType;
import com.yangys.mvp.presenter.BasePresenter;
import com.yangys.mvp.uiinterface.BaseView;

/**
 * Created by yangys on 2018/9/3.
 */

public interface LoginContract {

    interface Presenter extends BasePresenter{
        void login(String name ,String password ,@NonNull LoginType loginType);

    }


    interface View extends BaseView<Presenter>{
        void showLoginError(String errorMsg);

        void saveUserToPreference(LoginDetailData loginDetailData);

        boolean isActive();
    }


}
