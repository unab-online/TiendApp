package co.edu.unab.amazonappexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Producto strResult= (Producto) getIntent().getSerializableExtra("datos");
        TextView textView= findViewById(R.id.textdetail);
        textView.setText(strResult.toString());
    }
}
