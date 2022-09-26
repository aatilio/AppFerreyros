package araho.com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import araho.com.example.database.db.DbUsuarios;

public class RegistroActivity extends AppCompatActivity {

    EditText etUsuario, etPassword;
    Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);


        etUsuario = findViewById(R.id.etUser);
        etPassword = findViewById(R.id.etPassword);


        btnRegistrar = findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!etUsuario.getText().toString().equals("") && !etPassword.getText().toString().equals("")) {

                    DbUsuarios dbUsuarios = new DbUsuarios(RegistroActivity.this);
                    long id = dbUsuarios.insertarUsuario(etUsuario.getText().toString(), etPassword.getText().toString());

                    if (id > 0) {
                        Toast.makeText(RegistroActivity.this, "Usuario registrado correctamente con exito", Toast.LENGTH_LONG).show();
                        limpiar();
                        verLoguin();
                        etUsuario.requestFocus();
                    } else {
                        Toast.makeText(RegistroActivity.this, "Error al registrar usuario", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(RegistroActivity.this, "Debe llenar los campos obligatoriamente", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void limpiar() {
        etUsuario.setText("");
        etPassword.setText("");
    }

    private void verLoguin(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void atras(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}