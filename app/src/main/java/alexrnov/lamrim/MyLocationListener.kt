package alexrnov.lamrim

import android.content.Context
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

internal class MyLocationListener(
        private val context: Context,
        private val lifecycle: Lifecycle): LifecycleObserver {
  private var enabled = false

  @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
  fun onCreate() {
    Log.i("P", "lifecycle create")
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_START)
  fun start() {
    if (enabled) {
      Log.i("P", "lifecycle start")
    }
  }

  fun enable() {
    enabled = true
    // check for a specific state
    if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {

    }
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
  fun stop() {
    Log.i("P", "lifecycle stop")
  }

}