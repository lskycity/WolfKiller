package com.dream.yzbb.wolfkiller.service;

import android.content.Context;

import com.dream.yzbb.wolfkiller.Factory;
import com.dream.yzbb.wolfkiller.R;
import com.dream.yzbb.wolfkiller.apputils.XmlUtils;
import com.dream.yzbb.wolfkiller.entity.Role;
import com.dream.yzbb.wolfkiller.entity.RoleDistributionInfo;

import java.util.List;
import java.util.Objects;

/**
 * Created by kevinbest on 16/2/27.
 */
public class RoleManager {
    private static RoleManager instance;
    private static List<Role> roles;
    private static final Object mLock = new Object();

    public static RoleManager getInstance() {
        synchronized (mLock) {
            if (instance == null) {
                instance = new RoleManager();
            }
        }
        return instance;
    }

    private RoleManager() {
        roles = XmlUtils.parseRolesFromXml(Factory.get().getApplicationContext().getResources().openRawResource(R.raw.roles));
    }

    public List<Role> getRoles() {
        return roles;
    }

    public static RoleDistributionInfo getRoleDistributionInfo(int count) {
        return null;
    }

    public static Role getRoleByID(int id) {
        if (instance == null) {
            throw new RuntimeException("Should initiate RoleManager first to init roles!!!");
        }
        for (Role role : roles) {
            if (role.getRoleID() == id) {
                return role;
            }
        }
        return null;
    }
}
