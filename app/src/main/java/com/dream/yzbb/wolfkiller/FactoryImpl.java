package com.dream.yzbb.wolfkiller;

import android.content.Context;

/**
 * Created by kevinbest on 16/2/27.
 */
public class FactoryImpl extends Factory {
    private Context mApplicationContext;

    @Override
    public Context getApplicationContext() {
        return mApplicationContext;
    }

    public static Factory register(Context applicationContext) {
        FactoryImpl factory = new FactoryImpl();
        setInstance(factory);

        factory.mApplicationContext = applicationContext;
        return factory;
    }

    private FactoryImpl() {
    }
}
