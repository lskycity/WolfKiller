package com.dream.yzbb.wolfkiller.ui.daytime;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dream.yzbb.wolfkiller.R;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class DaytimeFragment extends Fragment implements View.OnClickListener {
    private Button mNextActionBtn;
    private TextView mDesc;
    private NextActionListener mNextActionListener;

    public DaytimeFragment() {
        // Required empty public constructor
    }

    public interface NextActionListener {
        void onNextDaytimeActionTriggered();
    }

    protected void init(){};

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() instanceof NextActionListener) {
            mNextActionListener = (NextActionListener) getActivity();
        } else {
            throw new RuntimeException("Attached activity should implement NextActionListeners");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    protected void initView(View rootView) {
        mDesc = (TextView) rootView.findViewById(R.id.desc);
        mDesc.setText(getActionDescription());
        mNextActionBtn = (Button) rootView.findViewById(R.id.next_event_btn);
        mNextActionBtn.setText(getNextActionText());
        mNextActionBtn.setOnClickListener(this);
    }

    protected abstract String getActionDescription();

    protected String getNextActionText() {
        return getString(R.string.next_event);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daytime, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.next_event_btn:
                if (mNextActionListener != null) {
                    mNextActionListener.onNextDaytimeActionTriggered();
                }
                break;
        }
    }
}
