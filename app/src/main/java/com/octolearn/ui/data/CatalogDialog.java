package com.octolearn.ui.data;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

public class CatalogDialog extends AppCompatDialogFragment {
    private DialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add catalog");
        builder.setCancelable(false);
        final EditText input = new EditText(getContext());
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("Yes", new Dialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int whichButton) {
                listener.onYesClicked();
            }
        });
        builder.setNegativeButton("Cancel", new Dialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int whichButton) {
            }
        });
        return builder.create();
    }
    public interface DialogListener {
        void onYesClicked();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try{
            listener = (DialogListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "must implement DialogListener");
        }
    }
}
