package com.yangys.mvp.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.yangys.data.interfaces.LoginDataSource;
import com.yangys.data.login.LoginDataRepository;
import com.yangys.model.LoginData;
import com.yangys.model.LoginType;
import com.yangys.mvp.LoginContract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yangys on 2018/9/3.
 */

public class LoginPresenter implements LoginContract.Presenter{

    @NonNull
    private LoginContract.View view;

    @NonNull
    private LoginDataRepository loginDataRepository;

    private CompositeDisposable compositeDisposable;

    private static final String TAG = "LoginPresenter";

    public LoginPresenter(@NonNull LoginContract.View view,LoginDataRepository loginDataRepository){
        this.view = view;
        this.loginDataRepository = loginDataRepository;
        this.view.setPresenter(this);
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void login(String name, String password, @NonNull LoginType loginType) {
        Disposable disposable = loginDataRepository.getRemoteLoginData(name,password,loginType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<LoginData>() {
                    @Override
                    public void onNext(LoginData value) {

                        if(!view.isActive()){
                            return;
                        }
                        if(value.getErrorCode() == -1){
                            view.showLoginError(value.getErrorMsg());
                        }else{
                            Log.i(TAG,"value:"+value.getData().getUsername());
                            view.saveUserToPreference(value.getData());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if(view.isActive()){

                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        compositeDisposable.add(disposable);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        compositeDisposable.clear();
    }
}
