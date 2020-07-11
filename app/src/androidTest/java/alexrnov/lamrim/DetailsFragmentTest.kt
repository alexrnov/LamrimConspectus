package alexrnov.lamrim

import alexrnov.lamrim.architecture.Repository
import alexrnov.lamrim.fragments.DetailsFragment
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.google.common.truth.Truth.assertThat

@RunWith(AndroidJUnit4::class)
class DetailsFragmentTest {

  @Test
  fun f() {
    val fragmentArgs = Bundle().apply {
      putString("id", "1")
    }

    val scenario = launchFragmentInContainer<DetailsFragment>(
              fragmentArgs, R.id.fragment_details)

    scenario.moveToState(Lifecycle.State.RESUMED)
    onView(withId(R.id.details_text)).check(matches(isDisplayed()))
    //onView(withId(R.id.details_text)).check(matches(TextMatchers.isLength(2860)))
    //onView(withId(R.id.details_text)).check(matches(withText("")))
  }

}