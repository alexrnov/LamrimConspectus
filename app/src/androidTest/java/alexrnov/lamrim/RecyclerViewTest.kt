package alexrnov.lamrim

import alexrnov.lamrim.TestUtils.load
import alexrnov.lamrim.activities.MainActivity
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
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
  fun clickFirstItem() {
    onView(withId(R.id.main_menu))
            .inRoot(RootMatchers.withDecorView(Matchers.`is`(activityRule.activity.window.decorView)))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))

    val input = activityRule.activity.resources.openRawResource(R.raw.preview_text0)
    val s = load(input)

    onView(withId(R.id.preview_text)).check(matches(isDisplayed()))
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