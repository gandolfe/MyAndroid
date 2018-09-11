package com.yangys.data.interfaces;

import android.support.annotation.NonNull;

import com.yangys.model.LoginData;
import com.yangys.model.LoginDetailData;
import com.yangys.model.LoginType;

import io.reactivex.Observable;

/**
 * Created by yangys on 2018/9/3.
 */

public interface LoginDataSource {

    Observable<LoginData> getRemoteLoginData(@NonNull String name, @NonNull String pwd, @NonNull LoginType loginType);

    Observable<LoginDetailData> getLocalLoginData(@NonNull int UserId);
}
