package co.edu.unab.amazonappexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText email,pass;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final SharedPreferences LocalStorage= getSharedPreferences(getString(R.string.nameStorage),MODE_PRIVATE);
        Boolean status= LocalStorage.getBoolean(getString(R.string.strStatus),false);
        String user= LocalStorage.getString(getString(R.string.user),"unk");
        if(status){
            Intent in = new Intent(LoginActivity.this,ListadoActivity.class);
            startActivity(in);
        }
        email= findViewById(R.id.editEmail);
        pass= findViewById(R.id.editPass);
        login= findViewById(R.id.btnLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email.getText().toString().equalsIgnoreCase(getString(R.string.userName)) || pass.getText().toString().equalsIgnoreCase(getString(R.string.passDefault))){
                SharedPreferences.Editor edit= LocalStorage.edit();
                edit.putBoolean(getString(R.string.strStatus),true);
                edit.putString(getString(R.string.user),email.getText().toString());
                edit.apply();
                    Intent in = new Intent(LoginActivity.this,ListadoActivity.class);
                    startActivity(in);
                }else {
                    Toast.makeText(LoginActivity.this, "Por favor llene los datos ", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }




}
