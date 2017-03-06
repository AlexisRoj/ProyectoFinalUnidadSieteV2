package com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.tbl_donantes_async.donantes_async;

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
 * Esta es la clase encargada de borrar los donantes
 * Created by alexi on 05/03/2017.
 */

public class BorrarDonantesAsync extends AsyncTask<URL,Integer,String>{

    private Activity activity;
    private ProgressDialog dialog;

    public BorrarDonantesAsync(Activity activity) {
        this.activity = activity;
        dialog = new ProgressDialog(activity);
    }

    @Override
    protected String doInBackground(URL... params) {

        HttpURLConnection connection = null;

        try {
            connection = (HttpURLConnection)params[0].openConnection();
            connection.setRequestMethod("DELETE");
            BufferedReader reader = new BufferedReader(new InputStreamReader(
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
        if (dialog.isShowing()){
            dialog.dismiss();
        }
    }
}
