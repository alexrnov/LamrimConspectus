package alexrnov.lamrim

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat

class AboutSettingsFragment : PreferenceFragmentCompat() {
  override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
    setPreferencesFromResource(R.xml.preferences_about_app, rootKey)
  }
}