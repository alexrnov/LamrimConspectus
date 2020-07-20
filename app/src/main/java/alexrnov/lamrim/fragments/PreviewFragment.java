package alexrnov.lamrim.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.util.Objects;

import alexrnov.lamrim.R;
import alexrnov.lamrim.activities.DetailsActivity;
import alexrnov.lamrim.architecture.PreviewModel;
import alexrnov.lamrim.architecture.TextStyleObserver;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class PreviewFragment extends Fragment {

  private TextView textView;
  private PreviewModel model;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // here used requireActivity() (activity including fragment) - If one fragment
    // replaces the other one, the UI continues to work without any problems. If
    // instead requireActivity() set 'this' then coroutines in ViewModel may invoke twice,
    // because this ViewModel registry also in MainActivity
    model = new ViewModelProvider(this).get(PreviewModel.class);

    if (getArguments() != null && getArguments().containsKey("id")) {
      String currentItemID = getArguments().getString("id");
      model.setCurrentItem(currentItemID != null ? currentItemID : "0");
    }

    // Create the observer which updates the UI.
    final Observer<String> textObserver = text -> {
      textView.setText(text); // Update UI (TextView)
    };

    model.getPreviewText().observe(this, textObserver);

    String s = model.getPreviewFileName() + getItemID();

    String packageName = Objects.requireNonNull(getActivity()).getPackageName();
    int resID = getResources().getIdentifier(s, "raw", packageName);

    InputStream input = getResources().openRawResource(resID);
    model.loadText(input);
  }

  // calls when it'currentItemID time for the fragment to draw its layout.
  @Override
  public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // A boolean indicating whether the inflated layout should be attached to the ViewGroup (the second parameter) during inflation.
    View rootView;

    rootView = inflater.inflate(R.layout.preview_layout, container, false);
    textView = rootView.findViewById(R.id.preview_text);

    Button button = rootView.findViewById(R.id.details_button);
    button.setOnClickListener(view -> {
      Context context = getContext();
      if (context != null) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra("id", getItemID());
        intent.putExtra("dualPaneMode", false);
        context.startActivity(intent);
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

  /* check for started not selected value (non selected value - that not
  selected recycler view item in portrait landscape)*/
  private String getItemID() {
    String item = model.getCurrentItem();
    // if it first time when select first item for landscape orientation
    return (item.equals("-1")) ? "0" : item;
  }
}
