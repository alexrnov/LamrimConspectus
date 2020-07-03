package alexrnov.lamrim

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.preference.PreferenceManager

internal class MyLocationListener(
        private val context: Context,
        private val lifecycle: Lifecycle,
        private val textView: TextView): LifecycleObserver {

  private lateinit var sharedPref: SharedPreferences


  @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
  fun onCreate() {
    sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
  }


  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun resume() {
    Log.i("P", "lifecycle resume()")
    // check for a specific state. Since multiple states can interleave for a given point
    // of time, if we want to check for a specific state, we always use the isAtLeast method:
    if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
      Log.i("P", "lifecycle is started")
    }

    val size = sharedPref.getString("font_size", "20")
    val color = sharedPref.getString("font_color", "#000000");
    textView.textSize = size!!.toFloat()
    textView.setTextColor(Color.parseColor(color))
  }
}