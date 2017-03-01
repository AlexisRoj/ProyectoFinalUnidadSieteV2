package com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.adapters.Donantes;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.dialogos.DialogoAgregarDonante;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.dialogos.DialogoCambiarContrasena;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.dialogos.DialogoCrearUsuario;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.preference.PreferenceConstant;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.tbl_users_async.DeleteUserAsync;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.tbl_users_async.UpdateUserAsync;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity
        implements DialogoCambiarContrasena.OnCambiarContrasenaUserListener,
        DialogoAgregarDonante.OnAgregarDonanteListener {

    private SharedPreferences pref;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pref = getSharedPreferences(PreferenceConstant.PREFERENCE_LOGIN, MODE_PRIVATE);
        username = pref.getString(PreferenceConstant.USER_NAME, null);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                DialogoAgregarDonante dialogo = new DialogoAgregarDonante();
                dialogo.show(getSupportFragmentManager(), DialogoAgregarDonante.TAG);

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_close_pref) {
            borrarPreference();
        }
        if (id == R.id.action_cambiarPass) {
            DialogoCambiarContrasena dialogo = new DialogoCambiarContrasena();
            dialogo.show(getSupportFragmentManager(), DialogoCrearUsuario.TAG);
        }

        if (id == R.id.action_eliminar_cuenta) {

            createSimpleDialog().show();

        }

        return super.onOptionsItemSelected(item);
    }

    public void borrarPreference() {

        /** Borra las preferencias */
        SharedPreferences.Editor edit = pref.edit();
        edit.remove(PreferenceConstant.USER_NAME);
        edit.remove(PreferenceConstant.USER_PASS);
        edit.remove(PreferenceConstant.USER_PREF);
        edit.apply();

        Intent intent = new Intent(MainActivity.this, Login.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void CambiarContrasenaUser(String username, String userpass) {

        try {
            /** Dependiendo del resultado del dialogo ejecuta el cambio*/
            new UpdateUserAsync(this, userpass).execute(new URL(PreferenceConstant.SERVERUSER + username));
            /** Pedir ingreso para refrescar las preferencias*/
            Toast.makeText(this, "Favor volver a ingresar al sistema", Toast.LENGTH_SHORT).show();
            borrarPreference();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    public AlertDialog createSimpleDialog() {

        /** Dialogo que borra la cuenta de usuario en la que se encuentra*/
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(getString(R.string.eliminarCuenta))
                .setMessage(R.string.mensajeEliminarCuenta)
                .setPositiveButton(getString(R.string.aceptar),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                try {
                                    new DeleteUserAsync(MainActivity.this).execute(new URL(PreferenceConstant.SERVERUSER +
                                            username));
                                    borrarPreference();
                                } catch (MalformedURLException e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                .setNegativeButton(getString(R.string.cancelar),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

        return builder.create();
    }


    @Override
    public void AgregarDonante(Donantes donantes) {

        TextView textView = (TextView) findViewById(R.id.text);

        textView.setText(
                donantes.getDonante_ced() + "\n " +
                        donantes.getDonante_nombre() + "\n " +
                        donantes.getDonante_apellido() + "\n " +
                        donantes.getDonante_edad() + "\n " +
                        donantes.getDonante_grupo() + "\n " +
                        donantes.getDonante_factor() + "\n " +
                        donantes.getDonante_peso() + "\n " +
                        donantes.getDonante_estatura());
    }
}
