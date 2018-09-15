package com.yangys.fragment;

import android.support.v4.app.Fragment;
import android.view.View;

import com.yangys.model.ArticleDetailData;
import com.yangys.model.BannerDetailData;
import com.yangys.mvp.ArticleContract;

import java.util.List;

/**
 * Created by yangys on 2018/9/15.
 */

public class ArticleFragment extends Fragment implements ArticleContract.View{


    @Override
    public void initView(View view) {

    }

    @Override
    public void setPresenter(ArticleContract.Presenter presenter) {

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

    }
}
