package com.rendiputra.githubuser.ui.preference

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.rendiputra.githubuser.R

class UserPreference : PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener {

    private lateinit var THEME: String
    private lateinit var switchThemePreference: SwitchPreference

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)

        THEME = resources.getString(R.string.key_themes)
        switchThemePreference = findPreference<SwitchPreference>(THEME) as SwitchPreference

        setPreferences()
    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences?.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences?.unregisterOnSharedPreferenceChangeListener(this)
    }

    private fun setPreferences() {
        val sh = preferenceManager.sharedPreferences
        if (sh != null) {
            switchThemePreference.isChecked = sh.getBoolean(THEME, DEFAULT_THEME)
        }
    }

    companion object {
        private const val DEFAULT_THEME = false
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String?) {
        if (key == THEME) {
            switchThemePreference.isChecked = sharedPreferences.getBoolean(THEME, DEFAULT_THEME)
            val themeMode = if (switchThemePreference.isChecked) {
                AppCompatDelegate.MODE_NIGHT_YES
            } else {
                AppCompatDelegate.MODE_NIGHT_NO
            }

            AppCompatDelegate.setDefaultNightMode(themeMode)
        }
    }


}