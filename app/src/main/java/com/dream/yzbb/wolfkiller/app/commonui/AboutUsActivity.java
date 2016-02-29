package com.dream.yzbb.wolfkiller.app.commonui;

import android.support.v4.app.Fragment;

import com.dream.yzbb.wolfkiller.app.SingleFragmentActivity;


public class AboutUsActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new AboutUsFragment();
    }

}
