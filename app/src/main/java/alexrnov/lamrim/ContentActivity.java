package alexrnov.lamrim;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ContentActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);

    if (savedInstanceState == null) {
      ContentFragment fragment = new ContentFragment();

      getSupportFragmentManager().beginTransaction()
              .add(R.id.fragment_container, fragment)
              .commit();


    }
  }


}
