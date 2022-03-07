package sv.edu.udb.desafiopractico1pm190339cs171609;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registrarse extends AppCompatActivity implements View.OnClickListener{

    EditText usuario, pass, nombre, apellido;
    Button registrar, cancelar;
    controladorUsuario conUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        usuario = (EditText)findViewById(R.id.txtUsuario);
        pass = (EditText)findViewById(R.id.txtPassword);
        nombre = (EditText)findViewById(R.id.txtNombre);
        apellido = (EditText)findViewById(R.id.txtApellido);

        registrar = (Button)findViewById(R.id.btnRegistrar);
        cancelar = (Button)findViewById(R.id.btnCancelar);
        registrar.setOnClickListener(this);
        cancelar.setOnClickListener(this);

        conUsuario = new controladorUsuario(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegistrar:
                Usuario u = new Usuario();
                u.setUsuario(usuario.getText().toString());
                u.setPassword(pass.getText().toString());
                u.setNombre(nombre.getText().toString());
                u.setApellido(apellido.getText().toString());

                if(!u.isNull()){
                    Toast.makeText(this, "ERROR: Campos vacios", Toast.LENGTH_LONG).show();
                }else if(conUsuario.insertarUsuario(u)) {
                    Toast.makeText(this, "Registrado con exito!!!", Toast.LENGTH_LONG).show();
                    Intent i2 = new Intent( Registrarse.this, MainActivity.class);
                    startActivity(i2);
                    finish();
                    break;
                }else {
                    Toast.makeText(this, "Error al registrar - ya existe el usuario", Toast.LENGTH_LONG).show();
                }

                break;

            case R.id.btnCancelar:
                Intent i = new Intent( Registrarse.this, MainActivity.class);
                startActivity(i);
                finish();
                break;
        }
    }
}