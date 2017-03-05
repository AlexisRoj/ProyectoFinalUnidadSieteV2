package com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.tbl_users_async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Elimina la cuenta del usuario
 * Created by alexi on 19/02/2017.
 */

public class BorrarUserAsync extends AsyncTask<URL, Integer, String> {

    private Activity activity;
    private ProgressDialog dialog;

    public BorrarUserAsync(Activity activity) {
        this.activity = activity;
        dialog = new ProgressDialog(activity);
    }

    @Override
    protected String doInBackground(URL... params) {

        HttpURLConnection connection = null;

        try {
            connection = (HttpURLConnection) params[0].openConnection();
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
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        Toast.makeText(activity, R.string.userDelete, Toast.LENGTH_SHORT).show();
    }
}

