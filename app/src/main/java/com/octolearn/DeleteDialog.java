package com.octolearn;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DeleteDialog extends AppCompatDialogFragment {
    private DialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Delete catalog");
        builder.setMessage("Are you sure that you want to delete catalog? All data will be lost");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new Dialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int whichButton) {
                listener.onYesClicked();
            }
        });
        builder.setNegativeButton("No", new Dialog.OnClickListener() {
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

