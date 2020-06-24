package alexrnov.lamrim;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

public class ContentFragment extends Fragment {

  private String currentItemID = "";
  private TextView textView;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (getArguments() != null && getArguments().containsKey("id")) {
      currentItemID = getArguments().getString("id");
      Log.i("P", "currentItemID = " + currentItemID);
    }
  }

  // calls when it'currentItemID time for the fragment to draw its layout.
  @Override
  public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

    // A boolean indicating whether the inflated layout should be attached to the ViewGroup (the second parameter) during inflation.
    View rootView = inflater.inflate(R.layout.item_detail, container, false);

    textView = ((TextView) rootView.findViewById(R.id.item_detail));
    textView.setText("text = " + currentItemID
            + " sddskljkj kdskckdscj dskjkskl dsklfsdk dskklsd " +
            "dssdfkdskkk;lkmkmm \n dsklf dskfdsckm \n dslkjk \n dkjslsc akl dfgef dsfse dsdfd ds fsdffd s dsf efsfsd dsf ds khghg kjhkjhkj hiuhuihiu iguihuihi hgjhgj jhgh jihese gvgh dsf dsfdsf dsf sdf");

    return rootView;
  }

  @Override
  public void onResume() {
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
    String size = sharedPreferences.getString("font_size", "20");
    String color = sharedPreferences.getString("font_color", "#000000");
    textView.setTextSize(Float.valueOf(size));
    textView.setTextColor(Color.parseColor(color));
    super.onResume();
  }
}
