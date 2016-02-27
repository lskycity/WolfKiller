package com.dream.yzbb.wolfkiller.service;

import android.content.Context;

import com.dream.yzbb.wolfkiller.Factory;
import com.dream.yzbb.wolfkiller.R;
import com.dream.yzbb.wolfkiller.apputils.XmlUtils;
import com.dream.yzbb.wolfkiller.entity.Role;
import com.dream.yzbb.wolfkiller.entity.RoleDistributionInfo;

import java.util.List;

/**
 * Created by kevinbest on 16/2/27.
 */
public class RoleManager {
    private List<Role> roles;

    public RoleManager() {
        roles = XmlUtils.parseRolesFromXml(Factory.get().getApplicationContext().getResources().openRawResource(R.raw.roles));
    }

    public List<Role> getRoles() {
        return roles;
    }

    public RoleDistributionInfo getRoleDistributionInfo(int count){
        return null;
    }

    public Role getRoleByID(int id){
        return null;
    }
}
