package alexrnov.lamrim

import alexrnov.lamrim.fragments.DetailsFragment
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
class DetailsFragmentTest {

  @Test
  fun loadOneItem() {
    val fragmentArgs = Bundle().apply {
      putString("id", "1")
    }

    val scenario = launchFragmentInContainer<DetailsFragment>(
            fragmentArgs, R.id.fragment_details)

    scenario.moveToState(Lifecycle.State.RESUMED) // set state
    onView(withId(R.id.details_text)).check(matches(isDisplayed()))

    var previewText = ""
    scenario.onFragment { fragment ->
      val input = fragment.activity?.resources?.openRawResource(R.raw.details_text0)
      previewText = TestUtils.loadTextFromFile(input)
    }
    onView(withId(R.id.details_text)).check(matches(isDisplayed()))
    onView(withId(R.id.details_text)).check(matches(TextMatches.isLength(previewText.length)))
    onView(withId(R.id.details_text)).check(matches(withText(previewText)))
  }

}