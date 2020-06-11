package alexrnov.lamrim;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ContentActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);

    // savedInstanceState is non-null when there is fragment state saved from previous
    // configurations of this activity (e.g. when rotating the screen from portrait to landscape).
    if (savedInstanceState == null) {
      // to manage the fragments in activity, need to use FragmentManager.
      FragmentManager manager = this.getSupportFragmentManager();
      // to make fragment transactions in activity (such as add, remove, or replace a fragment)
      FragmentTransaction transaction = manager.beginTransaction();
      // add a fragment, specifying the fragment to add and the view in which to insert it.
      transaction.add(R.id.fragment_container, new ContentFragment());
      transaction.commit(); // call commit() for the changes to take effect.
    }
  }


}
