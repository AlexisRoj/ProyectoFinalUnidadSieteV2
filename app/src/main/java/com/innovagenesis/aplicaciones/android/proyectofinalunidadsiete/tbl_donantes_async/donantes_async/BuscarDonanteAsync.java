package com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.tbl_donantes_async.donantes_async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.R;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.tbl_donantes_async.Donantes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * clase encargada de realizar las busquedas de usuario.
 * Created by alexi on 06/03/2017.
 */

public class BuscarDonanteAsync extends AsyncTask<URL,Integer,String> {

    private Activity activity;
    private ProgressDialog dialog;
    private Donantes donantes;

    public interface OnDonanteEncontrado{
        void UsuarioEncontrado (Donantes donantes);
    }

    private OnDonanteEncontrado listener;

    public BuscarDonanteAsync(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected String doInBackground(URL... params) {

        HttpURLConnection connection = null;

        try {
            connection = (HttpURLConnection)params[0].openConnection();
            BufferedReader reader = new BufferedReader( new InputStreamReader(
                    connection.getInputStream()));
            return reader.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            assert connection != null;
            connection.disconnect();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog.setMessage(activity.getString(R.string.waiting));
        dialog.show();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        // Falta crear el metodo que devuelva todos los elementos encontrados
    }
}
