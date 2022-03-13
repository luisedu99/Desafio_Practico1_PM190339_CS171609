package sv.edu.udb.desafiopractico1pm190339cs171609;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Ejercicio1 extends AppCompatActivity implements View.OnClickListener{

    Button btnVolver1;
    int id = 0;
    Usuario u;
    controladorUsuario conUsuario;

    private EditText valor_a;
    private EditText valor_b;
    private EditText valor_c;

    private Button btn_calcular;

    private TextView valor_x1;
    private TextView valor_x2;

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


        valor_x1 = findViewById(R.id.valorx1);
        valor_x2 = findViewById(R.id.valorx2);
        valor_a = findViewById(R.id.valora);
        valor_b = findViewById(R.id.valorb);
        valor_c = findViewById(R.id.valorc);
        btn_calcular = findViewById(R.id.btncalcular);

        btn_calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valor_x1.setText("X1 = " + ecuacion1(Double.parseDouble(valor_a.getText().toString()),Double.parseDouble(valor_b.getText().toString()),Double.parseDouble(valor_c.getText().toString()))+"");
                valor_x2.setText("X2 = " + ecuacion2(Double.parseDouble(valor_a.getText().toString()),Double.parseDouble(valor_b.getText().toString()),Double.parseDouble(valor_c.getText().toString()))+"");
            }
        });

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

    public double ecuacion1 (double a, double b, double c){
        double respuesta1=0;
        if(a!=0){
            respuesta1 = ( -b + Math.sqrt(b*b-4*a*c) ) / (2*a);
        }

        return respuesta1;

    }

    public double ecuacion2 (double a, double b, double c){
        double respuesta2=0;
        if(a!=0){
            respuesta2 = ( -b - Math.sqrt(b*b-4*a*c) ) / (2*a);
        }

        return respuesta2;

    }
}