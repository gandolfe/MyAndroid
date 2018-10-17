package com.yangys.mvp.presenter;

import com.yangys.data.articles.ArticleDataRepository;
import com.yangys.model.ArticleDetailData;
import com.yangys.mvp.ArticleContract;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yangys on 2018/10/13.
 */

public class ArticlePresenter implements ArticleContract.Presenter {

    private ArticleContract.View view;
    private CompositeDisposable compositeDisposable;
    private ArticleDataRepository dataRepository;

    public ArticlePresenter(ArticleContract.View view, ArticleDataRepository articleDataRepository){

        this.dataRepository = articleDataRepository;
        this.view = view;
        view.setPresenter(this);
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void getArticles(int page, boolean forceUpdate, boolean clearCache) {
        Disposable disposable = dataRepository.getArticles(page,forceUpdate,clearCache)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<ArticleDetailData>>() {
                    @Override
                    public void onNext(List<ArticleDetailData> value) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        compositeDisposable.add(disposable);

    }

    @Override
    public void getBanner() {

    }

    @Override
    public void autoLogin(String userName, String password) {

    }
}
