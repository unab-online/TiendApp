package co.edu.unab.toloza.cesar.tiendapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import co.edu.unab.toloza.cesar.tiendapp.R;

public class LoginActivity extends AppCompatActivity {

    EditText editUser, editPass;
    Button buttonIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        asociarElementos();

        final SharedPreferences misPreferencias = getSharedPreferences(getString(R.string.preferencias), MODE_PRIVATE);

        Boolean logueado = misPreferencias.getBoolean("logueado", false);

        if(logueado){
            Intent in = new Intent(LoginActivity.this, ListadoActivity.class);
            startActivity(in);
            finish();
        }

        buttonIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editUser.getText().toString().equals(getString(R.string.user)) &&
                editPass.getText().toString().equals(getString(R.string.pass))){
                    SharedPreferences.Editor miEditor = misPreferencias.edit();
                    miEditor.putBoolean("logueado", true);
                    miEditor.putString("user", editUser.getText().toString());
                    miEditor.apply();

                    Toast.makeText(getApplicationContext(), "Datos Correctos",Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(LoginActivity.this, ListadoActivity.class);
                    startActivity(in);
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(), "Datos Incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private  void  asociarElementos(){
        editUser = findViewById(R.id.editText_user);
        editPass = findViewById(R.id.editText_password);
        buttonIngresar = findViewById(R.id.button_ingresar);
    }
}
