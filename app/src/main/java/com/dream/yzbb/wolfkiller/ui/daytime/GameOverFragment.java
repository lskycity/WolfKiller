package com.dream.yzbb.wolfkiller.ui.daytime;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import com.dream.yzbb.wolfkiller.apputils.Constants;
import com.dream.yzbb.wolfkiller.ui.SetupNewGameActivity;

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
    protected boolean doPositiveAction() {
        //重新开始一局游戏
        Toast.makeText(getActivity(), "重新开始一局游戏", Toast.LENGTH_SHORT).show();
        Log.d(Constants.LOG_TAG, "重新开始一局游戏");
        startNewGame();
        return true;
    }

    @Override
    protected boolean doNegativeAction() {
        super.doNegativeAction();
        Toast.makeText(getActivity(), "退出游戏", Toast.LENGTH_SHORT).show();
        Log.d(Constants.LOG_TAG, "退出游戏");
        getActivity().finish();
        return true;
    }

    @Override
    protected String getActionDescription() {
        return "游戏结束,好人胜利";
    }

    @Override
    protected int getActionTargetNumber() {
        return 0;
    }

    private void startNewGame()
    {
        Intent intent = new Intent(getActivity(), SetupNewGameActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
