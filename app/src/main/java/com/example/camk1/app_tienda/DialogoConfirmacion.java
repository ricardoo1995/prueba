package com.example.camk1.app_tienda;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class DialogoConfirmacion extends DialogFragment {

    public boolean volverCargar = false;
    private DialogInterface.OnDismissListener onDismissListener;
    String cantidad="";
    String nombre="";
    String precio="";
    String impt="";

    String cnatidadp;
    String imporp;
    String preciop;
    String nombrep;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());

        //llama a la actividad dlientedialogo.xml
        LayoutInflater infrater = getActivity().getLayoutInflater();
        View view = infrater.inflate(R.layout.dialogo_confirmacion, null);
        builder.setView(view);
        ImageButton cargar = (ImageButton) view.findViewById(R.id.aceptarCompra);
        Button agregar=(Button)view.findViewById(R.id.agregarMas);

        Bundle mBundle = new Bundle();
        mBundle = getArguments();
        cantidad= mBundle.getString("cantidad", "");
        nombre= mBundle.getString("nombreProd", "");
        precio=mBundle.getString("precio", "");
        impt=mBundle.getString("imporp", "");

        cnatidadp = mBundle.getString("cnatidadp","");
        imporp = mBundle.getString("imporp","");
        preciop=mBundle.getString("preciop","");
        nombrep = mBundle.getString("nombrep","");

        //Toast.makeText(getActivity(),cnatidadp,Toast.LENGTH_SHORT).show();

        TextView cant=(TextView)view.findViewById(R.id.total);
        TextView compraactual=(TextView)view.findViewById(R.id.compraactual);
        if(imporp.equals("")){
            cant.setText(String.valueOf(Integer.parseInt(cantidad)*Integer.parseInt(precio)));
        }else{
            String con=imporp+"\n"+String.valueOf(Integer.parseInt(cantidad)*Integer.parseInt(precio));
            String monto[]=con.split("\n");
            int totalc=0;
            for (int i=0;i<monto.length;i++){
//
                //Toast.makeText(getContext(),monto[i],Toast.LENGTH_SHORT).show();
                if(monto[i].equals("")){
                    monto[i]="0";
                }
            }
            for (int i=0;i<monto.length;i++){

                if(Integer.parseInt(monto[i])!=0){
                    totalc=totalc+Integer.parseInt(monto[i]);
                }
            }
            cant.setText(String.valueOf(totalc));
        }
        compraactual.setText("SU COMPRA ACTUAL ES: \n"+nombre);
        final TextView nombrecli=(TextView)view.findViewById(R.id.nombre);
        final TextView nit=(TextView)view.findViewById(R.id.nit);





        cargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), FacturaActicity.class);
                intent.putExtra("nombreCliente",nombrecli.getText().toString());
                intent.putExtra("nitCliente",nit.getText().toString());
                intent.putExtra("cantidad",cnatidadp+"\n"+cantidad);
                intent.putExtra("precio",preciop+"\n"+precio);
                intent.putExtra("nombrep",nombrep+"\n"+nombre);
                intent.putExtra("importe",imporp+"\n"+String.valueOf(Integer.parseInt(cantidad)*Integer.parseInt(precio)));

                intent.putExtra("cnatidadp",cnatidadp);
                intent.putExtra("imporp",imporp);
                intent.putExtra("preciop",preciop);
                getActivity().startActivity(intent);
                getActivity().finish();
                dismiss();
            }
        });
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ProductoActivity.class);

                intent.putExtra("cantidad",cnatidadp+"\n\n"+cantidad);
                intent.putExtra("precio",preciop+"\n\n"+precio);
                intent.putExtra("nombrep",nombrep+"\n"+nombre);
                intent.putExtra("importe",imporp+"\n\n"+String.valueOf(Integer.parseInt(cantidad)*Integer.parseInt(precio)));

                getActivity().startActivity(intent);
                getActivity().finish();
                dismiss();
            }
        });
        return builder.create();
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (onDismissListener != null) {
            onDismissListener.onDismiss(dialog);
        }
    }

    public boolean isVolverCargar() {
        return volverCargar;
    }
}
