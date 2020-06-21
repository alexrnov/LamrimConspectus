package alexrnov.lamrim;

import android.app.Dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

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

    builder.setView(inflater.inflate(R.layout.about_dialog, null))
            // Add action buttons
            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int id) {
              }
            })
            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int id) {
                AboutDialogFragment.this.getDialog().cancel();
              }
            });

    return builder.create();
  }
}