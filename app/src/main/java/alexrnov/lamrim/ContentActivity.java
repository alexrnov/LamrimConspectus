package alexrnov.lamrim;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ContentActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);

    CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.toolbar_layout);

    collapsingToolbarLayout.setTitle("Конспект ламрима");
    Toolbar toolbar = findViewById(R.id.detail_toolbar);
    setSupportActionBar(toolbar);

    (getSupportActionBar()).setDisplayHomeAsUpEnabled(true); // enable the Up button
    getSupportActionBar().setIcon(R.mipmap.ic_launcher_round);

    // savedInstanceState is non-null when there is fragment state saved from previous
    // configurations of this activity (e.g. when rotating the screen from portrait to landscape).
    if (savedInstanceState == null) {

      Bundle arguments = new Bundle();
      arguments.putString("id", getIntent().getStringExtra("id"));
      ContentFragment fragment = new ContentFragment();
      fragment.setArguments(arguments);

      // to manage the fragments in activity, need to use FragmentManager.
      FragmentManager manager = this.getSupportFragmentManager();
      // to make fragment transactions in activity (such as add, remove, or replace a fragment)
      FragmentTransaction transaction = manager.beginTransaction();
      // add a fragment, specifying the fragment to add and the view in which to insert it.
      transaction.add(R.id.fragment_container, fragment);
      transaction.commit(); // call commit() for the changes to take effect.
    }
  }


}
