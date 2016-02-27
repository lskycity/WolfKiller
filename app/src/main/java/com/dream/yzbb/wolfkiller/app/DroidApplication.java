package com.dream.yzbb.wolfkiller.app;

import android.app.Application;

import com.dream.yzbb.wolfkiller.Factory;
import com.dream.yzbb.wolfkiller.FactoryImpl;

/**
 * Created by liuzhaofeng on 2016/2/13.
 */
public class DroidApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FactoryImpl.register(this);
    }
}
