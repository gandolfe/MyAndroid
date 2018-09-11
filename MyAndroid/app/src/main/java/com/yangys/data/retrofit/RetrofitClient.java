package com.yangys.data.retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yangys on 2018/9/3.
 */

public class RetrofitClient {

    private RetrofitClient(){

    }

    private static class ClientHolder{
        private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cookieJar(new CookieManager())
                .build();

        private static Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.API_BASE)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public static Retrofit getInstance(){
        return ClientHolder.retrofit;
    }




}
