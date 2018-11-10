package com.example.zachl.tcubed;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

public abstract class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        // Load the preferences from an XML resource
        setPreferencesFromResource(R.xml.app_preferences, rootKey);
    }
}