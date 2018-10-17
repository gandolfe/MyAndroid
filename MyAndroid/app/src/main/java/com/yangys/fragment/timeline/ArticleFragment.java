package com.yangys.fragment.timeline;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yangys.activity.MainActivity;
import com.yangys.model.ArticleDetailData;
import com.yangys.model.BannerDetailData;
import com.yangys.mvp.ArticleContract;
import com.yangys.utils.SettingsUtil;
import com.youth.banner.Banner;

import java.util.List;

import example.com.myandroid.R;

/**
 * Created by yangys on 2018/9/15.
 */

public class ArticleFragment extends Fragment implements ArticleContract.View{

    private NestedScrollView scrollView;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private ArticleContract.Presenter presenter;
    private Banner banner;

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
        View view  = inflater.inflate(R.layout.artical_fragment_layout,container,false);
        initView(view);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
        return view;
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
        scrollView = view.findViewById(R.id.nested_scroll_view);
        recyclerView = view.findViewById(R.id.recycler_view);
        refreshLayout = view.findViewById(R.id.refresh_layout);
        banner = new Banner(getActivity());

    }

    @Override
    public void setPresenter(ArticleContract.Presenter presenter) {
        this.presenter = presenter;
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
