/*
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
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.provider.Settings;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;

public class NavBarSettings extends SettingsPreferenceFragment implements OnPreferenceChangeListener {

    private static final String KEY_NAV_BUTTONS_HEIGHT = "nav_buttons_height";

    private ListPreference mNavButtonsHeight;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.navigation_bar_settings);

        mNavButtonsHeight = (ListPreference) findPreference(KEY_NAV_BUTTONS_HEIGHT);
        mNavButtonsHeight.setOnPreferenceChangeListener(this);
        int statusNavButtonsHeight = Settings.System.getInt(getActivity().getApplicationContext().getContentResolver(),
                 Settings.System.NAV_BUTTONS_HEIGHT, 48);
        mNavButtonsHeight.setValue(String.valueOf(statusNavButtonsHeight));
        mNavButtonsHeight.setSummary(mNavButtonsHeight.getEntry());
        
        }

    public boolean onPreferenceChange(Preference preference, Object objValue) {
        if (preference == mNavButtonsHeight) {
            int statusNavButtonsHeight = Integer.valueOf((String) objValue);
            int index = mNavButtonsHeight.findIndexOfValue((String) objValue);
            Settings.System.putInt(getActivity().getApplicationContext().getContentResolver(),
                    Settings.System.NAV_BUTTONS_HEIGHT, statusNavButtonsHeight);
            mNavButtonsHeight.setSummary(mNavButtonsHeight.getEntries()[index]);
            return true;
        }
        return true;
    }
}
