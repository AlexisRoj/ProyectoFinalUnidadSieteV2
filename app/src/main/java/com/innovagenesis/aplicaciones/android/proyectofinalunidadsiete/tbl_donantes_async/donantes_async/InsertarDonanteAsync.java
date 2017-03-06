package com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.tbl_donantes_async.donantes_async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.R;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.tbl_donantes_async.Donantes;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Clase encargada de agregar donantes
 * Created by alexi on 05/03/2017.
 */

public class InsertarDonanteAsync extends AsyncTask<URL, Integer, Boolean> {

    private Donantes donantes;
    private Activity activity;
    private ProgressDialog dialog;

    public interface OnDonanteAgregado {
        void DonanteAgregado(Boolean agregado);
    }

    private OnDonanteAgregado listener;


    public InsertarDonanteAsync(Donantes donantes, Activity activity) {
        this.donantes = donantes;
        this.activity = activity;
        dialog = new ProgressDialog(activity);

        try {
            listener = (OnDonanteAgregado) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("La interface no ha sido implementada");
        }
    }

    @Override
    protected Boolean doInBackground(URL... params) {

        HttpURLConnection connection = null;

        JSONObject datos = new JSONObject();

        try {
            datos.put("donante_ced",donantes.donante_ced)
                    .put("donante_nombre",donantes.donante_nombre)
                    .put("donante_apellido",donantes.donante_apellido)
                    .put("donante_edad",donantes.donante_edad)
                    .put("donante_tipo",donantes.donante_tipo)
                    .put("donante_factor",donantes.donante_factor)
                    .put("donante_peso",donantes.donante_peso)
                    .put("donante_estatura",donantes.donante_estatura);

            connection = (HttpURLConnection)params[0].openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            connection.setDoOutput(true);
            connection.setFixedLengthStreamingMode(datos.toString().getBytes().length);

            OutputStream output = connection.getOutputStream();
            output.write(datos.toString().getBytes());
            output.flush();
            output.close();
            return true;

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }finally {
            assert connection != null;
            connection.disconnect();
        }
        return false;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog.setMessage(activity.getString(R.string.waiting));
        dialog.show();
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);

        if (aBoolean){
            listener.DonanteAgregado(true);
            Toast.makeText(activity, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
            activity.closeContextMenu();
        }
        if (dialog.isShowing())dialog.dismiss();
    }
}
