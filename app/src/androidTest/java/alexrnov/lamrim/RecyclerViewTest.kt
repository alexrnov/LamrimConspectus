package alexrnov.lamrim

import alexrnov.lamrim.TestUtils.isLength
import alexrnov.lamrim.TestUtils.loadTextFromFile
import alexrnov.lamrim.activities.MainActivity
import android.content.pm.ActivityInfo
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest // Marks a test that should run as part of the medium tests.
class RecyclerViewTest {

  @get:Rule
  var activityRule: ActivityTestRule<MainActivity>
          = ActivityTestRule(MainActivity::class.java)

  @Test
  fun clickItemsWhenLandscapeOrientation() {
    // set landscape orientation for test
    activityRule.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

    // At start, the zero index item is set (first item)
    var input = activityRule.activity.resources.openRawResource(R.raw.preview_text0)
    var previewText = loadTextFromFile(input)
    onView(withId(R.id.preview_text)).check(matches(isDisplayed()))
    onView(withId(R.id.preview_text)).check(matches(isLength(previewText.length)))

    input = activityRule.activity.resources.openRawResource(R.raw.preview_text1)
    previewText = loadTextFromFile(input)
    onView(withId(R.id.main_menu))
            .inRoot(RootMatchers.withDecorView(Matchers.`is`(activityRule.activity.window.decorView)))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))

    onView(withId(R.id.preview_text)).check(matches(isDisplayed()))
    onView(withId(R.id.preview_text)).check(matches(isLength(previewText.length)))
    onView(withId(R.id.details_button)).check(matches(isDisplayed()))
    onView(withId(R.id.details_button)).check(matches(withText(R.string.details_button_text)))

    input = activityRule.activity.resources.openRawResource(R.raw.preview_text2)
    previewText = loadTextFromFile(input)
    onView(withId(R.id.main_menu))
            .inRoot(RootMatchers.withDecorView(Matchers.`is`(activityRule.activity.window.decorView)))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))

    onView(withId(R.id.preview_text)).check(matches(isDisplayed()))
    onView(withId(R.id.preview_text)).check(matches(isLength(previewText.length)))
    onView(withId(R.id.details_button)).check(matches(isDisplayed()))
    onView(withId(R.id.details_button)).check(matches(withText(R.string.details_button_text)))
  }

  @Test
  fun clickItemWhenPortraitOrientation() {
    // set portrait orientation for test
    activityRule.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    onView(withText(R.id.preview_text)).check(doesNotExist())

    onView(withId(R.id.main_menu))
            .inRoot(RootMatchers.withDecorView(Matchers.`is`(activityRule.activity.window.decorView)))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))

    val input = activityRule.activity.resources.openRawResource(R.raw.details_text1)
    val detailsText = loadTextFromFile(input)

    onView(withText(R.id.frame_main_menu)).check(doesNotExist())
    onView(withId(R.id.fragment_details)).check(matches(isDisplayed()))
    onView(withId(R.id.details_text)).check(matches(isDisplayed()))
    onView(withId(R.id.details_text)).check(matches(isLength(detailsText.length)))
  }

  @Test
  fun clickEndItemLandscapeOrientation() {
    // set landscape orientation for test
    activityRule.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

    val recyclerView = activityRule.activity.findViewById<RecyclerView>(R.id.main_menu)
    val itemCount = recyclerView.adapter?.itemCount

    val lastItem = itemCount?.minus(1)?: 0
    // scroll to end of position
    onView(withId(R.id.main_menu)).inRoot(RootMatchers.withDecorView(
                    Matchers.`is`(activityRule.activity.window.decorView)))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(lastItem))

    val input = activityRule.activity.resources.openRawResource(R.raw.preview_text18)
    val previewText = loadTextFromFile(input)

    // click last element
    onView(withId(R.id.main_menu))
            .inRoot(RootMatchers.withDecorView(Matchers.`is`(activityRule.activity.window.decorView)))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(lastItem, click()))

    onView(withId(R.id.preview_text)).check(matches(isDisplayed()))
    onView(withId(R.id.preview_text)).check(matches(isLength(previewText.length)))
    onView(withId(R.id.details_button)).check(matches(isDisplayed()))
    onView(withId(R.id.details_button)).check(matches(withText(R.string.details_button_text)))
  }

  @Test
  fun clickEndItemPortraitOrientation() {
    // set portrait orientation for test
    activityRule.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    val recyclerView = activityRule.activity.findViewById<RecyclerView>(R.id.main_menu)
    val itemCount = recyclerView.adapter?.itemCount

    val lastItem = itemCount?.minus(1)?: 0
    // scroll to end of position
    onView(withId(R.id.main_menu)).inRoot(RootMatchers.withDecorView(
                    Matchers.`is`(activityRule.activity.window.decorView)))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(lastItem))

    val input = activityRule.activity.resources.openRawResource(R.raw.details_text18)
    val detailsText = loadTextFromFile(input)

    // click last element
    onView(withId(R.id.main_menu))
            .inRoot(RootMatchers.withDecorView(Matchers.`is`(activityRule.activity.window.decorView)))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(lastItem, click()))

    onView(withText(R.id.frame_main_menu)).check(doesNotExist())
    onView(withId(R.id.fragment_details)).check(matches(isDisplayed()))
    onView(withId(R.id.details_text)).check(matches(isDisplayed()))
    onView(withId(R.id.details_text)).check(matches(isLength(detailsText.length)))
  }

}