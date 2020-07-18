package alexrnov.lamrim;

import android.view.View;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TestUtils {

  /** allow load text from file */
  public static String loadTextFromFile(InputStream input) {
    BufferedReader bf;
    StringBuilder result = new StringBuilder();
    try {
      bf = new BufferedReader(new InputStreamReader(input));
      String line = bf.readLine();
      while (line != null) {
        result.append(line);
        result.append(System.getProperty("line.separator"));
        line = bf.readLine();
      }
    } catch(IOException e) { e.printStackTrace(); }
    return result.toString();
  }

  /** Allows comparison by text length */
  public static TypeSafeMatcher<View> isLength(final int lines) {
    return new TypeSafeMatcher<View>() {
      @Override
      protected boolean matchesSafely(View item) {
        return ((TextView) item).getText().length() == lines;
      }

      @Override
      public void describeTo(Description description) {
        description.appendText("isTextInLines");
      }
    };
  }

}
