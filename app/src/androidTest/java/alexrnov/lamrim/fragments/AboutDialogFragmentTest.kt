package alexrnov.lamrim.fragments

import alexrnov.lamrim.R
import alexrnov.lamrim.testutils.Utils.isLength
import alexrnov.lamrim.activities.MainActivity
import androidx.fragment.app.testing.launchFragment
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import com.google.common.truth.Truth.assertThat
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import org.junit.Before
import org.junit.Rule

/**
 * FragmentScenario also supports testing of dialogs. Even though dialogs are instances
 * of graphical fragments, you use the launchFragment() method so that the dialog's elements
 * are populated in the dialog itself, rather than in the activity that launches the dialog.
 */
@RunWith(AndroidJUnit4::class)
class AboutDialogFragmentTest {

  private lateinit var buttonLabel: String
  private lateinit var aboutText: String

  @get:Rule
  var activityRule: ActivityTestRule<MainActivity>
          = ActivityTestRule(MainActivity::class.java)

  @Before
  fun initString() {
    buttonLabel = activityRule.activity.getString(R.string.ok_button)
    aboutText = activityRule.activity.getString(R.string.about_program_text)
  }

  @Test
  fun showDialog() {
    val scenario = launchFragment<AboutDialogFragment>()
    scenario.onFragment { fragment ->
      assertThat(fragment.dialog).isNotNull()
      assertThat(fragment.requireDialog().isShowing).isTrue()
    }
    onView(withId(R.id.about_app_text)).check(matches(isDisplayed()))
    onView(withId(R.id.about_app_text)).check(matches(isLength(aboutText.length)))
    onView(withId(R.id.close_dialog_button)).check(matches(isDisplayed()))
    onView(withId(R.id.close_dialog_button)).check(matches(withText(buttonLabel)))

    scenario.onFragment { fragment ->
      fragment.dismiss()
      // is deprecated
      //fragment.requireFragmentManager().executePendingTransactions()
      fragment.parentFragmentManager.executePendingTransactions()
      assertThat(fragment.dialog).isNull()
    }
    onView(withText(buttonLabel)).check(doesNotExist())
  }
}