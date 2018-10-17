package com.yangys.data.articles;

import android.support.annotation.NonNull;

import com.yangys.data.retrofit.RetrofitClient;
import com.yangys.data.retrofit.RetrofitService;
import com.yangys.model.ArticleDetailData;
import com.yangys.model.ArticlesData;
import com.yangys.utils.SortDataSourceUtil;

import java.util.Comparator;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * Created by yangys on 2018/10/17.
 */

public class ArticleRemote implements ArticleDataResource {
    @Override
    public Observable<List<ArticleDetailData>> getArticles(@NonNull int page, @NonNull boolean froceUpdate, @NonNull boolean clearCache) {

        return RetrofitClient
                .getInstance()
                .create(RetrofitService.class)
                .getArticles(page)
                .filter(new Predicate<ArticlesData>() {
                    @Override
                    public boolean test(ArticlesData articlesData) throws Exception {
                        return articlesData.getErrorCode() !=-1;
                    }
                })
                .flatMap(new Function<ArticlesData, ObservableSource<List<ArticleDetailData>>>() {
                    @Override
                    public ObservableSource<List<ArticleDetailData>> apply(ArticlesData articlesData) throws Exception {
                        return Observable.fromIterable(articlesData.getData().getDatas())
                                .toSortedList(new Comparator<ArticleDetailData>() {
                                    @Override
                                    public int compare(ArticleDetailData o1, ArticleDetailData o2) {
                                        return SortDataSourceUtil.sortArticleData(o1,o2);
                                    }
                                }).toObservable();
                    }
                });
    }

    @Override
    public Observable<List<ArticleDetailData>> queryArticles(@NonNull int page, @NonNull String keyWords, @NonNull boolean froceUpdate, @NonNull boolean clearCache) {
        return null;
    }
}
