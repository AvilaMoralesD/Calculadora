package com.dami.calculadora.ui.base;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class BaseDialogFragment extends DialogFragment {
    public static final String TITLE = "title";
    public static final String MESSAGE = "message";
    public static final String REQUEST = "requestdialog";
    public static final String KEY_BUNDLE = "result";


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle(getArguments().getString(TITLE));
            builder.setMessage(getArguments().getString(MESSAGE));
            builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //Una de las claves para realizar la comunicación entre fragmentos (padre-hijo) es utilizar los métodos
                    // supportFragmentManager de la actividad para realizar el intercambio de información.
                    Bundle result = new Bundle();
                    result.putBoolean(KEY_BUNDLE, true);
                    getActivity().getSupportFragmentManager().setFragmentResult(REQUEST, result);

                }
            });
            builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dismiss();
                }
            });
            return builder.create();
        }
        return null;
    }
}