package alexrnov.lamrim

import alexrnov.lamrim.TestUtils.isFontSize
import alexrnov.lamrim.activities.MainActivity
import android.content.pm.ActivityInfo
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest // Marks a test that should run as part of the large tests.
class SettingsOtherTest {

  @get:Rule
  var activityRule: ActivityTestRule<MainActivity>
          = ActivityTestRule(MainActivity::class.java)

  @Test
  fun changeFontSize() {
    activityRule.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    val density = activityRule.activity.resources.displayMetrics.scaledDensity

    onView(withId(R.id.action_settings)).perform(click())
    onView(withText(R.string.font_size)).perform(click())
    onView(withText(R.string.very_min_size)).perform(click())
    // press up home button
    onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click())
    onView(withId(R.id.preview_text)).check(matches(isDisplayed()))
    onView(withId(R.id.preview_text)).check(matches(isFontSize(14.0f, density)))

    onView(withId(R.id.action_settings)).perform(click())
    onView(withText(R.string.font_size)).perform(click())
    onView(withText(R.string.min_size)).perform(click())
    onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click())
    onView(withId(R.id.preview_text)).check(matches(isFontSize(17.0f, density)))

    onView(withId(R.id.action_settings)).perform(click())
    onView(withText(R.string.font_size)).perform(click())
    onView(withText(R.string.middle_size)).perform(click())
    onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click())
    onView(withId(R.id.preview_text)).check(matches(isFontSize(20.0f, density)))

    onView(withId(R.id.action_settings)).perform(click())
    onView(withText(R.string.font_size)).perform(click())
    onView(withText(R.string.big_size)).perform(click())
    onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click())
    onView(withId(R.id.preview_text)).check(matches(isFontSize(23.0f, density)))

    onView(withId(R.id.action_settings)).perform(click())
    onView(withText(R.string.font_size)).perform(click())
    onView(withText(R.string.very_big_size)).perform(click())
    onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click())
    onView(withId(R.id.preview_text)).check(matches(isFontSize(26.0f, density)))
  }

  /*
  @Test
  fun changeFontColor() {
    activityRule.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    onView(withId(R.id.action_settings)).perform(click())
    onView(withText(R.string.font_color)).perform(click())
    onView(withText(R.string.gray_color)).perform(click())
    // press up home button
    onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click())
  }

   */
}