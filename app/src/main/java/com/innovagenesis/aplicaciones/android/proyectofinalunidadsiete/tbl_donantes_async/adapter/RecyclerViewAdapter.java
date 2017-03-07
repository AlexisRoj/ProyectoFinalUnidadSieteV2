package com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.tbl_donantes_async.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.R;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.preference.PreferenceConstant;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.tbl_donantes_async.Donantes;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.tbl_donantes_async.donantes_async.BorrarDonantesAsync;


import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

/**
 *
 * Created by alexi on 04/03/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private Context context;
    private Activity activity;
    private LayoutInflater inflater;
    private List<Donantes> data = Collections.emptyList();

    public RecyclerViewAdapter(Context context, Activity activity, List<Donantes> data) {
        //Constructor
        this.context = context;
        this.activity = activity;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.template_item_donante,parent,false);

        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {

        final Donantes current = data.get(position);
        final int idDonanteBorrar = current.donante_ced;

        /** Carga los datos en el recyclerView */
        holder.idCedula.setText(String.valueOf(current.donante_ced));
        holder.nombre.setText(current.donante_nombre);
        holder.apellido.setText(current.donante_apellido);
        holder.edad.setText(String.valueOf(current.donante_edad));
        holder.tipoSangre.setText(current.donante_tipo);
        holder.factorSangre.setText(current.donante_factor);
        holder.peso.setText(String.valueOf(current.donante_peso));
        holder.estatura.setText(String.valueOf(current.donante_estatura));

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Editar donante",Toast.LENGTH_SHORT).show();


               /* data.add()
                ObjectOutputStream output =
                        new ObjectOutputStream(context.openFileOutput(nombreArchivo,
                                Context.MODE_PRIVATE));
                output.writeObject(vehiculos);
                output.close();*/
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Mensaje que pregunta antes de eliminar un donante
                elimarDonante(holder,idDonanteBorrar).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public AlertDialog elimarDonante(final RecyclerViewHolder holder, final int idDonanteBorrar) {

        /** Dialogo que borra la cuenta de usuario en la que se encuentra*/
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(R.string.tituloMensajeEliminarDonante)
                .setMessage(R.string.eliminarDonante)
                .setPositiveButton(activity.getString(R.string.aceptar),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                int item = holder.getAdapterPosition();

                                try {
                                    /** Remueve el donante de la base de datos*/
                                    new BorrarDonantesAsync(activity).execute(
                                            new URL(PreferenceConstant.URL_TBL_DONANTES
                                                    + idDonanteBorrar));

                                    /** Remuelve el elemento del recyclerView*/
                                    remoteDonanteRecycler(item);
                                } catch (MalformedURLException e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                .setNegativeButton(activity.getString(R.string.cancelar),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

        return builder.create();
    }

    private void remoteDonanteRecycler(int item) {
        //Elimina los items del recyclerView
        data.remove(item);
        notifyItemRemoved(item);
        notifyItemRangeChanged(item, data.size());
    }
}
