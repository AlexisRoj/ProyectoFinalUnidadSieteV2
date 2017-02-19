package com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.dialogos.DialogoCambiarContrasena;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.dialogos.DialogoCrearUsuario;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.preference.PreferenceConstant;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.tbl_users_async.UpdateUserAsync;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity
        implements DialogoCambiarContrasena.OnCambiarContrasenaUserListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        if (id == R.id.action_cambiarPass){
            DialogoCambiarContrasena dialogo = new DialogoCambiarContrasena();
            dialogo.show(getSupportFragmentManager(), DialogoCrearUsuario.TAG);
        }


        //http://192.168.100.4:8080/WebServiceExamenSiete/webapi/Users/


        return super.onOptionsItemSelected(item);
    }

    public void borrarPreference() {

        SharedPreferences pref = getSharedPreferences(PreferenceConstant.PREFERENCE_LOGIN, MODE_PRIVATE);

        /** Borra las preferencias */
        SharedPreferences.Editor edit = pref.edit();
        edit.remove(PreferenceConstant.USER_NAME);
        edit.remove(PreferenceConstant.USER_PASS);
        edit.apply();

        Intent intent = new Intent(MainActivity.this, Login.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void CambiarContrasenaUser(String username, String userpass) {

        try {
            /** Dependiendo del resultado del dialogo ejecuta el cambio*/
            new UpdateUserAsync(this,userpass).execute(new URL(PreferenceConstant.SERVERUSER + username));
            /** Pedir ingreso para refrescar las preferencias*/
            Toast.makeText(this, "Favor volver a ingresar al sistema",Toast.LENGTH_SHORT).show();
            borrarPreference();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
}
