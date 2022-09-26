package araho.com.example.database;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import araho.com.example.database.db.DbMaquinarias;
import araho.com.example.database.entidades.Maquinarias;

public class VerActivity extends AppCompatActivity {

    EditText txtCodigo, txtNombre, txtCapacidad, txtpeso;
    ImageButton btnEditar, btnEliminar, btnGuarda;
    //FloatingActionButton btnEditar, btnEliminar;
    TextView tvEmcavesado;

    Maquinarias maquinarias;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtCodigo = findViewById(R.id.txtCodigo);
        txtNombre = findViewById(R.id.txtNombre);
        txtCapacidad = findViewById(R.id.txtCapacidad);
        txtpeso = findViewById(R.id.txtPeso);

        btnEditar = findViewById(R.id.btnEditar);
        btnEliminar = findViewById(R.id.btnEliminar);
        btnGuarda = findViewById(R.id.btnGuardar);

        tvEmcavesado = findViewById(R.id.tvEmcabesado);

        //para validar diferentes tipos de variables que se puedan encontrar
        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        DbMaquinarias dbMaquinarias = new DbMaquinarias(VerActivity.this);
        maquinarias = dbMaquinarias.verMaquinaria(id);

        if(maquinarias != null){
            txtCodigo.setText(maquinarias.getCodigo());
            txtNombre.setText(maquinarias.getNombre());
            txtCapacidad.setText(maquinarias.getCapacidad());
            txtpeso.setText(maquinarias.getPeso());
            //para que el botton no se borre, solo se esconda
            btnGuarda.setVisibility(View.INVISIBLE);
            //para que no habra el teclado al momento de mostrar los registros
            txtCodigo.setInputType(InputType.TYPE_NULL);
            txtNombre.setInputType(InputType.TYPE_NULL);
            txtCapacidad.setInputType(InputType.TYPE_NULL);
            txtpeso.setInputType(InputType.TYPE_NULL);
        }

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerActivity.this, EditarActivity.class);
                intent.putExtra("ID", id);
                startActivity(intent);
                txtCodigo.requestFocus();
                String a = String.valueOf("Cargando...");
                tvEmcavesado.setText(a);
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder( VerActivity. this);
                builder.setMessage("¿Desea eliminar esta maquinaria?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if (dbMaquinarias.eliminarMaquinaria(id)){
                                    lista();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        }).show();
            }
        });
    }

    private  void  lista(){
        Intent intent = new Intent( this, DatosActivity.class);
        startActivity(intent);
    }

    public void atras(View view) {
        startActivity(new Intent(getApplicationContext(), DatosActivity.class));
        finish();
    }
}