package com.example.camk1.app_tienda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText usuario;
    private EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button ingresa=(Button)findViewById(R.id.ingresar);

        usuario=(EditText) findViewById(R.id.txtusuario);
        password=(EditText) findViewById(R.id.txtpassword);
        ingresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(usuario.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                    Intent inicio = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(inicio);
                }else{
                    Toast.makeText(getApplicationContext(),"Usuario o contraseña no valido",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
