package com.github.constructionplannotes;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class InputDialog extends AlertDialog {

    interface InputDialogResultListener{
        void onOkSelected(String input);
        void onCancelSelected();
    }

    private Context context;

    InputDialog(@NonNull Context context) {
        super(context);
        init(context);
    }

    InputDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }

    InputDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init(context);
    }

    private void init(@NonNull Context context){
        this.context = context;
    }

    void showInputDialog(final InputDialogResultListener dialogResultListener) {

        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View promptView = layoutInflater.inflate(R.layout.dialog_input, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(promptView);

        final EditText editText = (EditText) promptView.findViewById(R.id.edittext);
        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if(dialogResultListener!=null){
                            dialogResultListener.onOkSelected(editText.getText().toString());
                        }
                    }
                })
                .setNegativeButton(android.R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if(dialogResultListener!=null){
                                    dialogResultListener.onCancelSelected();
                                }
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }
}
