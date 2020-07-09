package alexrnov.lamrim;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class FileRepository {

  private static FileRepository instance;

  // mutable because only the repository can change data
  @NonNull
  private MutableLiveData<String> newLiveData = new MutableLiveData<>();

  public static FileRepository getInstance() {
    if (instance == null) {
      synchronized (FileRepository.class) {
        instance = new FileRepository();
      }
    }
    return instance;
  }

  // The getter upcasts to LiveData, this ensures that only the repository can cause a change
  @NonNull
  public LiveData<String> getNewLiveData() {
    return newLiveData;
  }

  public void performJob(InputStream input) {
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
      newLiveData.postValue("Updated time: "+result.toString());
    } catch(IOException e) {
      Log.v("P", "Error readRawFile");
    }
  }
}