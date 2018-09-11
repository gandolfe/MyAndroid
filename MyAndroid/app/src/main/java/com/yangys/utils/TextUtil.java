package com.yangys.utils;

import android.text.TextUtils;

/**
 * Created by yangys on 2018/9/3.
 */

public class TextUtil {

    public static boolean isInvalid(String str){
        if (TextUtils.isEmpty(str)||str.contains(" ")){
            return true;
        }
        return false;
    }

}
