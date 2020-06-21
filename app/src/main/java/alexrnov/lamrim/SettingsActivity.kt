package alexrnov.lamrim

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity: AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.settings_activity)


    supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, ContentFragment())
            .commit()


    /*
    // to manage the fragments in activity, need to use FragmentManager.

    // to manage the fragments in activity, need to use FragmentManager.
    val manager = this.supportFragmentManager
    // to make fragment transactions in activity (such as add, remove, or replace a fragment)
    // to make fragment transactions in activity (such as add, remove, or replace a fragment)
    val transaction = manager.beginTransaction()
    // add a fragment, specifying the fragment to add and the view in which to insert it.
    // add a fragment, specifying the fragment to add and the view in which to insert it.
    transaction.add(R.id.fragment_container, ContentFragment())
    transaction.commit() // call commit() for the changes to take effect.

*/
  }


}