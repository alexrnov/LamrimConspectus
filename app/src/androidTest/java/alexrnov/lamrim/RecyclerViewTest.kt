package alexrnov.lamrim

import alexrnov.lamrim.activities.MainActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RecyclerViewTest {

  @get:Rule
  var activityRule: ActivityTestRule<MainActivity>
          = ActivityTestRule(MainActivity::class.java)

  @Test
  public fun f() {
    onView(ViewMatchers.withId(R.id.main_menu))
            .inRoot(RootMatchers.withDecorView(Matchers.`is`(activityRule.activity.window.decorView)))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click()))

  }
}