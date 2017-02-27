package com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;

import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.R;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.adapters.Donantes;

/**
 * Dialogo que captura los datos del fragment
 * Created by alexi on 27/02/2017.
 */

public class DialogoAgregarDonante extends DialogFragment{


    public static final String TAG = "dialogo_agregar_donantes";
    Donantes donantes;

    public interface OnAgregarDonanteListener{
        /** Interface que captura los datos y envia un objeto donante*/
        void AgregarDonante(Donantes donantes);
    }

    private OnAgregarDonanteListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.dialogo_agregar_donante, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view);


        return builder.create();


    }
}
