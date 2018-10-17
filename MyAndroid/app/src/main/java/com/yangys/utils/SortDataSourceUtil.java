package com.yangys.utils;

import com.yangys.model.ArticleDetailData;

/**
 * Created by yangys on 2018/10/16.
 */

public class SortDataSourceUtil {

    public static int sortArticleData(ArticleDetailData articleDetailData1,ArticleDetailData articleDetailData2){
        if(articleDetailData1.getPublishTime()>articleDetailData2.getPublishTime()){
            return 1;
        }else{
            return -1;
        }
    }

}
