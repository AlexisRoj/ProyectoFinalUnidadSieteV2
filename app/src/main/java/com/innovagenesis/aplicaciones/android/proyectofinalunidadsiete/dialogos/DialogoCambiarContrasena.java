package com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.R;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.preference.PreferenceConstant;

import static android.content.Context.MODE_PRIVATE;

/**
 * Administra el cambio de contraseña
 * Created by alexi on 18/02/2017.
 */

public class DialogoCambiarContrasena extends DialogFragment {

    private SharedPreferences preference;
    private String user_name;
    private String user_pass;


    public static final String TAG = "dialogo_camnbiar_contrasena";

    public interface OnCambiarContrasenaUserListener {

        void CambiarContrasenaUser(String username, String userpassnuevo);
    }

    private OnCambiarContrasenaUserListener listener;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.dialogo_polifuncional, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view);/** Edit Text del Dialogo*/
        TextView textView = (TextView) view.findViewById(R.id.dialogoTitulo);

        final EditText contrasenaActual = (EditText) view.findViewById(R.id.dialogoCampo1);
        final EditText nuevaContrasena = (EditText) view.findViewById(R.id.dialogoCampo2);
        final EditText RepitaContrasena = (EditText) view.findViewById(R.id.dialogoCampo3);


        /** Botones del Dialogo*/
        Button btnUno = (Button) view.findViewById(R.id.dialogoBtnReg);
        Button btnDos = (Button) view.findViewById(R.id.dialogoBtnCancel);

        /** Cambiando propiedades*/

        textView.setText(getString(R.string.cambiar_contrasena));

        contrasenaActual.setHint(getString(R.string.contrasena_actual));
        nuevaContrasena.setHint(getString(R.string.nueva_contrasena));
        RepitaContrasena.setHint(getString(R.string.repetir_contrasena));

        btnUno.setText(getString(R.string.guardar));
        btnDos.setText(getString(R.string.cancelar));


        preference = getContext().getSharedPreferences(PreferenceConstant.PREFERENCE_LOGIN, MODE_PRIVATE);

        /** Convierte el texto a pass*/
        contrasenaActual.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        /** Cargar preferencias para hacer el login*/
        user_name = preference.getString(PreferenceConstant.USER_NAME, null);
        user_pass = preference.getString(PreferenceConstant.USER_PASS, null);

        /** Falta los controles y las validaciones de los datos*/

        btnUno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String actual = contrasenaActual.getText().toString();
                final String nueva = nuevaContrasena.getText().toString();
                final String confirmarNueva = RepitaContrasena.getText().toString();


                if (actual.equals(user_pass)) {
                    if (nueva.equals(confirmarNueva)) {
                        listener.CambiarContrasenaUser(user_name, nueva);
                    } else
                        Toast.makeText(getContext(), "No coinciden las contraseñas \n" +
                                user_pass + "-" + actual + "-" + nueva + "-" + confirmarNueva
                                , Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "No coincide la contraseña actual \n" +
                            user_pass + "-" + actual + "-" + nueva + "-" + confirmarNueva,
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        btnDos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (OnCambiarContrasenaUserListener) getContext();
        } catch (ClassCastException e) {
            throw new ClassCastException("La inteface no ha sido implementada en el activity");
        }
    }
}
