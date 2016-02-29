package com.dream.yzbb.wolfkiller.ui;

import android.app.FragmentTransaction;
import android.os.Bundle;

import com.dream.yzbb.wolfkiller.R;
import com.dream.yzbb.wolfkiller.app.BaseActivity;


public class SettingsActivity extends BaseActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle(R.string.main_menu_setting);
        if(savedInstanceState==null)
        {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.add(R.id.container, new SettingsFragment());
            transaction.commitAllowingStateLoss();
        }
    }

}
