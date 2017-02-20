package com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.tbl_users_async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Administra la validacion del usuario y ejecuta el ingreso del usuario
 * Created by alexi on 16/02/2017.
 */

public class ConsultarUserAsync extends AsyncTask<URL, Integer,String> {
    @Override
    protected String doInBackground(URL... params) {
        HttpURLConnection connection = null;

        try {
            /** Conexion a la base de datos*/
            connection = (HttpURLConnection) params[0].openConnection();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            return reader.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert connection != null;
            connection.disconnect();
        }
        return null;
    }

    public interface OnIfExistUser {
        /**
         * Interface que envia los datos del login si existe
         */
        void OnIfExistUserGetFinish(Boolean username);
    }

    private OnIfExistUser listener;

    private Activity activity;
    private ProgressDialog dialog;

    public ConsultarUserAsync(Activity activity) {
        /** Constructor*/
        this.activity = activity;
        dialog = new ProgressDialog(activity);

        try {
            listener = (OnIfExistUser) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("La activity no implementa la interfaz consultar usuario");
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog.setMessage(activity.getString(R.string.waiting));
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        /** Si el usuario existe devuelve un verdadero de lo contrario
         * permiete almacenar el nuevo usuario*/
        if (s != null){
            listener.OnIfExistUserGetFinish(true);
        }else{
            listener.OnIfExistUserGetFinish(false);
        }
    }
}



