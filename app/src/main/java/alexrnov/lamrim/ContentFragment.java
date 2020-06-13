package alexrnov.lamrim;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

import androidx.fragment.app.Fragment;

public class ContentFragment extends Fragment {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (getArguments().containsKey("id")) {
      String s = getArguments().getString("id");
      Log.i("P", "s = " + s);
    }
  }

  // calls when it's time for the fragment to draw its layout.
  @Override
  public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

    // A boolean indicating whether the inflated layout should be attached to the ViewGroup (the second parameter) during inflation.
    View rootView = inflater.inflate(R.layout.item_detail, container, false);

    Random r = new Random();
    int i = r.nextInt();
    ((TextView) rootView.findViewById(R.id.item_detail)).setText("text = " + i);
    return rootView;

  }
}
