package com.yangys.data.retrofit;

import com.yangys.model.ArticlesData;
import com.yangys.model.LoginData;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by yangys on 2018/9/5.
 */

public interface RetrofitService {

    @FormUrlEncoded
    @POST(Api.LOGIN)
    Observable<LoginData> login(@Field("username") String username,@Field("password") String password);

    @FormUrlEncoded
    @POST(Api.REGISTER)
    Observable<LoginData> register(@Field("username") String username,@Field("password") String password,@Field("repassword") String repassword);


    //获取文章
    @FormUrlEncoded
    @GET(Api.ARTICAL_LIST)
    Observable<ArticlesData> getArticles(@Path("page") int page);
}
