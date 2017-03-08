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
 * Clase encargada de modificar los donantes
 * Created by alexi on 06/03/2017.
 */

public class ModificarDonanteAsync extends AsyncTask<URL,Integer,Boolean> {

    private Activity activity;
    private ProgressDialog dialog;
    private Donantes donantes;

    public ModificarDonanteAsync(Donantes donantes, FragmentActivity activity) {
        this.activity = activity;
        this.donantes = donantes;
        dialog = new ProgressDialog(activity);
    }

    @Override
    protected Boolean doInBackground(URL... params) {

        HttpURLConnection connection = null;

        JSONObject datos = new JSONObject();

        try {
            datos.put("donante_nombre",donantes.donante_nombre)
                    .put("donante_apellido",donantes.donante_apellido)
                    .put("donante_edad",donantes.donante_edad)
                    .put("donante_tipo",donantes.donante_tipo)
                    .put("donante_factor",donantes.donante_factor)
                    .put("donante_peso",donantes.donante_peso)
                    .put("donante_estatura",donantes.donante_estatura);

            connection = (HttpURLConnection)params[0].openConnection();
            connection.setRequestMethod("PUT");
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
        return null;
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

        if (aBoolean) {
            Toast.makeText(activity, R.string.donantesActualizado, Toast.LENGTH_SHORT).show();
            activity.closeContextMenu();
        }

        if (dialog.isShowing()){
            dialog.dismiss();
        }


    }
}
