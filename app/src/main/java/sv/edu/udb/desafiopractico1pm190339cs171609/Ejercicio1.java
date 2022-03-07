package sv.edu.udb.desafiopractico1pm190339cs171609;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Ejercicio1 extends AppCompatActivity implements View.OnClickListener{

    Button btnVolver1;
    int id = 0;
    Usuario u;
    controladorUsuario conUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio1);

        btnVolver1 = (Button)findViewById(R.id.btnVolver1);

        btnVolver1.setOnClickListener(this);

        Bundle b = getIntent().getExtras();
        id = b.getInt("Id");
        conUsuario = new controladorUsuario(this);
        u = conUsuario.getUsuarioId(id);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnVolver1:
                Intent i = new Intent( Ejercicio1.this, Menu.class);
                i.putExtra("Id", id);
                startActivity(i);
                finish();
                break;
        }
    }
}