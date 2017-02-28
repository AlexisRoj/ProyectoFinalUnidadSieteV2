package com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.R;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.adapters.Donantes;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Dialogo que captura los datos del fragment
 * Created by alexi on 27/02/2017.
 */

public class DialogoAgregarDonante extends DialogFragment{


    public static final String TAG = "dialogo_agregar_donantes";

    Donantes donantes;
    TextInputLayout nombre,apellido,edad,grupo,factor,peso,estatura; // Campos del dialogo


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

        /** Botones */
        Button btnAgregar = (Button)view.findViewById(R.id.agrDonanteBtnAgregar);
        Button btnCancelar = (Button)view.findViewById(R.id.agrDonanteBtnCancelar);
        /** Spinner*/
        Spinner spGrupo = (Spinner)view.findViewById(R.id.agrDonanteGrupo);
        Spinner spFactor = (Spinner)view.findViewById(R.id.agrDonanteFactor);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getContext(),R.array.grupo_sanguineo , android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGrupo.setAdapter(adapter);
        spGrupo.setOnItemSelectedListener(new MyOnItemSelectedListener()){
        };



        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view);


        return builder.create();


    }
}
