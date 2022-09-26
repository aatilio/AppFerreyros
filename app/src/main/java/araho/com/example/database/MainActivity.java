package araho.com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity  {

    private EditText etUser, etPassword;
    Button btnLoguin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUser = (EditText)findViewById(R.id.etUser);
        etPassword = (EditText)findViewById(R.id.etPassword);


        btnLoguin = (Button) findViewById(R.id.btnRegistrar);

        btnLoguin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //validarUsuario("http://127.0.0.1/sorian/validar_usuario.php");
                ingresar(view);
            }
        });
    }

    //este metodo es para hacer la demostracion de la validacion de inicio de sección en la App
    public void ingresar(View v){
        String user = etUser.getText().toString();
        String pass = etPassword.getText().toString();
        if ( user.equals("admin") && pass.equals("123.") && !user.isEmpty() && !pass.isEmpty()){
            Intent l = new Intent(this,DatosActivity.class);
            l.putExtra("use",user); startActivity(l);
            Toast.makeText(this ,"Entering...",Toast.LENGTH_SHORT).show(); //Toast.setGravity(Gravity.CENTER|);
        } else if(user.isEmpty() && pass.isEmpty()) {
            Toast.makeText(this, "Username and Password, can't be emtry", Toast.LENGTH_SHORT).show(); //Toast.setGravity(Gravity.CENTER|);
        } else if(user.isEmpty()) {
            Toast.makeText(this ,"Username can't be emtry",Toast.LENGTH_SHORT).show(); //Toast.setGravity(Gravity.CENTER|);
        } else if(pass.isEmpty()) {
            Toast.makeText(this ," Password can't be emtry",Toast.LENGTH_SHORT).show(); //Toast.setGravity(Gravity.CENTER|);
        } else {
            Toast.makeText(this ,"Username and/or Password, Incorrect",Toast.LENGTH_LONG).show(); //Toast.setGravity(Gravity.CENTER|);
        }
    }

    public void registrar(View view) {
        startActivity(new Intent(getApplicationContext(), RegistroActivity.class));
        finish();
    }

/*
    //base de datos SQLite
    public void ingresarDB(View v){
        DbHelper usuarios=new DbHelper(MainActivity.this);
        SQLiteDatabase sqLiteDatabase=usuarios.getWritableDatabase();

        //DbHelper dbHelper = new DbHelper(context);
        //SQLiteDatabase db = dbHelper.getWritableDatabase();

        String usuario=etUser.getText().toString();
        String password=etPassword.getText().toString();
        //Cursor fila = sqLiteDatabase.rawQuery("SELECT usuarios, password FROM usuarios WHERE usuario='" + usuario + "' AND password='" + password + "'");

    }
*/

/*
    //Metodo para poder consultar con base de datos MySQL localhost
    private void validarUsuario(String URL){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.isEmpty()){
                    Intent intent=new Intent(getApplicationContext(), DatosActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this ,"Username and/or Password, no existen",Toast.LENGTH_SHORT).show(); //Toast.setGravity(Gravity.CENTER|);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this , error.toString(),Toast.LENGTH_SHORT).show(); //Toast.setGravity(Gravity.CENTER|);
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros= new HashMap<String, String>();
                parametros.put("u",etUser.getText().toString());
                parametros.put("p",etPassword.getText().toString());
                return parametros;
            }
        };

        //Objetos
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest); //ayudar a prosesar todas las peticiones hechas desde la app
    }*/

}
