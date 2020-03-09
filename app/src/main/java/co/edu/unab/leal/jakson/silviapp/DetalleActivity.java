package co.edu.unab.leal.jakson.silviapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.widget.TextView;

public class DetalleActivity extends AppCompatActivity {

    private TextView textViewPrecio;
    private TextView textViewNombre;
    private TextView textViewDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        Bundle datosB = getIntent().getExtras();
        Producto prodObj = (Producto) datosB.getSerializable("objeto");

        this.asociarProductos();
        textViewNombre.setText(prodObj.getNombre());
        textViewPrecio.setText("$" + prodObj.getPrecio() + "USD");
        textViewDescripcion.setText(prodObj.getDescripcion());

    }

    private void asociarProductos() {

        textViewDescripcion = findViewById(R.id.textView_descripcion);
        textViewNombre = findViewById(R.id.textView_nombre);
        textViewPrecio = findViewById(R.id.textView_precio);

    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/

}
