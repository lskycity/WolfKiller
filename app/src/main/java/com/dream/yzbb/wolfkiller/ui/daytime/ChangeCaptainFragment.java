package com.dream.yzbb.wolfkiller.ui.daytime;

import android.widget.Toast;

/**
 * Created by kevinbest on 16/3/20.
 */
public class ChangeCaptainFragment extends ActionDaytimeFragment {

    @Override
    protected String getPositiveActionText() {
        return "更换";
    }

    @Override
    protected String getActionDescription() {
        return "请重新选择警长";
    }

    @Override
    protected void doPositiveAction() {
        Toast.makeText(getActivity(),"重新选定了警长",Toast.LENGTH_SHORT).show();
    }
}
