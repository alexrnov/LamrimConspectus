package alexrnov.lamrim

import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.preference.PreferenceFragmentCompat

class SettingsActivity: AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.settings_activity)


    //Toolbar collapsingToolbarLayout = findViewById(R.id.toolbar);

    //collapsingToolbarLayout.setTitle("Конспект ламрима");
    val toolbar = findViewById<Toolbar>(R.id.main_toolbar)
    setSupportActionBar(toolbar)


    val actionBar = supportActionBar
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true) // enable the Up button
      actionBar.setIcon(R.drawable.home_icon)
      actionBar.title = Html.fromHtml("<font color='#fffbbe'>" +
              this.getString(R.string.app_name) + "</font>")
    }


    supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, SettingsFragment())
            .commit()


  }


}

class SettingsFragment2 : PreferenceFragmentCompat() {
  override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
    setPreferencesFromResource(R.xml.preferences, rootKey)
  }
}