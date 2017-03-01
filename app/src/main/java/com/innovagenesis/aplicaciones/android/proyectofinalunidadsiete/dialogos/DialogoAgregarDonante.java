package com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
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
import android.widget.Toast;

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

public class DialogoAgregarDonante extends DialogFragment implements View.OnClickListener {


    public static final String TAG = "dialogo_agregar_donantes";

    Donantes donantes;
    TextInputLayout textImputNombre, textImputApellido, textImputEdad, textImputGrupo,
            textImputFactor, textImputPeso, textImputEstatura; // Campos del dialogo

    String nombre, apellido, grupo, factor;
    int edad, peso, estatura;

    String[] arregloGrupo;
    String[] arregloFactor;

    @Override
    public void onClick(View elemento) {
        /** Botones del dialogo*/

        switch (elemento.getId()){

            case R.id.agrDonanteBtnAgregar:{
                break;
            }

            case R.id.agrDonanteBtnCancelar:{
                dismiss();
            }

        }


    }


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

        btnAgregar.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);

        /**                                                                             **
         *                                PRIMER SPINNER                                **
         *                                                                              **
         **                                                                             */

        arregloGrupo = getActivity().getResources().getStringArray(R.array.grupo_sanguineo);

        final List<String> aGrupoSanguinieo = new ArrayList<>(Arrays.asList(arregloGrupo));
        final ArrayAdapter<String> spinnerArrayAdapterGrupo = new ArrayAdapter<String>(
                getActivity(), R.layout.spinner_item, aGrupoSanguinieo) {

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

        spinnerArrayAdapterGrupo.setDropDownViewResource(R.layout.spinner_item);
        spGrupo.setAdapter(spinnerArrayAdapterGrupo);

        spGrupo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                /** Captura grupo sangineo*/
                grupo = arregloGrupo [position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        /**                                                                            **
        *                                SEGUNDO SPINNER                               **
        *                                                                              **
        **                                                                             */

        arregloFactor = getActivity().getResources().getStringArray(R.array.factor_sanguineo);

        final List<String> aFactorSanguinieo = new ArrayList<>(Arrays.asList(arregloFactor));
        final ArrayAdapter<String> spinnerArrayAdapterFactor = new ArrayAdapter<String>(
                getActivity(), R.layout.spinner_item, aFactorSanguinieo) {

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


        spinnerArrayAdapterFactor.setDropDownViewResource(R.layout.spinner_item);
        spFactor.setAdapter(spinnerArrayAdapterFactor);

        nombre = "Alexis";
        apellido = "Rojas";
        edad = 38;
        grupo = "Pelada";
        peso = 100;
        estatura = 180;

        donantes = new Donantes(nombre,apellido,edad,grupo,peso,estatura);

        listener.AgregarDonante(donantes);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view);


        return builder.create();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {

            listener = (OnAgregarDonanteListener) context;
        }catch (ClassCastException e) {
            throw new ClassCastException("La inteface no ha sido implementada en el activity");
        }
    }
}
