package com.example.camk1.app_tienda;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.camk1.app_tienda.Clases.Metodos;
import com.example.camk1.app_tienda.Clases.Producto;
import com.example.camk1.app_tienda.Clases.ProductoListaAdapter;
import com.example.camk1.app_tienda.Clases.SinConexionFragment;
import com.example.camk1.app_tienda.Clases.TarjetaInicio;
import com.example.camk1.app_tienda.Clases.TarjetaInicioAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.security.AccessController.getContext;

/**
 * Created by hola on 27/11/2016.
 */

public class ProductoActivity extends AppCompatActivity {
    List<TarjetaInicio> tarjeta = new ArrayList<>();
    //RecyclerView
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    private Metodos metodos;
    private ProgressDialog circular;
    int c = 1;

    //Variables
    private String nombreProducto;
    private String imagenProducto;

    private DatabaseReference myDataBase = FirebaseDatabase.getInstance().getReference();


    String cnatidad="";
    String nombrep="";
    String impor="";
    String precio="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        circular = ProgressDialog.show(this, "Cargando", "Aguarde un momento...", true);
        circular.setProgress(R.color.colorPrimary);
        Intent intent = getIntent();
            cnatidad = intent.getStringExtra("cantidad");
            nombrep = intent.getStringExtra("nombrep");
            impor = intent.getStringExtra("importe");
            precio = intent.getStringExtra("precio");
        //Toast.makeText(getBaseContext(),cnatidad,Toast.LENGTH_SHORT).show();

    }
    public void usarRecycleView(){
        recycler = (RecyclerView) findViewById(R.id.my_recycler_producto);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(getBaseContext());
        recycler.setLayoutManager(lManager);

        // Crear un nuevo adaptador
        recycler.setLayoutManager(new GridLayoutManager(getBaseContext(), 2));
        adapter = new TarjetaInicioAdapter(tarjeta);
        recycler.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        tarjeta.clear();
        super.onStart();
        if (metodos.compruebaConexion(getApplicationContext())){
            myDataBase.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    if (dataSnapshot.hasChildren()) {
                        nombreProducto="";
                        imagenProducto="https://firebasestorage.googleapis.com/v0/b/tiendaelec-3e964.appspot.com/o/Motores%2FpruebaImagen.jpg?alt=media&token=0bf267e0-11d5-4ba7-bc54-7bcf6bfb05f0";
                        nombreProducto = dataSnapshot.getKey();
                        Map<String, Object> dataChild = (Map<String, Object>) dataSnapshot.getValue();
                        imagenProducto = dataChild.get("ImagenProducto").toString();
                        c=1;
                    } else
                        Toast.makeText(getApplicationContext(), "No se encurntran datos en la db", Toast.LENGTH_LONG).show();
                    tarjeta.add(new TarjetaInicio(nombreProducto,imagenProducto,cnatidad,nombrep,impor,precio));

                    usarRecycleView();

                    if (c == 1) {
                        circular.dismiss();
                        c = 0;
                    }
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            final SinConexionFragment dialogo = new SinConexionFragment();
            dialogo.show(fragmentManager, "tagAlerta");
            dialogo.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    if (dialogo.isVolverCargar()) {
                        onStart();
                    } else {
                        Intent refresh = new Intent(getBaseContext(), MainActivity.class);
                        startActivity(refresh);
                        finish();
                    }
                }
            });
        }
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
