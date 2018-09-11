package com.yangys.data.login;

import android.support.annotation.NonNull;

import com.yangys.data.interfaces.LoginDataSource;
import com.yangys.model.LoginData;
import com.yangys.model.LoginDetailData;
import com.yangys.model.LoginType;

import io.reactivex.Observable;

/**
 * Created by yangys on 2018/9/3.
 */

public class LoginDataRepository implements LoginDataSource{

    @NonNull
    private LoginDataSource remoteLogin;
    @NonNull
    private LoginDataSource localLogin;

    private static LoginDataRepository instance;

    private LoginDataRepository (LoginDataSource remoteLogin,LoginDataSource localLogin){
        this.remoteLogin = remoteLogin;
        this.localLogin = localLogin;
    };

    public static LoginDataRepository getInstance(LoginDataSource remoteLogin,LoginDataSource localLogin){

        if(instance == null){
            instance = new LoginDataRepository(remoteLogin,localLogin);
        }
        return instance;
    };


    @Override
    public Observable<LoginData> getRemoteLoginData(@NonNull String name, @NonNull String pwd, @NonNull LoginType loginType) {
        return remoteLogin.getRemoteLoginData(name,pwd,loginType);
    }

    @Override
    public Observable<LoginDetailData> getLocalLoginData(@NonNull int UserId) {
        return localLogin.getLocalLoginData(UserId);
    }


}
