package com.yangys.data.retrofit;

/**
 * Created by yangys on 2018/9/5.
 */

public class Api {

    //Base API
    public static final String API_BASE = "http://www.wanandroid.com/";

    //获取文章列表
    public  static final String ARTICAL_LIST = API_BASE + "article/list/";

    //获取首页Banner
    public static final String BANNER = API_BASE + "banner/json";

    //登录
    public static final String LOGIN = API_BASE + "user/login/";

    //注册
    public static final String REGISTER = API_BASE + "user/register";

    //获得查询的文章
    public static final String QUERY_ARTICLES = API_BASE + "article/query/";

}
