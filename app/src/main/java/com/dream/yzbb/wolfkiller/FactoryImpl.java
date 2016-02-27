package com.dream.yzbb.wolfkiller;

import android.content.Context;

import com.dream.yzbb.wolfkiller.service.GameManager;
import com.dream.yzbb.wolfkiller.service.RoleManager;

/**
 * Created by kevinbest on 16/2/27.
 */
public class FactoryImpl extends Factory {
    private Context mApplicationContext;
    private RoleManager mRoleManager;
    private GameManager mGameManager;

    @Override
    public Context getApplicationContext() {
        return mApplicationContext;
    }

    public static Factory register(Context applicationContext) {
        FactoryImpl factory = new FactoryImpl();
        setInstance(factory);

        factory.mApplicationContext = applicationContext;
        factory.mRoleManager = new RoleManager();
        factory.mGameManager = new GameManager();
        return factory;
    }

    private FactoryImpl() {
    }

    @Override
    public RoleManager getRoleManager() {
        return mRoleManager;
    }

    @Override
    public GameManager getGameManager() {
        return mGameManager;
    }
}
