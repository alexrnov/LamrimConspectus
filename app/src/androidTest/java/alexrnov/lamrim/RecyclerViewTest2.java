package alexrnov.lamrim;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;

import alexrnov.lamrim.activities.MainActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class RecyclerViewTest2 {

  @Rule
  public ActivityTestRule<MainActivity> activityRule =
          new ActivityTestRule(MainActivity.class);

  @Test
  public void f() {
    onView(withId(R.id.main_menu))
            .inRoot(RootMatchers.withDecorView(Matchers.is(activityRule.getActivity().getWindow().getDecorView())))
            .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

    RecyclerView recyclerView = activityRule.getActivity().findViewById(R.id.main_menu);

    onView(withId(R.id.main_menu)).inRoot(RootMatchers.withDecorView(
            Matchers.is(activityRule.getActivity().getWindow().getDecorView())))
            .perform(RecyclerViewActions.scrollToPosition(4));
  }
}
