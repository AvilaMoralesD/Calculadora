package com.dami.calculadora.ui.preferencias;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.preference.PreferenceFragmentCompat;

import com.dami.calculadora.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }
}
