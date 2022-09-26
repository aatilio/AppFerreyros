package araho.com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import araho.com.example.database.db.DbMaquinarias;

public class NuevoActivity extends AppCompatActivity {

    EditText txtCodigo, txtNombre, txtCapacidad, txtpeso;
    Button btnGuarda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        txtCodigo = findViewById(R.id.txtCodigo);
        txtNombre = findViewById(R.id.txtNombre);
        txtCapacidad = findViewById(R.id.txtCapacidad);
        txtpeso = findViewById(R.id.txtPeso);

        btnGuarda = findViewById(R.id.btnGuarda);

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ( !txtCodigo.getText().toString().equals("") && !txtNombre.getText().toString().equals("") && !txtCapacidad.getText().toString().equals("") && !txtpeso.getText().toString().equals("")) {

                    DbMaquinarias dbMaquinarias = new DbMaquinarias(NuevoActivity.this);
                    long id = dbMaquinarias.insertarMaquinaria(txtCodigo.getText().toString(), txtNombre.getText().toString(), txtCapacidad.getText().toString(), txtpeso.getText().toString());

                    if (id > 0) {
                        Toast.makeText(NuevoActivity.this, "Maquinaria guardado con exito", Toast.LENGTH_LONG).show();
                        limpiar();
                        txtCodigo.requestFocus();
                    } else {
                        Toast.makeText(NuevoActivity.this, "Error al intentar guardar maquinaria", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(NuevoActivity.this, "Debe de llenar todos los campos obligatoriamente", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void limpiar() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtCapacidad.setText("");
        txtpeso.setText("");
    }

    public void atras(View view) {
        startActivity(new Intent(getApplicationContext(), DatosActivity.class));
        finish();
    }
}