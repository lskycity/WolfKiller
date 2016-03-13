package com.dream.yzbb.wolfkiller.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dream.yzbb.wolfkiller.R;

/**
 * Created by liuzhaofeng on 2016/3/13.
 */
public class StartDayOrNightFragment extends Fragment implements View.OnClickListener {

    private static final String KEY = "key_is_night";

    @Override
    public void onClick(View v) {
        onStartClickListener.onStart(getArguments().getBoolean(KEY));
    }

    public interface OnStartClickListener {
        void onStart(boolean isNight);
    }

    public static Fragment create(boolean isNight) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(KEY, isNight);
        StartDayOrNightFragment startDayOrNightFragment = new StartDayOrNightFragment();
        startDayOrNightFragment.setArguments(bundle);
        return startDayOrNightFragment;
    }

    private OnStartClickListener onStartClickListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        onStartClickListener = (OnStartClickListener) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_start, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button button = (Button) view.findViewById(R.id.start);
        button.setText(getArguments().getBoolean(KEY)?R.string.start_night:R.string.start_daytime);
        button.setOnClickListener(this);
    }
}
