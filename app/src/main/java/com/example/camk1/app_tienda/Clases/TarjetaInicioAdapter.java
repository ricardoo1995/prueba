package com.example.camk1.app_tienda.Clases;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.camk1.app_tienda.ProductoCompraActivity;
import com.example.camk1.app_tienda.ProductoListaActivity;
import com.example.camk1.app_tienda.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Ivan on 2/8/2016.
 */
public class TarjetaInicioAdapter extends RecyclerView.Adapter<TarjetaInicioAdapter.ViewHolder> {
    private static List<TarjetaInicio> items;
    private View v;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item

        public TextView nombre;
        public ImageView imagen;

        public ViewHolder(final View v) {
            super(v);
            nombre = (TextView) v.findViewById(R.id.tarjetaNombre);
            imagen=(ImageView)v.findViewById(R.id.tarjetaImagen);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = v.getContext();
                    int pos = getAdapterPosition();
                    TarjetaInicio tarjetaInicio = items.get(pos);
                    String categoria = tarjetaInicio.getNombre();
                    String cantidad=tarjetaInicio.getCantidad();
                    String nombrep=tarjetaInicio.getNombrep();
                    String importe=tarjetaInicio.getImporte();
                    String precio=tarjetaInicio.getPrecio();

                    //Toast.makeText(context.getApplicationContext(),producto+"---"+urlImagen,Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(context, ProductoListaActivity.class);
                    intent.putExtra("Categoria",categoria);
                    intent.putExtra("cantidadp",cantidad);
                    intent.putExtra("nombrep",nombrep);
                    intent.putExtra("importep",importe);
                    intent.putExtra("precio",precio);
                    context.startActivity(intent);

                }
            });
        }
    }

    public TarjetaInicioAdapter(List<TarjetaInicio> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int i) {
        v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.tarjeta_producto, viewGroup, false);
        /*v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast1 =
                        Toast.makeText(v.getContext(),String.valueOf(1), Toast.LENGTH_SHORT);

                toast1.show();
            }
        });*/
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Picasso.with(v.getContext()).load(items.get(i).getUrlImagen()).into(viewHolder.imagen);
        viewHolder.nombre.setText(items.get(i).getNombre());
    }
}
