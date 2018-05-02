package com.example.camk1.app_tienda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

/**
 * Created by hola on 27/11/2016.
 */

public class FacturaActicity extends AppCompatActivity {
    String nombreCli="";
    String nitCli="";
    String cnatidad="";
    String nombrep="";
    String impor="";
    String precio="";

    String cnatidadp="";
    String imporp="";
    String preciop="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factura);

        Button aceptar=(Button)findViewById(R.id.aceptar);
        TextView fecha=(TextView)findViewById(R.id.fecha);
        Date d = new Date();
        CharSequence s  = DateFormat.format("MMMM d, yyyy ", d.getTime());

        TextView nombreCliente=(TextView)findViewById(R.id.nombreC);
        TextView nitcliente=(TextView)findViewById(R.id.nitC);
        TextView cantidad=(TextView)findViewById(R.id.cantidad);
        TextView descripcion=(TextView)findViewById(R.id.descripcion);
        TextView unit=(TextView)findViewById(R.id.unit);
        TextView importe=(TextView)findViewById(R.id.importe);
        TextView total=(TextView)findViewById(R.id.total);
        Intent intent = getIntent();
        nombreCli = intent.getStringExtra("nombreCliente");
        nitCli = intent.getStringExtra("nitCliente");
        cnatidad = intent.getStringExtra("cantidad");
        nombrep = intent.getStringExtra("nombrep");
        impor = intent.getStringExtra("importe");
        precio=intent.getStringExtra("precio");
        cnatidadp= intent.getStringExtra("cnatidadp");
        imporp = intent.getStringExtra("imporp");
        preciop=intent.getStringExtra("preciop");

        nombreCliente.setText(nombreCli);
        nitcliente.setText(nitCli);
        cantidad.setText(cnatidad);
        descripcion.setText(nombrep);
        unit.setText(precio);

        importe.setText(impor);
        fecha.setText(String.valueOf(s));
        String monto[]=impor.split("\n");
        int totalc=0;
        for (int i=0;i<monto.length;i++){
//
            if(monto[i].equals("")){
               monto[i]="0";
            }
        }
        for (int i=0;i<monto.length;i++){

            if(Integer.parseInt(monto[i])!=0){
                totalc=totalc+Integer.parseInt(monto[i]);
            }
        }
        total.setText(String.valueOf(totalc));
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent producto = new Intent(getBaseContext(), MainActivity.class);
                finish();
                startActivity(producto);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
