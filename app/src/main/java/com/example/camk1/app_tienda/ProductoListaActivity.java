package com.example.camk1.app_tienda;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.camk1.app_tienda.Clases.Metodos;
import com.example.camk1.app_tienda.Clases.Producto;
import com.example.camk1.app_tienda.Clases.ProductoListaAdapter;
import com.example.camk1.app_tienda.Clases.SinConexionFragment;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by hola on 27/11/2016.
 */

public class ProductoListaActivity extends AppCompatActivity {
    List<Producto> producto = new ArrayList<>();
    //RecyclerView
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    private Metodos metodos;
    private String categoria;
    //cargar lista
    private ProgressDialog circular;
    int c = 0;
    private DatabaseReference myDataBase = FirebaseDatabase.getInstance().getReference();

    //Variables
    private String keyProd;
    private String nombreProd;

    String cnatidad="";
    String nombrep="";
    String impor="";
    String precio="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_producto);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //nombre producto
        TextView tituloCategoria=(TextView)findViewById(R.id.nombreProducto);
        circular = ProgressDialog.show(this, "Cargando", "Aguarde un momento...", true);
        circular.setProgress(R.color.colorPrimary);
        Intent intent = getIntent();
        categoria=intent.getStringExtra("Categoria");
        cnatidad = intent.getStringExtra("cantidadp");
        nombrep = intent.getStringExtra("nombrep");
        impor = intent.getStringExtra("importep");
        precio=intent.getStringExtra("precio");

        tituloCategoria.setText(categoria);
    }

    public void usarRecycleView(){
        recycler = (RecyclerView) findViewById(R.id.my_recycler_view);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(getBaseContext());
        recycler.setLayoutManager(lManager);

        // Crear un nuevo adaptador
        adapter = new ProductoListaAdapter(producto);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(getBaseContext()));
    }

    @Override
    protected void onStart() {
        super.onStart();
        DatabaseReference categoriaProducto = myDataBase.child(categoria);
        producto.clear();
        if (metodos.compruebaConexion(getApplicationContext())){
        categoriaProducto.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Producto productoAgregar=new Producto("vacio","vacio","vacio","","","","");
                if (dataSnapshot.hasChildren()) {
                    keyProd = nombreProd= "";
                    keyProd = dataSnapshot.getKey();
                    Map<String, Object> dataChild = (Map<String, Object>) dataSnapshot.getValue();
                    nombreProd = dataChild.get("Nombre").toString();
                    productoAgregar = new Producto(categoria,keyProd,nombreProd,cnatidad,nombrep,impor,precio);
                    c=1;
                }
                if (!producto.contains(productoAgregar) && !productoAgregar.getKey().equals("vacio")){
                    producto.add(productoAgregar);
                }
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
