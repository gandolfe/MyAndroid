package com.yangys.mvp;

import com.yangys.model.ArticleDetailData;
import com.yangys.model.BannerDetailData;
import com.yangys.mvp.presenter.BasePresenter;
import com.yangys.mvp.uiinterface.BaseView;

import java.util.List;

/**
 * Created by yangys on 2018/9/15.
 */

public interface ArticleContract {

    interface Presenter extends BasePresenter {

        void getArticles(int page, boolean forceUpdate, boolean clearCache);

        void getBanner();

        void autoLogin(String userName, String password);
    }

    interface View extends BaseView<Presenter>{

        void autoLogin(String userName,String passWord);

        boolean isActive();

        void setLoadingIndicator(boolean isloding);

        void showArticles(List<ArticleDetailData> datas);

        void showEmptyView(boolean isshow);

        void showBanner(List<BannerDetailData> datas);

        void hideBanner();

        void showAutoLoginFail();
    }
}
