package alexrnov.lamrim;

import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class ViewSPMatcher extends TypeSafeMatcher<View> {

  public ViewSPMatcher() {

  }

  @Override
  protected boolean matchesSafely(View item) {
    return false;
  }

  @Override
  public void describeTo(Description description) {

  }
}
