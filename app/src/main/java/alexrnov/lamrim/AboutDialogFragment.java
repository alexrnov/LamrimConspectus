package alexrnov.lamrim;

import android.app.Dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import org.jetbrains.annotations.NotNull;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class AboutDialogFragment extends DialogFragment {

  @NotNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    //AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.MyAlertDialogTheme);
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    LayoutInflater inflater = getActivity().getLayoutInflater();

    /*
    button.setOnClickListener( new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Log.i("P", "Click");
      }
    });

     */
    View v = inflater.inflate(R.layout.about_dialog, null);
    builder.setView(v);
            // Add action buttons
            /*
            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int id) {
              }
            });
            */

            /*
            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int id) {
                AboutDialogFragment.this.getDialog().cancel();
              }
            });
            */


    AlertDialog alertDialog = builder.create();
    Button button = (Button) v.findViewById(R.id.close_dialog_button);

    if (button == null) {
      Log.i("P", "button is null");
    } else {
      Log.i("P", "button is not null");
    }

    button.setOnClickListener( new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        AboutDialogFragment.this.getDialog().cancel();
      }
    });

    return builder.create();
  }
}
