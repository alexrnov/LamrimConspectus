package alexrnov.lamrim;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import androidx.lifecycle.MutableLiveData;

public class TestUtils {

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
    } catch(IOException e) {
      Log.v("P", "Error readRawFile");
    }
    return result.toString();
  }

}
