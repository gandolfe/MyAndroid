package com.yangys.data.login;

import android.support.annotation.NonNull;

import com.yangys.data.interfaces.LoginDataSource;
import com.yangys.model.LoginData;
import com.yangys.model.LoginDetailData;
import com.yangys.model.LoginType;

import io.reactivex.Observable;

/**
 * Created by yangys on 2018/9/5.
 */

public class loginDataLocalSource implements LoginDataSource {
    @Override
    public Observable<LoginData> getRemoteLoginData(@NonNull String name, @NonNull String pwd, @NonNull LoginType loginType) {
        return null;
    }

    @Override
    public Observable<LoginDetailData> getLocalLoginData(@NonNull int UserId) {
        return null;
    }
}
