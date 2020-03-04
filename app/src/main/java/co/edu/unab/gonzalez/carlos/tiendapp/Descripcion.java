package co.edu.unab.gonzalez.carlos.tiendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Descripcion extends AppCompatActivity {
    private TextView name;
    private TextView des;
    private TextView precio;
    private Button volver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion);
        Bundle misdatos = getIntent().getExtras();
        Producto elprod =(Producto) misdatos.getSerializable("prod");

        this.asociar();

        name.setText(elprod.getNombre());
        des.setText(elprod.getDescripcion());
        precio.setText("$"+ elprod.getPrecio());

    }
    private void asociar(){
        name = findViewById(R.id.nombre);
        des = findViewById(R.id.descripcion);
        precio = findViewById(R.id.precio);
        volver = findViewById(R.id.btn_volver);
    }
    public void back(View element){
        Intent volver = new Intent(this, MainActivity.class);
        startActivity(volver);
    }
}
