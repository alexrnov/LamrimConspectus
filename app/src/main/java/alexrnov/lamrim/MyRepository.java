package alexrnov.lamrim;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MyRepository {

  private static MyRepository instance;

  // Note the use of MutableLiveData, this allows changes to be made
  @NonNull
  private MutableLiveData<String> myLiveData = new MutableLiveData<>();

  public static MyRepository getInstance() {
    if (instance == null) {
      synchronized (MyRepository.class) {
        if (instance == null) {
          instance = new MyRepository();
        }
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
  public void doSomeStuff() {
    new Thread(() -> {
      try {
        Thread.sleep(10000);
      } catch (InterruptedException ignored) {
      }

      myLiveData.postValue("Updated time: "+System.currentTimeMillis());
    }).start();
  }
}