/*
 * Copyright (C) 2012 CyanogenMod
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings.saber;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.provider.Settings;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;

public class PowerMenu extends SettingsPreferenceFragment {
    private static final String TAG = "PowerMenu";

    private static final String KEY_EXPANDED_DESKTOP = "power_menu_expanded_desktop";

    private CheckBoxPreference mExpandedDesktopPref;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.power_menu_settings);

        mExpandedDesktopPref = (CheckBoxPreference) findPreference(KEY_EXPANDED_DESKTOP);
        boolean showExpandedDesktopPref =
            getResources().getBoolean(R.bool.config_show_expandedDesktop);
        if (!showExpandedDesktopPref) {
            if (mExpandedDesktopPref != null) {
                getPreferenceScreen().removePreference(mExpandedDesktopPref);
            }
        } else {
            mExpandedDesktopPref.setChecked((Settings.System.getInt(getContentResolver(),
                Settings.System.POWER_MENU_EXPANDED_DESKTOP_ENABLED, 0) == 1));
        }
    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        boolean value;

        if (preference == mExpandedDesktopPref) {
            value = mExpandedDesktopPref.isChecked();
            Settings.System.putInt(getContentResolver(),
                    Settings.System.POWER_MENU_EXPANDED_DESKTOP_ENABLED,
                    value ? 1 : 0);
        } else {
            return super.onPreferenceTreeClick(preferenceScreen, preference);
        }

        return true;
    }

}
