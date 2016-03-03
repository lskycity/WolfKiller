package com.dream.yzbb.wolfkiller.utils;

import android.view.View;

/**
 * Created by liuzhaofeng on 2016/3/3.
 */
public class ViewUtils {

    public static void setVisible(View view, Boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    public static boolean isVisible(View view) {
       return view.getVisibility() == View.VISIBLE;
    }

}
