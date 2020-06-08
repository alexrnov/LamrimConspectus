package alexrnov.lamrim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

  private RecyclerView recyclerView;
  private RecyclerView.Adapter adapter;
  private RecyclerView.LayoutManager layoutManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Class1 class1 = new Class1();
    class1.f();
    Log.i("P", "77777");

    recyclerView = (RecyclerView) findViewById(R.id.main_menu);
    // use this setting to improve performance if you know that changes
    // in content do not change the layout size of the RecyclerView
    recyclerView.setHasFixedSize(true);
    // LinearLayoutManager arranges the items in a one-dimensional list
    layoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(layoutManager);

    String[] dataset = new String[] {"item1", "item2", "item4", "item5",
            "item6", "item7", "item8", "item9", "item10", "item11", "item12", "item13"};
    // specify an adapter
    adapter = new MainMenuAdapter(dataset);
    recyclerView.setAdapter(adapter);

  }
}
