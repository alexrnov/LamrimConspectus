package alexrnov.lamrim.settings

import alexrnov.lamrim.R
import android.os.Bundle
import android.util.Log
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat

class SettingsFragment : PreferenceFragmentCompat() {
  override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
    setPreferencesFromResource(R.xml.preferences, rootKey)

    val listPreference: ListPreference? = findPreference("font_size") as ListPreference?

    listPreference!!.onPreferenceChangeListener = Preference.OnPreferenceChangeListener { _, newValue ->
      //preference.summary = newValue.toString() // change summary under preference name
      Log.i("P", "click value = $newValue")
      true
    }

    /* // click by preferences
    findPreference<Preference>("font_size")!!.onPreferenceClickListener = Preference.OnPreferenceClickListener {
      Log.i("P", "preference click")
      false
    }
     */
  }

  /* // click by preference
  override fun onPreferenceTreeClick(preference: Preference?): Boolean {
    Log.i("P", "preference tree click")
    return super.onPreferenceTreeClick(preference)
  }
  */
}