package alexrnov.lamrim.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.InputStream;

import alexrnov.lamrim.R;
import alexrnov.lamrim.activities.DetailsActivity;
import alexrnov.lamrim.architecture.PreviewModel;
import alexrnov.lamrim.architecture.TextStyleObserver;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class PreviewFragment extends Fragment {

  private String currentItemID = "";
  private boolean dualPane = false;
  private TextView textView;
  private PreviewModel model;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (getArguments() != null) {

      if (getArguments().containsKey("id")) {
        currentItemID = getArguments().getString("id");
        Log.i("P", "currentItemID = " + currentItemID);
      }

      if (getArguments().containsKey("dualPaneMode")) {
        dualPane = getArguments().getBoolean("dualPaneMode");
        Log.i("P", "dualPane fragment = " + dualPane);
      }
    }

    // here used requireActivity() (activity including fragment) - If one fragment
    // replaces the other one, the UI continues to work without any problems. If
    // instead requireActivity() set 'this' then coroutines in ViewvModel may invoke twice,
    // becouse this ViewModel registry also in MainActivity
    model = new ViewModelProvider(this).get(PreviewModel.class);

    // Create the observer which updates the UI.
    final Observer<String> textObserver = new Observer<String>() {
      @Override
      public void onChanged(@Nullable String newName) {
        textView.setText(newName); // Update UI (TextView)
      }
    };

    model.getPreviewText().observe(this, textObserver);
    InputStream input = getResources().openRawResource(R.raw.text1);
    model.loadText(input);
  }

  // calls when it'currentItemID time for the fragment to draw its layout.
  @Override
  public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // A boolean indicating whether the inflated layout should be attached to the ViewGroup (the second parameter) during inflation.
    View rootView;

    rootView = inflater.inflate(R.layout.descript_text_view, container, false);
    textView = ((TextView) rootView.findViewById(R.id.item_detail));

    Button button = rootView.findViewById(R.id.details_button);
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Context context = getContext();
        if (context != null) {
          Intent intent = new Intent(context, DetailsActivity.class);
          intent.putExtra("id", currentItemID);
          intent.putExtra("dualPaneMode", false);
          context.startActivity(intent);
        }
      }
    });

    // add observer for TextView change style (color and size)
    FragmentActivity activity = this.getActivity();
    if (activity != null) {
      Context context = activity.getApplicationContext();
      TextStyleObserver observer = new TextStyleObserver(context, getLifecycle());
      observer.addView(textView);
      getLifecycle().addObserver(observer);
    }
    return rootView;
  }
}
