package com.dream.yzbb.wolfkiller.ui.daytime;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dream.yzbb.wolfkiller.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameOverFragment extends ActionDaytimeFragment {


    public GameOverFragment() {
        // Required empty public constructor
    }

    @Override
    protected String getPositiveActionText() {
        return "再玩一局";
    }

    @Override
    protected String getNegativeActionText() {
        return "退出游戏";
    }

    @Override
    protected void doPositiveAction() {
        //重新开始一局游戏
        Toast.makeText(getActivity(), "重新开始一局游戏", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void doNegativeAction() {
        super.doNegativeAction();
    }

    @Override
    protected String getActionDescription() {
        return "游戏结束,好人胜利";
    }

    @Override
    protected int getActionTargetNumber() {
        return 0;
    }
}
