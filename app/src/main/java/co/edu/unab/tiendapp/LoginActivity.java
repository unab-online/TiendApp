package co.edu.unab.tiendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText edtUsuario;
    EditText edtPass;
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
            public void onClick(View view) {
                if(edtUsuario.getText().toString().equals(getString(R.string.usuario))&&
                edtPass.getText().toString().equals(getString(R.string.pass))){

                    SharedPreferences.Editor miEditor = misPreferencias.edit();
                    miEditor.putBoolean("logueado", true);
                    miEditor.putString("usuario", edtUsuario.getText().toString());
                    miEditor.apply();

                    Toast.makeText(LoginActivity.this, "Datos correctos", Toast.LENGTH_LONG).show();
                    Intent in = new Intent(LoginActivity.this, ListadoActivity.class);
                    startActivity(in);
                }else{
                    Toast.makeText(LoginActivity.this, "Datos incorrectos", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void asociarElementos(){
        edtUsuario = findViewById(R.id.edt_usuario);
        edtPass = findViewById(R.id.edt_pass);
        btnIngresar = findViewById(R.id.btn_ingresar);
    }
}
