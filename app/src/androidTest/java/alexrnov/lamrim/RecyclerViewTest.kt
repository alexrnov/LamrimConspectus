package alexrnov.lamrim

import alexrnov.lamrim.TestUtils.loadTextFromFile
import alexrnov.lamrim.activities.MainActivity
import android.content.pm.ActivityInfo
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
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
    onView(withId(R.id.preview_text)).check(matches(TextMatches.isLength(previewText.length)))

    input = activityRule.activity.resources.openRawResource(R.raw.preview_text1)
    previewText = loadTextFromFile(input)
    onView(withId(R.id.main_menu))
            .inRoot(RootMatchers.withDecorView(Matchers.`is`(activityRule.activity.window.decorView)))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
    onView(withId(R.id.preview_text)).check(matches(isDisplayed()))
    onView(withId(R.id.preview_text)).check(matches(TextMatches.isLength(previewText.length)))

    input = activityRule.activity.resources.openRawResource(R.raw.preview_text2)
    previewText = loadTextFromFile(input)
    onView(withId(R.id.main_menu))
            .inRoot(RootMatchers.withDecorView(Matchers.`is`(activityRule.activity.window.decorView)))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))
    onView(withId(R.id.preview_text)).check(matches(isDisplayed()))
    onView(withId(R.id.preview_text)).check(matches(TextMatches.isLength(previewText.length)))
  }

  fun clickItemWhenPortraitOrientation() {
    // set landscape orientation for test
    activityRule.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    val input = activityRule.activity.resources.openRawResource(R.raw.details_text0)
    val detailsText = loadTextFromFile(input)

    //onView(withText(R.id.preview_text)).check(doesNotExist())
  }

  @Test
  fun clickEndItem() {
    val recyclerView = activityRule.activity.findViewById<RecyclerView>(R.id.main_menu)
    val itemCount = recyclerView.adapter?.itemCount

    val lastItem = itemCount?.minus(1)?: 0
    // scroll to end of position
    onView(withId(R.id.main_menu)).inRoot(RootMatchers.withDecorView(
                    Matchers.`is`(activityRule.activity.window.decorView)))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(lastItem))

    onView(withId(R.id.main_menu))
            .inRoot(RootMatchers.withDecorView(Matchers.`is`(activityRule.activity.window.decorView)))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(lastItem, click()))
  }
}