package co.edu.unab.vasquez.nodier.tiendapp.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import co.edu.unab.vasquez.nodier.tiendapp.R;

public class LoginActivity extends AppCompatActivity {

    EditText edtUsuario;
    EditText edtPass;
    Button btnIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        asociarElementos();

        //no volver a solicitar login --- 1
        //Permite almacenar datos con variable de tipo primitivas
        final SharedPreferences misPreferencias = getSharedPreferences(getString(R.string.misDatos), MODE_PRIVATE);

        //no volver a solicitar login --- 4
        Boolean logueado = misPreferencias.getBoolean("logueado",false);
        if(logueado){
            Intent in = new Intent(LoginActivity.this, ListadoActivity.class);
            startActivity(in);
            finish(); //Si le da atrás en la app... no va a poder mostrar esta actividad.
        }

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtUsuario.getText().toString().equals(getString(R.string.usuario))&&
                        edtPass.getText().toString().equals(getString(R.string.pass))){

                    //no volver a solicitar login --- 2
                    SharedPreferences.Editor miEditor = misPreferencias.edit();
                    miEditor.putBoolean("logueado", true);
                    miEditor.putString("usuario",edtUsuario.getText().toString());
                    miEditor.apply();

                    Toast.makeText(getApplicationContext(),"Datos correctos" ,Toast.LENGTH_LONG).show();
                    Intent in = new Intent (LoginActivity.this,ListadoActivity.class);
                    startActivity(in);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"Datos incorrectos" ,Toast.LENGTH_LONG).show();
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
