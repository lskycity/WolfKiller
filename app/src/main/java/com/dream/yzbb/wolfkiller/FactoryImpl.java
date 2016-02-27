package com.dream.yzbb.wolfkiller;

import android.content.Context;

import com.dream.yzbb.wolfkiller.service.RoleManager;

/**
 * Created by kevinbest on 16/2/27.
 */
public class FactoryImpl extends Factory {
    private Context mApplicationContext;
    private RoleManager mRoleManager;

    @Override
    public Context getApplicationContext() {
        return mApplicationContext;
    }

    public static Factory register(Context applicationContext) {
        FactoryImpl factory = new FactoryImpl();
        setInstance(factory);

        factory.mApplicationContext = applicationContext;
        factory.mRoleManager = new RoleManager();
        return factory;
    }

    private FactoryImpl() {
    }

    @Override
    public RoleManager getRoleManager() {
        return mRoleManager;
    }
}
