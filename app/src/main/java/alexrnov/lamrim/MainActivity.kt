package alexrnov.lamrim

import alexrnov.lamrim.settings.SettingsActivity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
  private var recyclerView: RecyclerView? = null
  private var adapter: MainMenuAdapter? = null
  private var layoutManager: RecyclerView.LayoutManager? = null
  private var dualPane = false
  private val TAG = "P"

  private lateinit var textStyleObserver: TextStyleObserver

  override fun onCreate(savedInstanceState: Bundle?) {

    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    //for java: TextViewModel model = new ViewModelProvider(this).get(TextViewModel.class);
    val v:TextViewModel by viewModels()

    val container = findViewById<View>(R.id.fragment_container)
    dualPane = container != null && container.visibility == View.VISIBLE
    recyclerView = findViewById<View>(R.id.main_menu) as RecyclerView
    // use this setting to improve performance if you know that changes
    // in content do not change the layout size of the RecyclerView
    recyclerView!!.setHasFixedSize(true)
    // LinearLayoutManager arranges the items in a one-dimensional list
    layoutManager = LinearLayoutManager(this)
    recyclerView!!.layoutManager = layoutManager

    // Check whether we're recreating a previously destroyed instance.
    // If yes, then get the item number, if not, then get 0 for first item
    val item: String = savedInstanceState?.getString(SELECT_ITEM) ?: "0"

    adapter = MainMenuAdapter(item, dualPane, this)
    recyclerView!!.adapter = adapter

    val toolbar = findViewById<Toolbar>(R.id.main_toolbar)
    setSupportActionBar(toolbar)
    val actionBar = supportActionBar
    if (actionBar != null) {
      actionBar.setIcon(R.drawable.home_icon)
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        // FROM_HTML_MODE_LEGACY is the behaviour that was used for versions
        // below android N we are using this flag to give a consistent behaviour
        actionBar.title = Html.fromHtml("<font color='#fffbbe'>" +
                this.getString(R.string.app_name) + "</font>", Html.FROM_HTML_MODE_LEGACY)
      } else {
        @Suppress("DEPRECATION")
        actionBar.title = Html.fromHtml("<font color='#fffbbe'>" +
                this.getString(R.string.app_name) + "</font>")
      }
    }
    getScreenSizeWithNavBar(this)
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    val inflater = menuInflater
    inflater.inflate(R.menu.menu_layout, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when (item.itemId) {
      R.id.action_about -> {
        val about = AboutDialogFragment()
        about.show(this.supportFragmentManager, "tag")
        true
      }
      R.id.action_settings -> {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
        true
      }
      else -> super.onOptionsItemSelected(item)
    }
  }

  override fun onSaveInstanceState(outState: Bundle) {
    outState.run {
      putString(SELECT_ITEM, adapter?.currentSelectId) // save select item
    }
    // Always call the superclass so it can save the view hierarchy state. Note: In order
    // for the Android system to restore the state of the views in your activity,
    // each view must have a unique ID, supplied by the android:id attribute.
    super.onSaveInstanceState(outState)
  }

  companion object {
    const val SELECT_ITEM = "selectItem"
  }
}