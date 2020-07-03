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
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TextStyleObserverTest {

  private lateinit var textStyleObserver: TextStyleObserver
  private lateinit var lifeCycle: LifecycleRegistry

  @Mock
  private lateinit var context: Context

  private val textView = mock(TextView::class.java)


  @Before
  fun setUp() {

    val lifecycleOwner: LifecycleOwner = mock(LifecycleOwner::class.java)
    lifeCycle = LifecycleRegistry(lifecycleOwner)

    val m = mock(SharedPreferences::class.java)
    `when`(context.getSharedPreferences(anyString(), anyInt())).thenReturn(m)

    textStyleObserver = TextStyleObserver(context, lifeCycle)
    textStyleObserver.addView(textView)

    lifeCycle.addObserver(textStyleObserver)
    lifeCycle.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
  }


  @Test
  fun f() {

  }

}