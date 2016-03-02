package com.dream.yzbb.wolfkiller.service;

import android.content.Context;
import android.util.Xml;

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
    private List<Role> roles;
    private List<RoleDistributionInfo> roleDistributionInfo;
    private static final Object mLock = new Object();

    public static RoleManager getInstance() {
        synchronized (mLock) {
            if (instance == null) {
                instance = new RoleManager();
                instance.init();
            }
        }
        return instance;
    }

    private void init() {
        roles = XmlUtils.parseRolesFromXml(Factory.get().getApplicationContext().getResources().openRawResource(R.raw.roles));
        roleDistributionInfo = XmlUtils.parseRoleDistributionFromXml(Factory.get().getApplicationContext().getResources().openRawResource(R.raw.games));
    }

    private RoleManager() {
    }

    public List<Role> getRoles() {
        return roles;
    }

    public RoleDistributionInfo getRoleDistributionInfo(int count) {
        if (instance == null) {
            throw new RuntimeException("Should initiate RoleManager first to init roles!!!");
        }
        return getRoleDistributionInfoByCount(count);
    }

    private  RoleDistributionInfo getRoleDistributionInfoByCount(int count) {
        for (int i = 0; i < roleDistributionInfo.size(); i++) {
            if (roleDistributionInfo.get(i).getPlayerCount() == count) {
                return roleDistributionInfo.get(i);
            }
        }
        return null;
    }

    public Role getRoleByID(int id) {
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
