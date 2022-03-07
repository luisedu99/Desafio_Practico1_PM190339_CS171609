package sv.edu.udb.desafiopractico1pm190339cs171609;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText user, pass;
    Button btnEntrar, btnRegistrarse;
    controladorUsuario conUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = (EditText)findViewById(R.id.txtUsuario);
        pass = (EditText)findViewById(R.id.txtPassword);
        btnEntrar = (Button)findViewById(R.id.btnEntrar);
        btnRegistrarse = (Button)findViewById(R.id.btnRegistrarse);

        btnEntrar.setOnClickListener(this);
        btnRegistrarse.setOnClickListener(this);

        conUsuario = new controladorUsuario(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnEntrar:

                String u = user.getText().toString();
                String p = pass.getText().toString();

                if(u.equals("") && p.equals("")){

                    Toast.makeText(this, "ERROR: Los campos no pueden estar vacios.", Toast.LENGTH_LONG).show();

                }else if(conUsuario.login(u,p) == 1){

                    Usuario ux = conUsuario.getUsuario(u,p);
                    Toast.makeText(this, "Datos correctos", Toast.LENGTH_LONG).show();
                    Intent i2 = new Intent( MainActivity.this, Menu.class);
                    i2.putExtra("Id", ux.getId());
                    startActivity(i2);

                }else{

                    Toast.makeText(this, "ERROR: Datos erroneos.", Toast.LENGTH_LONG).show();

                }
                break;

            case R.id.btnRegistrarse:
                Intent i = new Intent( MainActivity.this,Registrarse.class);
                startActivity(i);
                break;
        }
    }
}