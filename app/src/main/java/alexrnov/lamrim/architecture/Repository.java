package alexrnov.lamrim.architecture;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class Repository {

  private static Repository instance;

  // mutable because only the repository can change data
  @NonNull
  private MutableLiveData<String> previewText = new MutableLiveData<>();

  public static Repository getInstance() {
    if (instance == null) {
      synchronized (Repository.class) {
        instance = new Repository();
      }
    }
    return instance;
  }

  // The getter upcasts to LiveData, this ensures that only the repository can cause a change
  @NonNull
  public LiveData<String> getPreviewText() {
    return previewText;
  }

  public void loadTextPromFile(InputStream input) {
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
      previewText.postValue("Updated time: "+result.toString());
    } catch(IOException e) {
      Log.v("P", "Error readRawFile");
    }
  }
}