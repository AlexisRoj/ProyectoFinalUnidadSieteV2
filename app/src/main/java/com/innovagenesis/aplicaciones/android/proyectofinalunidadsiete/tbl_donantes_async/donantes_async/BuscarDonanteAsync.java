package com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.tbl_donantes_async.donantes_async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.R;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.tbl_donantes_async.Donantes;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * clase encargada de realizar las busquedas de usuario.
 * Created by alexi on 06/03/2017.
 */

public class BuscarDonanteAsync extends AsyncTask<URL, Integer, String> {

    private Activity activity;
    private ProgressDialog dialog;
    private int cedula, donante_ced;


    public interface OnDonanteEncontrado {
        void UsuarioEncontrado(Boolean existe);
    }

    private OnDonanteEncontrado listener;

    public BuscarDonanteAsync(Activity activity, int cedula) {
        dialog = new ProgressDialog(activity);
        this.cedula = cedula;
        this.activity = activity;

        try {
            listener = (OnDonanteEncontrado) activity;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String doInBackground(URL... params) {

        HttpURLConnection connection = null;

        try {
            connection = (HttpURLConnection) params[0].openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            return reader.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
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
        Boolean stop = true;

        if (dialog.isShowing()) {
            dialog.dismiss();
        }

        try {
            JSONArray jsonArray = new JSONArray(s);

            for (int i = 0; i < jsonArray.length(); i++) {
                /** Mejorar se utilizo el list*/
                donante_ced = jsonArray.getJSONObject(i).getInt("donante_ced");

                if (donante_ced == cedula) {
                    stop = false;
                    listener.UsuarioEncontrado(true);
                    break;
                }

            }

            if (stop)
                listener.UsuarioEncontrado(false);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
