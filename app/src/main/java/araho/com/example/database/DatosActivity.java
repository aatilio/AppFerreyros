package araho.com.example.database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;

import java.util.ArrayList;

import araho.com.example.database.Adaptadores.ListaMaquinariassAdapter;
import araho.com.example.database.db.DbMaquinarias;
import araho.com.example.database.entidades.Maquinarias;

public class DatosActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    SearchView txtBuscar;
    RecyclerView listaMaquinarias;
    ArrayList<Maquinarias> listaArrayMaquinaria;
    ListaMaquinariassAdapter adapter;
    ImageButton btnAnadir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        txtBuscar = findViewById(R. id.txtBuscar);
        btnAnadir = (ImageButton) findViewById(R.id.btnNuevo);

        listaMaquinarias = findViewById(R.id.listaContactos);
        listaMaquinarias.setLayoutManager(new LinearLayoutManager(this));

        DbMaquinarias dbMaquinarias = new DbMaquinarias( DatosActivity.this);

        listaArrayMaquinaria = new ArrayList<>();

        adapter = new ListaMaquinariassAdapter(dbMaquinarias.mostrarMaquinarias());
        listaMaquinarias.setAdapter(adapter);

        //boton para poder registrar un nuevo registro
        btnAnadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nuevoRegistro();
            }
        });

        //al momento de colocar un texto lo esmpiese a leer
        txtBuscar.setOnQueryTextListener(this);
    }

/*
    //esta funcion solo es para que el menu se vea
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }
    //esta funcion es para
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuNuevo:
                nuevoRegistro();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

    //para poder pasar de una ventana a otra
    private void nuevoRegistro(){
        Intent intent = new Intent(this, NuevoActivity.class);
        startActivity(intent);
    }

    public void atras(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    //otra forma de llamar a un boton con un metodo sencillo
    /*public void  nuevoDato(View v){
        Intent intent = new Intent( this, NuevoActivity.class);
        startActivity(intent);
    }*/

    //al mometo de escribir en el campo lo este buscando en tiempo real
    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    //@RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onQueryTextChange(String s) {
        adapter.filtrado(s);
        return false;
    }


}