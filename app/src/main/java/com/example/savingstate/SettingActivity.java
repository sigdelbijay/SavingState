package com.example.savingstate;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;

import androidx.annotation.Nullable;

public class SettingActivity extends PreferenceActivity {

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("settingActivity------->", this.toString());
        addPreferencesFromResource(R.xml.preferences);
    }


}
