package com.example.mycalc_1;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by Админ on 07.01.2017.
 */

public class PrefActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_screen);
    }
}
