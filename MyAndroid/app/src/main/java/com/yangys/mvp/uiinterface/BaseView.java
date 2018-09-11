package com.yangys.mvp.uiinterface;

import android.view.View;

/**
 * Created by yangys on 2018/9/3.
 */

public interface BaseView<T> {

    void initView(View view);

    void setPresenter(T presenter);
}
