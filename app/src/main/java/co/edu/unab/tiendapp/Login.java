package co.edu.unab.tiendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
EditText usuario;
EditText password;
Button btnIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        asociarElementos();
        //Parametros (nombre, seguridad) se recomienda privado
        //No olvidar crear el archivo que guarda los datos, en Strings
        final SharedPreferences misPreferencias= getSharedPreferences(getString(R.string.misDatos),MODE_PRIVATE );

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(usuario.getText().toString().equals(getString(R.string.user1)) &&
                password.getText().toString().equals(getString(R.string.pass1))){
                    //un objeto que me va a permitir grabar informacion
                    SharedPreferences.Editor miEditor= misPreferencias.edit();
                    //a ese archivo creele una varibale logueado y guarde el valor true
                    miEditor.putBoolean("logueado", true);
                    //quiero guardar el usuario
                    miEditor.putString("usuario", usuario.getText().toString());
                    miEditor.apply();

                    Toast.makeText(getApplicationContext(), "Datos correctos", Toast.LENGTH_LONG).show();
                    Intent in= new Intent(Login.this, ListadoActivity.class);
                    startActivity(in);

                }
                else{
                    Toast.makeText(getApplicationContext(), "Datos incorrectos", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void asociarElementos(){
        usuario=findViewById(R.id.et_usuario);
        password=findViewById(R.id.et_password);
        btnIngresar=findViewById(R.id.btn_ingresar);
    }

}
