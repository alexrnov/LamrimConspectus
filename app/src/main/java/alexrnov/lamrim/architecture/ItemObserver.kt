package alexrnov.lamrim.architecture

import alexrnov.lamrim.activities.MainMenuAdapter
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

internal class ItemObserver(
        private val adapter: MainMenuAdapter,
        private val model: PreviewModel,
        private val lifecycle: Lifecycle): LifecycleObserver {

  @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
  fun stop() {
    Log.i("P", "stop() onLifecycleEvent = " + adapter.currentSelectId)
    model.setCurrentItem(adapter.currentSelectId)
  }
}