package com.dream.yzbb.wolfkiller.ui;



import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v4.app.Fragment;

import com.dream.yzbb.wolfkiller.R;
import com.dream.yzbb.wolfkiller.utils.AppUtils;
import com.dream.yzbb.wolfkiller.utils.SharedPreUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends PreferenceFragment {



    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        getPreferenceManager().setSharedPreferencesName(SharedPreUtils.SHARED_PREFERENCE_NAME);
        getPreferenceScreen().findPreference("version").setSummary(getString(R.string.version, AppUtils.getVersionName(getActivity())));
    }


}
