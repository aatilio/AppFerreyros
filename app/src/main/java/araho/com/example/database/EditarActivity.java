package araho.com.example.database;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import araho.com.example.database.db.DbMaquinarias;
import araho.com.example.database.entidades.Maquinarias;

public class EditarActivity extends AppCompatActivity {

    EditText txtCodigo, txtNombre, txtCapacidad, txtpeso;
    //Button btnGuarda;
    boolean correcto = false;
    ImageButton btnEditar, btnEliminar, btnGuarda;

    TextView tvEmcabesado;
    Maquinarias maquinaria;
    int id = 0;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtCodigo = findViewById(R.id.txtCodigo);
        txtNombre = findViewById(R.id.txtNombre);
        txtCapacidad = findViewById(R.id.txtCapacidad);
        txtpeso = findViewById(R.id.txtPeso);

        tvEmcabesado = findViewById(R.id.tvEmcabesado);
        //btnGuarda = findViewById(R.id.btnGuarda);

        btnGuarda = (ImageButton) findViewById(R.id.btnGuardar);
        btnEditar = findViewById(R.id.btnEditar);
        btnEliminar = findViewById(R.id.btnEliminar);

        btnEditar.setVisibility(View.INVISIBLE);
        btnEliminar.setVisibility(View.INVISIBLE);

        String a = String.valueOf("Modificar");
        tvEmcabesado.setText(a);

        //para poder pasar a la anterior actividad con los datos relacionados a su id
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        final DbMaquinarias dbClientes = new DbMaquinarias(EditarActivity.this);
        maquinaria = dbClientes.verMaquinaria(id);

        //donde coloca todos los datos dentro de los edid text
        if (maquinaria != null) {
            txtCodigo.setText(maquinaria.getCodigo());
            txtNombre.setText(maquinaria.getNombre());
            txtCapacidad.setText(maquinaria.getCapacidad());
            txtpeso.setText(maquinaria.getPeso());
        }

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( !txtCodigo.getText().toString().equals("") && !txtNombre.getText().toString().equals("") && !txtCapacidad.getText().toString().equals("") && !txtpeso.getText().toString().equals("")) {

                    correcto = dbClientes.editarMaquinaria(id, txtCodigo.getText().toString(), txtNombre.getText().toString(), txtCapacidad.getText().toString(), txtpeso.getText().toString());

                    if(correcto){
                        Toast.makeText(EditarActivity.this, "Maquinaria modificado con exito", Toast.LENGTH_LONG).show();
                        verRegistro();
                    } else {
                        Toast.makeText(EditarActivity.this, "Error!!! al modificar maquinaria", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(EditarActivity.this, "Deve de llenar todos los campos solicitados", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void verRegistro(){
        Intent intent = new Intent(this, VerActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }

    public void atras(View view) {
        verRegistro();
    }
}