package alexrnov.lamrim.activities;

import android.os.AsyncTask;
import android.view.View;

import java.lang.ref.WeakReference;

import alexrnov.lamrim.R;
import androidx.appcompat.app.AppCompatActivity;

public class AsyncClass extends AsyncTask<Void, Void, Void> {

  private WeakReference<AppCompatActivity> mainActivity;
  private WeakReference<View> view;

  public AsyncClass(AppCompatActivity mainActivity, View view) {
    this.mainActivity = new WeakReference<>(mainActivity);
    this.view = new WeakReference<>(view);
  }

  // invoked on the UI thread before the task is executed.
  @Override
  protected void onPreExecute() {
    view.get().setBackgroundResource(R.drawable.item_check);
  }

  // invoked on the background thread immediately after onPreExecute()
  @Override
  protected Void doInBackground(Void... voids) {
    try {
      Thread.sleep(500L);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    mainActivity.get().runOnUiThread(() -> {
      view.get().setBackgroundResource(R.drawable.item_default);
    });
    return null;
  }

  //invoked on the UI thread after the background computation finishes.
  @Override
  protected void onPostExecute(Void result) {
    view.get().setBackgroundResource(R.drawable.item_default);
  }
}
