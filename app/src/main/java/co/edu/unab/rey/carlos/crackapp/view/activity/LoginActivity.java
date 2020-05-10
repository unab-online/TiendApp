package co.edu.unab.rey.carlos.crackapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import co.edu.unab.rey.carlos.crackapp.R;

public class LoginActivity extends AppCompatActivity {


    EditText edtUsuario;
    EditText edtPassword;
    Button btnIngresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        asociarElementos();

        final SharedPreferences misPreferencias = getSharedPreferences(getString(R.string.misDatos), MODE_PRIVATE);

        Boolean logueado = misPreferencias.getBoolean("logueado", false);
        if(logueado){
            Intent in = new Intent(LoginActivity.this, ListadoActivity.class);
            startActivity(in);
            finish();
        }


        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtUsuario.getText().toString().equals(getString(R.string.usuario)) && edtPassword.getText().toString().equals(getString(R.string.password))) {

                    SharedPreferences.Editor miEditor = misPreferencias.edit();
                    miEditor.putBoolean("logueado", true);
                    miEditor.putString("usuario", getString(R.string.usuario));
                    miEditor.apply();

                    Toast.makeText(getApplicationContext(), "Datos correctos", Toast.LENGTH_LONG).show();
                    Intent in = new Intent(getApplicationContext(), ListadoActivity.class);
                    startActivity(in);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Datos incorrectos", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void asociarElementos(){
        edtUsuario = findViewById(R.id.edt_usuario);
        edtPassword = findViewById(R.id.edt_password);
        btnIngresar = findViewById(R.id.bt_ingresar);
    }


}




