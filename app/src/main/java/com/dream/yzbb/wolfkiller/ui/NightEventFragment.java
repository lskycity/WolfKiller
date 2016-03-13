package com.dream.yzbb.wolfkiller.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dream.yzbb.wolfkiller.Factory;
import com.dream.yzbb.wolfkiller.R;
import com.dream.yzbb.wolfkiller.entity.NightRole;
import com.dream.yzbb.wolfkiller.entity.Player;

/**
 * Created by liuzhaofeng on 2016/3/13.
 */
public class NightEventFragment extends Fragment implements View.OnClickListener {
    protected static final String Key_role = "key_role";

    public interface NightEventListener {
        void shouldChoosePlayer(int playNumber);
        Player[] getPlayers();
        void actionFinished();
    }

    public static Fragment create(int roleId) {
        Bundle args = new Bundle();
        args.putInt(Key_role, roleId);
        NightEventFragment fragment = new NightEventFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private TextView description;
    private Button positive;
    private Button negative;
    protected NightRole role;

    protected NightEventListener listener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listener = (NightEventListener) activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        role = (NightRole) Factory.get().getRoleManager().getRoleByID(getArguments().getInt(Key_role));
        listener.shouldChoosePlayer(role.getActionTargetNumber());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.night_event_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        positive = (Button) view.findViewById(R.id.positive);
        negative = (Button) view.findViewById(R.id.negative);
        description = (TextView) view.findViewById(R.id.description);
        description.setText(role.getDescription());
        positive.setOnClickListener(this);
        negative.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.positive) {
            Player[] players = listener.getPlayers();
            if (players.length == role.getActionTargetNumber()) {
                Factory.get().getGameManager().getNightRoundManager().doAction(role, players);
                listener.actionFinished();
            } else {
                Toast.makeText(getActivity(), "need select player", Toast.LENGTH_LONG).show();
            }
        } else if(v.getId() == R.id.negative) {
            listener.actionFinished();
        }
    }
}
