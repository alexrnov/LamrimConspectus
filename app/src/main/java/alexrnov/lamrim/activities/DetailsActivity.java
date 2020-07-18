package alexrnov.lamrim.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;

import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import alexrnov.lamrim.fragments.AboutDialogFragment;
import alexrnov.lamrim.fragments.DetailsFragment;
import alexrnov.lamrim.R;
import alexrnov.lamrim.settings.SettingsActivity;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.widget.ImageView;

public class DetailsActivity extends AppCompatActivity {

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

      /*
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        // FROM_HTML_MODE_LEGACY is the behaviour that was used for versions
        // below android N we are using this flag to give a consistent behaviour
        actionBar.setTitle(Html.fromHtml("<font color='#fffbbe'>" +
          this.getString(R.string.app_name) + "</font>", Html.FROM_HTML_MODE_LEGACY));
      } else {
        actionBar.setTitle(Html.fromHtml("<font color='#fffbbe'>" +
          this.getString(R.string.app_name) + "</font>"));
      }
      */
      //actionBar.setTitle(Html.fromHtml("<font color='#fffbbe'>Ламрим</font>"));
    }

    String s = getIntent().getStringExtra("id");
    ImageView image = findViewById(R.id.image_toolbar);

    //private TextViewModel model;
    String name = "app_bar_image" + s;
    int i = getResources().getIdentifier(name, "drawable", getPackageName());
    image.setImageResource(i);

    // savedInstanceState is non-null when there is fragment state saved from previous
    // configurations of this activity (e.g. when rotating the screen from portrait to landscape).
    if (savedInstanceState == null) {

      Bundle arguments = new Bundle();
      arguments.putString("id", getIntent().getStringExtra("id"));

      DetailsFragment fragment = new DetailsFragment();
      fragment.setArguments(arguments);

      // to manage the fragments in activity, need to use FragmentManager.
      FragmentManager manager = this.getSupportFragmentManager();
      // to make fragment transactions in activity (such as add, remove, or replace a fragment)
      FragmentTransaction transaction = manager.beginTransaction();
      // add a fragment, specifying the fragment to add and the view in which to insert it.
      transaction.add(R.id.fragment_details, fragment);
      transaction.commit(); // call commit() for the changes to take effect.
    }
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed(); // In order for the home button press to return to the previous activity
        return true;
      case R.id.action_about:
        AboutDialogFragment settings = new AboutDialogFragment();
        settings.show(this.getSupportFragmentManager(), "tag");
        return true;
      case R.id.action_settings:
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
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