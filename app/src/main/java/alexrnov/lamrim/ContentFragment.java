package alexrnov.lamrim;

import android.content.Context;
import android.content.Intent;
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

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class ContentFragment extends Fragment {

  private String currentItemID = "";
  private boolean dualPane = false;
  private TextView textView;
  private TextViewModel model;



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

    Log.i("P", "create contentFragment");
    // here used requireActivity() (activity including fragment) - If one fragment
    // replaces the other one, the UI continues to work without any problems.
    model = new ViewModelProvider(requireActivity()).get(TextViewModel.class);

    // Create the observer which updates the UI.
    final Observer<String> textObserver = new Observer<String>() {
      @Override
      public void onChanged(@Nullable String newName) {
        // Update UI (TextView)
        textView.setText(newName);
      }
    };

    // Observe the LiveData, passing in this fragment as the LifecycleOwner and the observer.
    model.getMyLiveData().observe(this, textObserver);
    InputStream input = getResources().openRawResource(R.raw.text1);
    FileRepository.getInstance().doSomeStuff(input);
    //model.getText().observe(this, textObserver);
  }

  // calls when it'currentItemID time for the fragment to draw its layout.
  @Override
  public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // A boolean indicating whether the inflated layout should be attached to the ViewGroup (the second parameter) during inflation.
    View rootView;
    //TextView textView;
    if (dualPane) {
      rootView = inflater.inflate(R.layout.descript_text_view, container, false);
      textView = ((TextView) rootView.findViewById(R.id.item_detail));

      //model.getText1().setValue("55555");
      //model.getText().setValue("777777");
      /*
      textView.setText("text = " + currentItemID
              + " introduction text " +
              "dssdfkdskkk;lkmkmm \n c ffuihse gvgh dsf dsfdsf dsf sdf");
      */
      Button button = rootView.findViewById(R.id.details_button);
      button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          Context context = getContext();
          //model.getText1().setValue("555555");
          //model.getTextItem().getValue();
          if (context != null) {
            Intent intent = new Intent(context, ContentActivity.class);
            intent.putExtra("id", currentItemID);
            intent.putExtra("dualPaneMode", false);
            context.startActivity(intent);
          }
        }
      });

    } else {
      rootView = inflater.inflate(R.layout.detail_text_view, container, false);
      textView = ((TextView) rootView.findViewById(R.id.item_detail));
      textView.setText("text = " + currentItemID
              + " sddskljkj kdskckdscj dskjkskl dsklfsdk dskklsd " +
              "dssdfkdskkk;lkmkmm \n dsklf dskfdsckm \n dslkjk \n dkjslsc akl dfgef dsfse dsdfd ds fsdffd s dsf efsfsd dsf ds khghg kjhkjhkj hiuhuihiu iguihuihi hgjhgj jhgh jihese gvgh dsf dsfdsf dsf sdf");
    }

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
