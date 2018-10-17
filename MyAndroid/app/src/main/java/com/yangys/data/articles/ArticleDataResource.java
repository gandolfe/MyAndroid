package com.yangys.data.articles;

import android.support.annotation.NonNull;

import com.yangys.model.ArticleDetailData;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by yangys on 2018/10/16.
 */

public interface ArticleDataResource {

    Observable<List<ArticleDetailData>> getArticles(@NonNull int page, @NonNull boolean froceUpdate , @NonNull boolean clearCache);

    Observable<List<ArticleDetailData>> queryArticles(@NonNull int page,@NonNull String keyWords,@NonNull boolean froceUpdate ,@NonNull boolean clearCache);


}
