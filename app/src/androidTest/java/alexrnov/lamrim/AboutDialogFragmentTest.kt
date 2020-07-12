package alexrnov.lamrim

import alexrnov.lamrim.fragments.AboutDialogFragment
import androidx.fragment.app.testing.launchFragment
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import com.google.common.truth.Truth.assertThat

/**
 * To help set up the conditions for performing these tests, AndroidX provides
 * a library, FragmentScenario, to create fragments and change their state.
 */
@RunWith(AndroidJUnit4::class)
class AboutDialogFragmentTest {

  @Test
  fun showDialog() {
    val scenario = launchFragment<AboutDialogFragment>()
    scenario.onFragment { fragment ->
      assertThat(fragment.dialog).isNotNull()
      assertThat(fragment.requireDialog().isShowing).isTrue()
      fragment.dismiss()
      // is deprecated
      //fragment.requireFragmentManager().executePendingTransactions()
      fragment.parentFragmentManager.executePendingTransactions()
      assertThat(fragment.dialog).isNull()
    }

    // Assumes that the dialog had a button
    // containing the text "Cancel".
    // onView(withText("Cancel")).check(doesNotExist())
  }
}