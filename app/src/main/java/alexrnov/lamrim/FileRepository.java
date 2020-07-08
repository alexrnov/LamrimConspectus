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

  // Note the use of MutableLiveData, this allows changes to be made
  @NonNull
  private MutableLiveData<String> myLiveData = new MutableLiveData<>();

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
  public LiveData<String> getMyLiveData() {
    return myLiveData;
  }

  // This method runs some work for 3 seconds. It then posts a status update to the live data.
  // This would effectively be the "doInBackground" method from AsyncTask.
  public void doSomeStuff(InputStream input) {

    final String s = "";
    new Thread(() -> {
      int i = 0;
      try {
        Thread.sleep(1);
        i++;
        Log.i("P", "i = " + i);

        //прочитать с помощью BufferedReader
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
          Log.v("P", "raw file = " + result.toString());

          myLiveData.postValue("Updated time: "+result.toString());
        } catch(IOException e) {
          Log.v("P", "Error readRawFile");
        }
      } catch (InterruptedException ignored) {
      }





      //myLiveData.postValue("Updated time: "+System.currentTimeMillis());
    }).start();
  }
}