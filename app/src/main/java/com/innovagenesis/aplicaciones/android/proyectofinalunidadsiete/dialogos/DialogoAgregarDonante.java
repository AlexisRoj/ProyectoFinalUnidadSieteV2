package com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.R;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.tbl_donantes_async.Donantes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Dialogo que captura los datos del fragment
 * Created by alexi on 27/02/2017.
 */

public class DialogoAgregarDonante extends DialogFragment implements View.OnClickListener {


    public static final String TAG = "dialogo_agregar_donantes";

    Donantes donantes;

    TextInputLayout texInputCed, textInputNombre, textInputApellido, textInputEdad, textInputPeso,
            textInputEstatura; // Campos del dialogo


    EditText editTextCed, editTextNombre, editTextApellido, editTextEdad,
            editTextPeso, editTextEstatura; // Campos del dialogo

    String nombre = "", apellido = "", grupo = "", factor = "";
    int cedula, edad, peso, estatura;

    String[] arregloGrupo;
    String[] arregloFactor;

    public interface OnAgregarDonanteListener {
        /**
         * Interface que captura los datos y envia un objeto donante
         */
        void AgregarDonante(Donantes donantes);
    }

    private OnAgregarDonanteListener listener;

    /********************************************************************************
     * *                               onCreateDialog                               **
     ********************************************************************************/

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
        /** Textview*/
        TextView textView = (TextView) view.findViewById(R.id.agrDonanteTitulo);
        /** TextInputLayout*/
        texInputCed = (TextInputLayout) view.findViewById(R.id.agrDonanteId);
        textInputNombre = (TextInputLayout) view.findViewById(R.id.agrDonanteNombre);
        textInputApellido = (TextInputLayout) view.findViewById(R.id.agrDonanteApellido);
        textInputEdad = (TextInputLayout) view.findViewById(R.id.agrDonanteEdad);
        textInputPeso = (TextInputLayout) view.findViewById(R.id.agrDonantePeso);
        textInputEstatura = (TextInputLayout) view.findViewById(R.id.agrDonanteEstatura);


        textView.setText(R.string.agre_donante);

        btnAgregar.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);

        /********************************************************************************
         *                                PRIMER SPINNER                               **
         ********************************************************************************/

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
                grupo = arregloGrupo[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        /********************************************************************************
         *                                SEGUNDO SPINNER                               **
         *********************************************************************************/

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
        spFactor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                factor = arregloFactor[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view);
        return builder.create();
    }

    /********************************************************************************
     * *                                     onAttach                               **
     ********************************************************************************/
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {

            listener = (OnAgregarDonanteListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("La inteface no ha sido implementada en el activity");
        }
    }

    @Override
    public void onClick(View elemento) {
        /** Botones del dialogo*/

        switch (elemento.getId()) {

            case R.id.agrDonanteBtnAgregar: {
                /********************************************************************************
                 *                       validacion y asignación campos                        **
                 ********************************************************************************/
                editTextCed = texInputCed.getEditText();
                editTextNombre = textInputNombre.getEditText();
                editTextApellido = textInputApellido.getEditText();
                editTextEdad = textInputEdad.getEditText();
                editTextPeso = textInputPeso.getEditText();
                editTextEstatura = textInputEstatura.getEditText();

                Boolean agregar = true;
                mAsignaciones();

                if (cedula == 0) {
                    editTextCed.setError(getString(R.string.campoVacio));
                    agregar = false;
                }
                if ("".equals(nombre)) {
                    editTextNombre.setError(getString(R.string.campoVacio));
                    agregar = false;
                }
                if ("".equals(apellido)) {
                    editTextApellido.setError(getString(R.string.campoVacio));
                    agregar = false;
                }
                if (edad == 0) {
                    editTextEdad.setError(getString(R.string.campoVacio));
                    agregar = false;
                }
                if (arregloFactor[0].equals(factor)) {
                    Toast.makeText(getContext(), getString(R.string.mensajeFactor),
                            Toast.LENGTH_SHORT).show();
                    agregar = false;
                }
                if (arregloGrupo[0].equals(grupo)) {
                    Toast.makeText(getContext(), getString(R.string.mensajeGrupo),
                            Toast.LENGTH_SHORT).show();
                    agregar = false;
                }
                if (peso == 0) {
                    editTextPeso.setError(getString(R.string.campoVacio));
                    agregar = false;
                }
                if (estatura == 0) {
                    editTextEstatura.setError(getString(R.string.campoVacio));
                    agregar = false;
                }
                if (agregar) {
                    donantes = new Donantes(cedula, nombre, apellido, edad, grupo, factor, peso, estatura);
                    listener.AgregarDonante(donantes);
                    dismiss();
                }
                break;
            }

            case R.id.agrDonanteBtnCancelar: {
                //Boton Cancelar
                dismiss();
            }
        }
    }

    private void mAsignaciones() {

        /** Realiza la asignacion de los recursos a cada variable**/

        if (texInputCed != null) {
            cedula = Integer.parseInt(editTextCed.getText().toString());
        }
        if (textInputNombre.getEditText() != null) {
            nombre = editTextNombre.getText().toString();
        }
        if (textInputApellido.getEditText() != null) {
            apellido = editTextApellido.getText().toString();
        }
        if (textInputEdad.getEditText() != null) {
            edad = Integer.parseInt(editTextEdad.getText().toString());
        }
        if (textInputPeso.getEditText() != null) {
            peso = Integer.parseInt(editTextPeso.getText().toString());
        }
        if (textInputEstatura != null) {
            estatura = Integer.parseInt(editTextEstatura.getText().toString());
        }
    }
}
