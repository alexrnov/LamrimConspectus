package alexrnov.lamrim

import alexrnov.lamrim.TestUtils.isLength
import alexrnov.lamrim.fragments.PreviewFragment
import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*

/**
 * To help set up the conditions for performing these tests, AndroidX provides
 * a library, FragmentScenario, to create fragments and change their state.
 */
@RunWith(AndroidJUnit4::class)
class PreviewFragmentTest {

  private var previewText = ""

  @Test
  fun loadFirstPreviewFragment() {
    val fragmentArgs = Bundle().apply { putString("id", "0") }

    val scenario = launchFragmentInContainer<PreviewFragment>(fragmentArgs, R.id.fragment_preview)
    scenario.moveToState(Lifecycle.State.RESUMED) // set state
    onView(withId(R.id.preview_text)).check(matches(isDisplayed()))

    scenario.onFragment { fragment ->
      val input = fragment.activity?.resources?.openRawResource(R.raw.preview_text0)
      previewText = TestUtils.loadTextFromFile(input)
    }

    onView(withId(R.id.preview_text)).check(matches(isDisplayed()))
    onView(withId(R.id.preview_text)).check(matches(isLength(previewText.length)))
    onView(withId(R.id.preview_text)).check(matches(withText(previewText)))
    onView(withId(R.id.details_button)).check(matches(isDisplayed()))
    onView(withId(R.id.details_button)).check(matches(withText(R.string.details_button_text)))
  }

  @Test
  fun loadLastPreviewFragment() {
    val fragmentArgs = Bundle().apply { putString("id", "18") }

    val scenario = launchFragmentInContainer<PreviewFragment>(fragmentArgs, R.id.fragment_preview)
    scenario.moveToState(Lifecycle.State.RESUMED) // set state
    onView(withId(R.id.preview_text)).check(matches(isDisplayed()))

    scenario.onFragment { fragment ->
      val input = fragment.activity?.resources?.openRawResource(R.raw.preview_text18)
      previewText = TestUtils.loadTextFromFile(input)
    }

    onView(withId(R.id.preview_text)).check(matches(isDisplayed()))
    onView(withId(R.id.preview_text)).check(matches(isLength(previewText.length)))
    onView(withId(R.id.preview_text)).check(matches(withText(previewText)))
    onView(withId(R.id.details_button)).check(matches(isDisplayed()))
    onView(withId(R.id.details_button)).check(matches(withText(R.string.details_button_text)))
  }
}