package alexrnov.lamrim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

  private RecyclerView recyclerView;
  private RecyclerView.Adapter adapter;
  private RecyclerView.LayoutManager layoutManager;

  private boolean dualPane;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    View container = findViewById(R.id.fragment_container);
    dualPane = container != null && container.getVisibility() == View.VISIBLE;

    Log.i("P", "dualPane = " + dualPane);
    recyclerView = (RecyclerView) findViewById(R.id.main_menu);
    // use this setting to improve performance if you know that changes
    // in content do not change the layout size of the RecyclerView
    recyclerView.setHasFixedSize(true);
    // LinearLayoutManager arranges the items in a one-dimensional list
    layoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(layoutManager);


    adapter = new MainMenuAdapter(dualPane, this);

    recyclerView.setAdapter(adapter);
  }

  @Override
  public void onConfigurationChanged(@NonNull Configuration config) {
    super.onConfigurationChanged(config);

    // Checks the orientation of the screen
    if (config.orientation == Configuration.ORIENTATION_PORTRAIT && dualPane) {
      Context context = this.getApplicationContext();
      Intent intent = new Intent(context, ContentActivity.class);
      context.startActivity(intent);
    }
  }
}
