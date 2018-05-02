package com.example.camk1.app_tienda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button producto=(Button)findViewById(R.id.producto);
        Button contacto=(Button)findViewById(R.id.contacto);
        producto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent producto = new Intent(getBaseContext(), ProductoActivity.class);
                startActivity(producto);
            }
        });
        //
        contacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent contacto = new Intent(getBaseContext(), ContactoActivity.class);
                startActivity(contacto);
            }
        });
    }
}
