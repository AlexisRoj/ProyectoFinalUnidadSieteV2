package com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.R;



/**
 * Dialogo de crear usuarios
 * Created by alexi on 16/02/2017.
 */

public class DialogoCrearUsuario extends DialogFragment {


    public static final String TAG = "dialogo_crear_usuario";

    public interface OnInsertarUserListener {

        void InsertarUser(String username, String userpass);
    }

    private OnInsertarUserListener listener;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.dialogo_crear_user, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view);

        /** Edit Text del Dialogo*/
        final EditText nombreUsuario = (EditText) view.findViewById(R.id.etNewUsername);
        final EditText passUsuario1 = (EditText) view.findViewById(R.id.etNewUserpass);
        final EditText passUsuario2 = (EditText) view.findViewById(R.id.etNewUserpass2);
        /** Botones del Dialogo*/
        Button btnLogin = (Button) view.findViewById(R.id.btnReg);
        Button btnCancel = (Button) view.findViewById(R.id.btnCancel);




        /** Falta los controles y las validaciones de los datos*/


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String nombUser = nombreUsuario.getText().toString();
                final String passUser1 = passUsuario1.getText().toString();
                final String passUser2 = passUsuario2.getText().toString();

                listener.InsertarUser(nombUser,passUser1);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
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
            listener = (OnInsertarUserListener) getContext();
        } catch (ClassCastException e) {
            throw new ClassCastException("La inteface no ha sido implementada en el activity");
        }
    }

}
