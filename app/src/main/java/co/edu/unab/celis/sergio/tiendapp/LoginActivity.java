package co.edu.unab.celis.sergio.tiendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText edtUsuario;
    private EditText edtPassw;
    private Button btnIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.asociarElementos();

        final SharedPreferences misPreferencias = getSharedPreferences(getString(R.string.misDatos), MODE_PRIVATE);

        Boolean logueado = misPreferencias.getBoolean("logueado", false);

        if(logueado){
            Intent intent = new Intent(LoginActivity.this,ListadoActivity.class);
            startActivity(intent);
            finish();
        }

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtUsuario.getText().toString().equals(getString(R.string.usuario))&& edtPassw.getText().toString().equals(getString(R.string.pass))){

                    SharedPreferences.Editor miEditor = misPreferencias.edit();
                    miEditor.putBoolean("logueado", true);
                    miEditor.putString("usuario",edtUsuario.getText().toString());
                    miEditor.apply();

                    Intent intent = new Intent(LoginActivity.this,ListadoActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(LoginActivity.this,"Datos Incorrectos",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void asociarElementos(){
        edtUsuario = findViewById(R.id.edtUsuario);
        edtPassw = findViewById(R.id.edtContra);
        btnIngresar = findViewById(R.id.btnIngresar);
    }
}
