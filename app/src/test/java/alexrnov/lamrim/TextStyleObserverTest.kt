package alexrnov.lamrim

import android.content.Context
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

internal class TextStyleObserverTest {

  private lateinit var textStyleObserver: TextStyleObserver
  lateinit var lifeCycle: LifecycleRegistry
  val context = mock(Context::class.java)
  val textView = mock(TextView::class.java)


  @Before
  fun setUp() {
    val lifecycleOwner: LifecycleOwner = mock(LifecycleOwner::class.java)
    lifeCycle = LifecycleRegistry(lifecycleOwner)


    textStyleObserver = TextStyleObserver(context, lifeCycle)
    textStyleObserver.addView(textView)

    lifeCycle.addObserver(textStyleObserver)
    lifeCycle.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
  }

  @Test
  fun f() {
    //textStyleObserver.addView(textView)
  }
}