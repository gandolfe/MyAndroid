package com.yangys.data.login;

import android.support.annotation.NonNull;
import android.util.Log;

import com.yangys.data.interfaces.LoginDataSource;
import com.yangys.data.retrofit.RetrofitClient;
import com.yangys.data.retrofit.RetrofitService;
import com.yangys.model.LoginData;
import com.yangys.model.LoginDetailData;
import com.yangys.model.LoginType;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yangys on 2018/9/3.
 */

public class LoginDataRemoteSource implements LoginDataSource{

    private static LoginDataRemoteSource instance;

    private static final String TAG = "LoginDataRemoteSource";

    private LoginDataRemoteSource(){

    }

    public static LoginDataRemoteSource getInstance(){
        if(instance == null){
            instance = new LoginDataRemoteSource();
        }
        return instance;
    }

    @Override
    public Observable<LoginData> getRemoteLoginData(@NonNull String name, @NonNull String pwd, @NonNull LoginType loginType) {
       Observable<LoginData> loginDataObservable = null;
       if(loginType == LoginType.TYPE_LOGIN){
           loginDataObservable = RetrofitClient.getInstance()
                   .create(RetrofitService.class)
                   .login(name,pwd);

       }else if(loginType == LoginType.TYPE_REGISTER){
           loginDataObservable = RetrofitClient.getInstance()
                   .create(RetrofitService.class)
                   .register(name,pwd,pwd);
       }

        return loginDataObservable
                .subscribeOn(Schedulers.io())
                .doOnNext(new Consumer<LoginData>() {
                    @Override
                    public void accept(LoginData loginData) throws Exception {
                        Log.i(TAG,""+loginData.toString());
                    }
                });
    }

    @Override
    public Observable<LoginDetailData> getLocalLoginData(@NonNull int UserId) {
        return null;
    }
}
