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
public class ProductoListaAdapter extends RecyclerView.Adapter<ProductoListaAdapter.ViewHolder> {
    private static List<Producto> items;
    private View v;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item

        public TextView nombre;


        public ViewHolder(final View v) {
            super(v);
            nombre = (TextView) v.findViewById(R.id.tarjetaNombreLista);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Context context = v.getContext();

                    int pos = getAdapterPosition();
                    Producto producto = items.get(pos);
                    String keyProducto = producto.getKey();
                    String categoria = producto.getCategoria();

                    String cantidad=producto.getCantidad();
                    String nombrep=producto.getNombrep();
                    String importe=producto.getImporte();
                    String precio=producto.getPrecio();
                    Intent intent = new Intent(context, ProductoCompraActivity.class);
                    intent.putExtra("Categoria",categoria);
                    intent.putExtra("cantidadp",cantidad);
                    intent.putExtra("nombrep",nombrep);
                    intent.putExtra("importep",importe);
                    intent.putExtra("preciop",precio);
                    intent.putExtra("KeyProducto",keyProducto);
                    context.startActivity(intent);

                }
            });
        }
    }

    public ProductoListaAdapter(List<Producto> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int i) {
        v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.tarjeta, viewGroup, false);
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
        viewHolder.nombre.setText(items.get(i).getNombre());
    }
}
