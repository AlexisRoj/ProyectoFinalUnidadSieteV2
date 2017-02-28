package com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.MainActivity;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.R;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.adapters.Donantes;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.R.array.grupo_sanguineo;

/**
 * Dialogo que captura los datos del fragment
 * Created by alexi on 27/02/2017.
 */

public class DialogoAgregarDonante extends DialogFragment{


    public static final String TAG = "dialogo_agregar_donantes";

    Donantes donantes;
    TextInputLayout nombre, apellido, edad, grupo, factor, peso, estatura; // Campos del dialogo

    String[] arregloGrupo;

    MainActivity activity = new MainActivity();


    public interface OnAgregarDonanteListener {
        /**
         * Interface que captura los datos y envia un objeto donante
         */
        void AgregarDonante(Donantes donantes);
    }

    private OnAgregarDonanteListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.dialogo_agregar_donante, null);

        /** Botones */
        Button btnAgregar = (Button) view.findViewById(R.id.agrDonanteBtnAgregar);
        Button btnCancelar = (Button) view.findViewById(R.id.agrDonanteBtnCancelar);
        /** Spinner*/
        final Spinner spGrupo = (Spinner) view.findViewById(R.id.agrDonanteGrupo);
        final Spinner spFactor = (Spinner) view.findViewById(R.id.agrDonanteFactor);


        arregloGrupo = getActivity().getResources().getStringArray(R.array.grupo_sanguineo);

         final List<String> plantsList = new ArrayList<>(Arrays.asList(arregloGrupo));
        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                getActivity(), R.layout.spinner_item, plantsList) {

            /** Crea efecto de un hint en el spinner*/
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    /** Funciona para desabilitar un elemento del spinner*/
                    return false;
                } else {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    /** Funciona para efecto visual de un elemento del spinner*/
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spGrupo.setAdapter(spinnerArrayAdapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view);


        return builder.create();

    }




}
