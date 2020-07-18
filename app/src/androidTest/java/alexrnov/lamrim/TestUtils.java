package alexrnov.lamrim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
    } catch(IOException e) { e.printStackTrace(); }
    return result.toString();
  }
}
