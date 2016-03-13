package com.dream.yzbb.wolfkiller.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dream.yzbb.wolfkiller.Factory;
import com.dream.yzbb.wolfkiller.R;
import com.dream.yzbb.wolfkiller.entity.Player;
import com.dream.yzbb.wolfkiller.entity.Witch;

/**
 * Created by liuzhaofeng on 2016/3/13.
 */
public class WitchEventFragment extends NightEventFragment {

    private Witch witch;

    public static Fragment create(int roleId) {
        Bundle args = new Bundle();
        args.putInt(Key_role, roleId);
        WitchEventFragment fragment = new WitchEventFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private TextView mDescription;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        witch = (Witch) role;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDescription = (TextView) view.findViewById(R.id.description);
        mDescription.setText(getString(R.string.witch_description_save) + Factory.get().getGameManager().getNightRoundManager().latestNightRoundRecord().getKilledPerson());
    }

    @Override
    public void onClick(View v) {
        if(witch.getActionIndex() == 0) {
            if(v.getId() == R.id.positive) {
                Factory.get().getGameManager().getNightRoundManager().doAction(role);
            }
            witch.nextAction();
            listener.shouldChoosePlayer(witch.getActionTargetNumber());
            mDescription.setText(R.string.witch_description_kill);
        } else {
            if(v.getId() == R.id.positive) {
                Player[] players = listener.getPlayers();
                if (players.length == role.getActionTargetNumber()) {
                    Factory.get().getGameManager().getNightRoundManager().doAction(role, players);
                    listener.actionFinished();
                    witch.nextAction();
                } else {
                    Toast.makeText(getActivity(), "need select player", Toast.LENGTH_LONG).show();
                }
            } else if(v.getId() == R.id.negative) {
                listener.actionFinished();
                witch.nextAction();
            }
        }

    }
}
