package com.yangys.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yangys.activity.MainActivity;
import com.yangys.model.ArticleDetailData;
import com.yangys.model.BannerDetailData;
import com.yangys.mvp.ArticleContract;
import com.yangys.utils.SettingsUtil;

import java.util.List;

/**
 * Created by yangys on 2018/9/15.
 */

public class ArticleFragment extends Fragment implements ArticleContract.View{


    private String userName ,passWord;
    private boolean isFirstLoad = true;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getContext());
        userName = sp.getString(SettingsUtil.USERNAME,"");
        passWord = sp.getString(SettingsUtil.PASSWORD,"");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(isFirstLoad){
            
            isFirstLoad = false;
        }
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void setPresenter(ArticleContract.Presenter presenter) {

    }

    @Override
    public void autoLogin(String userName, String passWord) {

    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void setLoadingIndicator(boolean isloding) {

    }

    @Override
    public void showArticles(List<ArticleDetailData> datas) {

    }

    @Override
    public void showEmptyView(boolean isshow) {

    }

    @Override
    public void showBanner(List<BannerDetailData> datas) {

    }

    @Override
    public void hideBanner() {

    }

    @Override
    public void showAutoLoginFail() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getContext());
        sp.edit().putBoolean(SettingsUtil.KEY_SKIP_LOGIN_PAGE,false).apply();
        ((MainActivity)getActivity()).moveToLoginActivity();
        getActivity().finish();
    }
}
