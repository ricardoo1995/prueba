package com.example.camk1.app_tienda.Clases;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.camk1.app_tienda.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SinConexionFragment extends DialogFragment {

    public boolean volverCargar = false;
    private DialogInterface.OnDismissListener onDismissListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());

        //llama a la actividad dlientedialogo.xml
        LayoutInflater infrater = getActivity().getLayoutInflater();
        View view = infrater.inflate(R.layout.fragment_sin_conexion, null);
        builder.setView(view);
        Button cargar = (Button) view.findViewById(R.id.dialogoBtn_cargar);
        Button cancelar = (Button) view.findViewById(R.id.dialogoBtn_cancelar);

        cargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volverCargar = true;
                dismiss();
            }
        });
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volverCargar = false;
                dismiss();
            }
        });
        return builder.create();
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (onDismissListener != null) {
            onDismissListener.onDismiss(dialog);
        }
    }

    public boolean isVolverCargar() {
        return volverCargar;
    }
}
