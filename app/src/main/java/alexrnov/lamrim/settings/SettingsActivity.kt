package alexrnov.lamrim.settings

import alexrnov.lamrim.R
import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat

class SettingsActivity: AppCompatActivity(), PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.settings_activity)

    val toolbar = findViewById<Toolbar>(R.id.main_toolbar)
    setSupportActionBar(toolbar)

    supportActionBar?.let {

      it.setDisplayHomeAsUpEnabled(true) // enable the Up button
      it.setIcon(R.drawable.home_icon)

      val s = "<font color='#fffbbe'>" + this.getString(R.string.app_name) + "</font>"
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        it.title = Html.fromHtml(s, Html.FROM_HTML_MODE_LEGACY)
      } else {
        @Suppress("DEPRECATION")
        it.title = Html.fromHtml(s)
      }
    }

    supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, SettingsFragment())
            .commit()
  }

  // invoke when sub-settings is taps
  override fun onPreferenceStartFragment(caller: PreferenceFragmentCompat, pref: Preference): Boolean {

    val args = pref.extras
    val fragment = supportFragmentManager.fragmentFactory.instantiate(
            classLoader,
            pref.fragment)
    fragment.arguments = args
    fragment.setTargetFragment(caller, 0)
    supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()


    return true
  }

}