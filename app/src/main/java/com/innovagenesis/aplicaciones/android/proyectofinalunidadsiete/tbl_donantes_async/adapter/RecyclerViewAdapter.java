package com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.tbl_donantes_async.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.R;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.tbl_donantes_async.Donantes;

import java.util.Collections;
import java.util.List;

/**
 *
 * Created by alexi on 04/03/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private Context context;
    private Activity activity;
    LayoutInflater inflater;
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
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {

        final Donantes current = data.get(position);
        final int idDonanteBorrar = holder.getAdapterPosition();

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
                Toast.makeText(context,"Delete",Toast.LENGTH_SHORT).show();
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int item = holder.getAdapterPosition();
                //aca quede
            }
        });

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
