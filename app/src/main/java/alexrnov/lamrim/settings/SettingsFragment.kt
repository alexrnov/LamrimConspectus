package alexrnov.lamrim.settings

import alexrnov.lamrim.R
import alexrnov.lamrim.showSnackbar
import android.os.Bundle
import android.util.Log
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat

class SettingsFragment : PreferenceFragmentCompat() {
  override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
    setPreferencesFromResource(R.xml.preferences, rootKey)

    val fontSize: ListPreference? = findPreference("font_size") as ListPreference?

    if (fontSize != null) {
      if (fontSize.value == null) {
        // to ensure we don't get a null value set first value by default
        fontSize.setValueIndex(0)
      }

      fontSize.onPreferenceChangeListener = Preference.OnPreferenceChangeListener { _, newValue ->
        //preference.summary = newValue.toString() // change summary under preference name
        Log.i("P", "click value = $newValue")
        showSnackbar(this.requireView(), "adgfder")
        true
      }  
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