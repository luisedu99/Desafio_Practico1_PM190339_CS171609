package sv.edu.udb.desafiopractico1pm190339cs171609;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Ejercicio2 extends AppCompatActivity implements View.OnClickListener {

    Button btnVolver2;
    int id = 0;
    Usuario u;
    controladorUsuario conUsuario;

    private LinearLayout layoutt;
    private Button btnIniciar;
    private Button btnVotar;
    private Button btnReiniciar;
    private TextView valorResultado;
    private EditText valorCantidad;
    private EditText valorSecuencia;
    int cantidadParticipantes = 0;
    String secuenciaVotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio2);

        btnVolver2 = (Button)findViewById(R.id.btnVolver2);

        btnVolver2.setOnClickListener(this);

        Bundle b = getIntent().getExtras();
        id = b.getInt("Id");
        conUsuario = new controladorUsuario(this);
        u = conUsuario.getUsuarioId(id);


        btnReiniciar = findViewById(R.id.btn_volver);
        layoutt = findViewById(R.id.lei);
        btnIniciar = findViewById(R.id.btn_iniciar);
        btnVotar = findViewById(R.id.btn_votar);
        valorResultado = findViewById(R.id.text_resultados);
        valorCantidad = findViewById(R.id.valor_cantidad);
        valorSecuencia = findViewById(R.id.valor_secuencia);
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                iniciar();
            }
        });

        btnVotar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                secuenciaVotos = valorSecuencia.getText().toString();
                cantidadParticipantes = Integer.parseInt(valorCantidad.getText().toString());
                votacion(secuenciaVotos, cantidadParticipantes);
            }
        });

        btnReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnIniciar.setVisibility(View.VISIBLE);
                btnVotar.setVisibility(View.INVISIBLE);
                valorSecuencia.setVisibility(View.INVISIBLE);

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnVolver2:
                Intent i = new Intent( Ejercicio2.this, Menu.class);
                i.putExtra("Id", id);
                startActivity(i);
                finish();
                break;
        }
    }

    public void iniciar() {
        valorSecuencia.setVisibility(View.VISIBLE);
        btnVotar.setVisibility(View.VISIBLE);

    }

    public void votacion(String secuencia, int cantidad) {
        btnIniciar.setVisibility(View.GONE);
        btnVotar.setVisibility(View.GONE);
        String subsecuencia[] = secuencia.split(",");
        int cantidadSubsecuencia = subsecuencia.length;
        int[] votos = new int[cantidad];
        valorResultado.setVisibility(View.VISIBLE);
        for (int i = 0; i < cantidadSubsecuencia; i++) {
            String valorSubsecuencia = subsecuencia[i];
            for (int j = 0; j < votos.length; j++) {
                int j2 = j + 1;
                if (valorSubsecuencia.equals(String.valueOf(j2))) {

                    votos[j] = votos[j] + 1;
                }

            }

        }

        valorResultado.setVisibility(View.VISIBLE);
        valorResultado.setText("*** Resultados ***\n");
        for(int i=0; i<votos.length;i++){
            double porcentaje=0;
            porcentaje = Math.round( (votos[i]*100)/cantidadSubsecuencia);
            //porcentaje = (votos[i]*100)/cantidadSubsecuencia;

            TextView texto_resultados = new TextView(Ejercicio2.this);
            texto_resultados.setText("\nCandidato " + (i+1) + " obtuvo " + String.valueOf(votos[i]) + " votos (" + porcentaje + "%)\n\n");
            layoutt.addView(texto_resultados);
        }
    }
}