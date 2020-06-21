package alexrnov.lamrim;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.widget.ImageView;

public class ContentActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_detail);

    CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.toolbar_layout);

    collapsingToolbarLayout.setTitle(this.getString(R.string.app_name));
    // style for title text
    collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ToolbarTitleExpanded);
    collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.ToolbarTitleCollapsed);

    Toolbar toolbar = findViewById(R.id.detail_toolbar);
    setSupportActionBar(toolbar);





    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true); // enable the Up button
      actionBar.setIcon(R.drawable.home_icon);
      actionBar.setTitle(Html.fromHtml("<font color='#fffbbe'>Ламрим</font>"));
    }


    //Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.menu_icon);
    //toolbar.setOverflowIcon(drawable);

    String s = getIntent().getStringExtra("id");
    ImageView image = findViewById(R.id.image_toolbar);


    String name = "app_bar_image" + s;
    int i = getResources().getIdentifier(name, "drawable", getPackageName());
    Log.i("P", "identifier = " + i);
    Log.i("P", "identifiers = " + R.drawable.app_bar_image1);

    //image.setImageResource(R.drawable.app_bar_image);
    image.setImageResource(i);

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

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_about:
        AboutDialogFragment settings = new AboutDialogFragment();
        settings.show(this.getSupportFragmentManager(), "tag");
        Log.i("P", "action1");
        return true;
      case R.id.action_settings:


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new SettingsFragment())
                .commit();

        Log.i("P", "action2");
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_layout, menu);
    return true;
  }
}
