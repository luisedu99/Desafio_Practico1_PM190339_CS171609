package sv.edu.udb.desafiopractico1pm190339cs171609;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Menu extends AppCompatActivity implements View.OnClickListener{

    Button btnEjercicio1, btnEjercicio2, btnEjercicio3, btnSalir;
    TextView nombre;
    int id = 0;
    Usuario u;
    controladorUsuario conUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        nombre = (TextView)findViewById(R.id.txtViewUsuario);
        btnEjercicio1 = (Button)findViewById(R.id.btnEjercicio1);
        btnEjercicio2 = (Button)findViewById(R.id.btnEjercicio2);
        btnEjercicio3 = (Button)findViewById(R.id.btnEjercicio3);
        btnSalir = (Button)findViewById(R.id.btnSalir);

        btnEjercicio1.setOnClickListener(this);
        btnEjercicio2.setOnClickListener(this);
        btnEjercicio3.setOnClickListener(this);
        btnSalir.setOnClickListener(this);

        Bundle b = getIntent().getExtras();
        id = b.getInt("Id");
        conUsuario = new controladorUsuario(this);
        u = conUsuario.getUsuarioId(id);
        nombre.setText(u.getNombre() + " " + u.getApellido());

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnEjercicio1:
                Intent i1 = new Intent( Menu.this, Ejercicio1.class);
                i1.putExtra("Id", id);
                startActivity(i1);
                finish();

                break;

            case R.id.btnEjercicio2:
                Intent i2 = new Intent( Menu.this, Ejercicio2.class);
                i2.putExtra("Id", id);
                startActivity(i2);
                finish();

                break;

            case R.id.btnEjercicio3:
                Intent i3 = new Intent( Menu.this, Ejercicio3.class);
                i3.putExtra("Id", id);
                startActivity(i3);
                finish();

                break;
            case R.id.btnSalir:
                Intent i4 = new Intent( Menu.this, MainActivity.class);
                startActivity(i4);
                finish();
                break;
        }
    }
}