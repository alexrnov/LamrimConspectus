package alexrnov.lamrim

import android.content.Context
import android.content.SharedPreferences
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TextStyleObserverTest {

  private lateinit var textStyleObserver: TextStyleObserver
  private lateinit var lifeCycle: LifecycleRegistry

  @Mock
  private lateinit var context: Context

  @Mock
  private lateinit var textView: TextView

  @Before
  fun setUp() {

    val lifecycleOwner: LifecycleOwner = mock(LifecycleOwner::class.java)
    lifeCycle = LifecycleRegistry(lifecycleOwner)

    val sharedPref = mock(SharedPreferences::class.java)
    //`when`(sharedPref.getString("font_size", "20")).thenReturn("27")
    //`when`(sharedPref.getString("font_color", "#000000")).thenReturn("#1a3677")
    `when`(context.getSharedPreferences(anyString(), anyInt())).thenReturn(sharedPref)

    textStyleObserver = TextStyleObserver(context, lifeCycle)

    lifeCycle.addObserver(textStyleObserver)
    lifeCycle.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)

    textStyleObserver.addView(textView)
  }

  @Test
  fun check_resume() {
    // an error occurs because there is a static method Color.parseColor()
    // lifeCycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
  }

}
