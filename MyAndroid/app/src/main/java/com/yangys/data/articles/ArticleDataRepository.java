package com.yangys.data.articles;

import android.support.annotation.NonNull;

import com.yangys.model.ArticleDetailData;
import com.yangys.utils.SortDataSourceUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Consumer;

/**
 * Created by yangys on 2018/10/13.
 */

public class ArticleDataRepository implements ArticleDataResource{


    private ArticleDataResource remoteResource;

    private Map<Integer ,ArticleDetailData> articleCache;


    public ArticleDataRepository(ArticleDataResource remoteResource){
        this.remoteResource = remoteResource;
    }

    @Override
    public Observable<List<ArticleDetailData>> getArticles(@NonNull int page, @NonNull boolean forceUpdate, @NonNull final boolean clearCache) {

        //!forceUpdate即用户按home键然后再返回我们App的情况，这时候直接返回缓存的列表
        if(!forceUpdate && articleCache!=null){
            return Observable.fromIterable(new ArrayList<ArticleDetailData>(articleCache.values()))
                    .toSortedList(new Comparator<ArticleDetailData>() {  //这是一个比较器，
                        @Override                                        //前一个对象大返回正数，小返回负数，等返回0
                        public int compare(ArticleDetailData o1, ArticleDetailData o2) {
                            return SortDataSourceUtil.sortArticleData(o1,o2);
                        }
                    }).toObservable();
        }

        //forceUpdate && !clearCache即用户向下滑动列表的情况，我们需要请求下一页的数据，并保存到缓存中
        if(!clearCache && articleCache!=null){
            Observable<List<ArticleDetailData>> o1 = remoteResource.getArticles(page,forceUpdate,clearCache)
                    .doAfterNext(new Consumer<List<ArticleDetailData>>() {

                        @Override
                        public void accept(List<ArticleDetailData> articleDetailData) throws Exception {
                            refreshArticleCache(clearCache,articleDetailData);
                        }
                    });

            Observable<List<ArticleDetailData>> o2 = Observable
                    .fromIterable(new ArrayList<ArticleDetailData>(articleCache.values()))
                    .toSortedList(new Comparator<ArticleDetailData>() {
                        @Override
                        public int compare(ArticleDetailData o1, ArticleDetailData o2) {
                            return SortDataSourceUtil.sortArticleData(o1,o2);
                        }
                    }).toObservable();

            //获取到的缓存数据加上新请求的数据，合并到一起发送
            return Observable.merge(o1,o2).collect(new Callable<List<ArticleDetailData>>() {  //在这里定义返回的数据类型
                @Override
                public List<ArticleDetailData> call() throws Exception {
                    return new ArrayList<>();
                }
            }, new BiConsumer<List<ArticleDetailData>, List<ArticleDetailData>>() {
                @Override
                public void accept(List<ArticleDetailData> list, List<ArticleDetailData> list2) throws Exception {
                    list.addAll(list2);
                }
            }).toObservable();
        }


            //否则返回常规的网络请求
        return remoteResource.getArticles(page,forceUpdate,clearCache)
                .doAfterNext(new Consumer<List<ArticleDetailData>>() {

                    @Override
                    public void accept(List<ArticleDetailData> articleDetailData) throws Exception {
                        refreshArticleCache(clearCache,articleDetailData);
                    }
                });
    }

    @Override
    public Observable<List<ArticleDetailData>> queryArticles(@NonNull int page, @NonNull String keyWords, @NonNull boolean froceUpdate, @NonNull boolean clearCache) {
        return null;
    }


    private void refreshArticleCache(@NonNull boolean clearCache , @NonNull List<ArticleDetailData> list){
        if(articleCache == null){
            articleCache = new LinkedHashMap<>();
        }
        if(clearCache){
            articleCache.clear();
        }
        for (ArticleDetailData data:list){
            articleCache.put(data.getId(),data);
        }
    }

}
