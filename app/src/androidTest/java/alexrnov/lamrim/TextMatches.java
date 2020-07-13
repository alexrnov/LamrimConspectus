package alexrnov.lamrim;

import android.view.View;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class TextMatches {

  public static TypeSafeMatcher<View> isLength(final int lines) {
    return new TypeSafeMatcher<View>() {
      @Override
      protected boolean matchesSafely(View item) {
        System.out.println("item size = " + ((TextView) item).getText().length());
        return ((TextView) item).getText().length() == lines;
      }

      @Override
      public void describeTo(Description description) {
        description.appendText("isTextInLines");
      }
    };
  }




}
