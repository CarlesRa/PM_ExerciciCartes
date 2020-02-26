package com.carlesramos.pm_exercicicartes.fragments;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.carlesramos.pm_exercicicartes.R;

/**
 * @author Juan Carlos Ramos.
 */
public class PreferencesFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }
}
