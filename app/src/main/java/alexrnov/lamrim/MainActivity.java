package alexrnov.lamrim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

  private RecyclerView recyclerView;
  private MainMenuAdapter adapter;
  private RecyclerView.LayoutManager layoutManager;

  private boolean dualPane;
  private String TAG = "P";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ApplicationData model = new ViewModelProvider(this).get(ApplicationData.class);

    View container = findViewById(R.id.fragment_container);
    dualPane = container != null && container.getVisibility() == View.VISIBLE;

    Log.i(TAG, "dualPane = " + dualPane);
    recyclerView = (RecyclerView) findViewById(R.id.main_menu);
    // use this setting to improve performance if you know that changes
    // in content do not change the layout size of the RecyclerView
    recyclerView.setHasFixedSize(true);
    // LinearLayoutManager arranges the items in a one-dimensional list
    layoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(layoutManager);


    adapter = new MainMenuAdapter(dualPane, this);

    recyclerView.setAdapter(adapter);


    //Toolbar collapsingToolbarLayout = findViewById(R.id.toolbar);

    //collapsingToolbarLayout.setTitle("Конспект ламрима");
    Toolbar toolbar = findViewById(R.id.main_toolbar);
    setSupportActionBar(toolbar);

    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setIcon(R.drawable.home_icon);
    }

    //Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.menu_icon);
    //toolbar.setOverflowIcon(drawable);

  }

  /*
  @Override
  public void onConfigurationChanged(@NonNull Configuration config) {
    super.onConfigurationChanged(config);

    // Checks the orientation of the screen
    if (config.orientation == Configuration.ORIENTATION_PORTRAIT && dualPane) {
      Context context = this.getApplicationContext();
      Intent intent = new Intent(context, ContentActivity.class);
      intent.putExtra("id", adapter.getCurrentSelectId());
      context.startActivity(intent);
    }
  }
  */

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_layout, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_about:
        SettingsDialogFragment settings = new SettingsDialogFragment();
        settings.show(this.getSupportFragmentManager(), "tag");
        Log.i("P", "action1");
        return true;
      case R.id.action_settings:
        Log.i("P", "action2");
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }


}
