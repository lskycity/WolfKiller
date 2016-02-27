package com.dream.yzbb.wolfkiller;

import android.content.Context;

import com.dream.yzbb.wolfkiller.service.RoleManager;

/**
 * Created by kevinbest on 16/2/27.
 */
public abstract class Factory {
    private static volatile Factory instance;

    public static Factory get()
    {
        return instance;
    }

    protected static void setInstance(Factory ins)
    {
        instance = ins;
    }

    public abstract Context getApplicationContext();

    public abstract RoleManager getRoleManager();
}
