package com.dream.yzbb.wolfkiller.app.commonui;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dream.yzbb.wolfkiller.R;
import com.dream.yzbb.wolfkiller.apputils.Constants;
import com.dream.yzbb.wolfkiller.utils.SharedPreUtils;
import com.dream.yzbb.wolfkiller.utils.ViewUtils;


/**
 * A simple {@link Fragment} subclass.
 */
public class DisclaimerFragment extends Fragment implements View.OnClickListener {


    public DisclaimerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle(R.string.disclaimer);
        return inflater.inflate(R.layout.fragment_disclaimer, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View buttonBar = view.findViewById(R.id.button_bar);
        ViewUtils.setVisible(buttonBar, !SharedPreUtils.getBoolean(getActivity(), Constants.KEY_SHARED_PRE_DISCLAIMER_AGREED));

        if(ViewUtils.isVisible(buttonBar)) {
            buttonBar.findViewById(R.id.disagree).setOnClickListener(this);
            buttonBar.findViewById(R.id.agree).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.disagree) {
            getActivity().setResult(Activity.RESULT_CANCELED);
            getActivity().finish();
        } else if(v.getId() == R.id.agree) {
            SharedPreUtils.putBoolean(getActivity(), Constants.KEY_SHARED_PRE_DISCLAIMER_AGREED, true);
            getActivity().setResult(Activity.RESULT_OK);
            getActivity().finish();
        }
    }

}
