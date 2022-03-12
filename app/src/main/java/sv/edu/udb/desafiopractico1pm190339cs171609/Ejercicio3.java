package sv.edu.udb.desafiopractico1pm190339cs171609;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Ejercicio3 extends AppCompatActivity implements View.OnClickListener{

    EditText txtNombre1,txtNombre2, txtNombre3, txtCargo1, txtCargo2, txtCargo3, txtHoras1, txtHoras2, txtHoras3;
    TextView resultNombre1, resultNombre2, resultNombre3, resultDescISS1, resultDescISS2, resultDescISS3, resultDescAFP1, resultDescAFP2, resultDescAFP3, resultDescRENTA1, resultDescRENTA2, resultDescRENTA3, resultSueldo1, resultSueldo2, resultSueldo3;
    Button btnVolver3, btnCalcular3;
    int id = 0;
    Usuario u;
    controladorUsuario conUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio3);

        txtNombre1 = (EditText)findViewById(R.id.txtNombre1);
        txtNombre2 = (EditText)findViewById(R.id.txtNombre2);
        txtNombre3 = (EditText)findViewById(R.id.txtNombre3);
        txtCargo1 = (EditText)findViewById(R.id.txtCargo1);
        txtCargo2 = (EditText)findViewById(R.id.txtCargo2);
        txtCargo3 = (EditText)findViewById(R.id.txtCargo3);
        txtHoras1 = (EditText)findViewById(R.id.txtHoras1);
        txtHoras2 = (EditText)findViewById(R.id.txtHoras2);
        txtHoras3 = (EditText)findViewById(R.id.txtHoras3);

        resultNombre1 = (TextView)findViewById(R.id.resultNombre1);
        resultNombre2 = (TextView)findViewById(R.id.resultNombre2);
        resultNombre3 = (TextView)findViewById(R.id.resultNombre3);

        resultDescISS1 = (TextView)findViewById(R.id.resultDescISSS1);
        resultDescISS2 = (TextView)findViewById(R.id.resultDescISSS2);
        resultDescISS3 = (TextView)findViewById(R.id.resultDescISSS3);

        resultDescAFP1 = (TextView)findViewById(R.id.resultDescAFP1);
        resultDescAFP2 = (TextView)findViewById(R.id.resultDescAFP2);
        resultDescAFP3 = (TextView)findViewById(R.id.resultDescAFP3);

        resultDescRENTA1 = (TextView)findViewById(R.id.resultDescRENTA1);
        resultDescRENTA2 = (TextView)findViewById(R.id.resultDescRENTA2);
        resultDescRENTA3 = (TextView)findViewById(R.id.resultDescRENTA3);

        resultSueldo1 = (TextView)findViewById(R.id.resultSueldo1);
        resultSueldo2 = (TextView)findViewById(R.id.resultSueldo2);
        resultSueldo3 = (TextView)findViewById(R.id.resultSueldo3);

        btnCalcular3 = (Button)findViewById(R.id.btnCalcular3);
        btnVolver3 = (Button)findViewById(R.id.btnVolver3);

        btnCalcular3.setOnClickListener(this);
        btnVolver3.setOnClickListener(this);

        Bundle b = getIntent().getExtras();
        id = b.getInt("Id");
        conUsuario = new controladorUsuario(this);
        u = conUsuario.getUsuarioId(id);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnVolver3:
                Intent i = new Intent( Ejercicio3.this, Menu.class);
                i.putExtra("Id", id);
                startActivity(i);
                finish();
                break;

            case R.id.btnCalcular3:
                calcular();
                break;
        }
    }

    public void calcular(){

        //Declarando variables
        Double horas1 = Double.parseDouble(txtHoras1.getText().toString());
        Double horas2 = Double.parseDouble(txtHoras2.getText().toString());
        Double horas3 = Double.parseDouble(txtHoras3.getText().toString());

        //Validando campos vacios
        if(horas1 <= 0 || horas2 <= 0 || horas3 <= 0 ){
            Toast.makeText(this, "ERROR: Las horas no pueden ser 0 o menores", Toast.LENGTH_LONG).show();
        }else{
            //Todas son menores
            if (horas1 <= 160 && horas2 <= 160 && horas3 <= 160) {

                //Sueldo base
                Double sueldo1 = horas1 * 9.75;
                Double sueldo2 = horas2 * 9.75;
                Double sueldo3 = horas3 * 9.75;

                //Calculo de descuentos
                Double restaISS1 = sueldo1 * 0.0525;
                Double restaISS2 = sueldo2 * 0.0525;
                Double restaISS3 = sueldo3 * 0.0525;

                Double restaAFP1 = sueldo1 * 0.0688;
                Double restaAFP2 = sueldo2 * 0.0688;
                Double restaAFP3 = sueldo3 * 0.0688;

                Double restaRenta1 = sueldo1 * 0.10;
                Double restaRenta2 = sueldo2 * 0.10;
                Double restaRenta3 = sueldo3 * 0.10;

                //Calculo de sueldo liquido
                Double sueldoLiquido1 = sueldo1 - (restaISS1 + restaAFP1 + restaRenta1);
                Double sueldoLiquido2 = sueldo2 - (restaISS2 + restaAFP2 + restaRenta2);
                Double sueldoLiquido3 = sueldo3 - (restaISS3 + restaAFP3 + restaRenta3);

                //Mostrando resultados
                resultNombre1.setText("" + txtNombre1.getText().toString());
                resultNombre2.setText("" + txtNombre2.getText().toString());
                resultNombre3.setText("" + txtNombre3.getText().toString());

                resultDescISS1.setText(" $" + Math.round(restaISS1 * 100) / 100.0);
                resultDescISS2.setText(" $" + Math.round(restaISS2 * 100) / 100.0);
                resultDescISS3.setText(" $" + Math.round(restaISS3 * 100) / 100.0);

                resultDescAFP1.setText(" $" + Math.round(restaAFP1 * 100) / 100.0);
                resultDescAFP2.setText(" $" + Math.round(restaAFP2 * 100) / 100.0);
                resultDescAFP3.setText(" $" + Math.round(restaAFP3 * 100) / 100.0);

                resultDescRENTA1.setText(" $" + Math.round(restaRenta1 * 100) / 100.0);
                resultDescRENTA2.setText(" $" + Math.round(restaRenta2 * 100) / 100.0);
                resultDescRENTA3.setText(" $" + Math.round(restaRenta3 * 100) / 100.0);

                resultSueldo1.setText(" $" + Math.round(sueldoLiquido1 * 100) / 100.0);
                resultSueldo2.setText(" $" + Math.round(sueldoLiquido2 * 100) / 100.0);
                resultSueldo3.setText(" $" + Math.round(sueldoLiquido3 * 100) / 100.0);



            }
            //Todas las horas son mayores
            else if(horas1 >= 160 && horas2 >= 160 && horas3 >= 160){
                //Calculo de horas mayores
                Double horasPagoEsp1 = horas1 - 160;
                Double horasPagoEsp2 = horas2 - 160;
                Double horasPagoEsp3 = horas3 - 160;

                //Suelda de horas mayores a 160
                Double sueldoM1 = horasPagoEsp1 * 11.50;
                Double sueldoM2 = horasPagoEsp2 * 11.50;
                Double sueldoM3 = horasPagoEsp3 * 11.50;

                //Sueldo de horas normales
                Double sueldoN1 = 160 * 9.75;
                Double sueldoN2 = 160 * 9.75;
                Double sueldoN3 = 160 * 9.75;

                //Sueldo base
                Double sueldo1 = sueldoM1 + sueldoN1;
                Double sueldo2 = sueldoM2 + sueldoN2;
                Double sueldo3 = sueldoM3 + sueldoN3;

                //Calculo de descuentos
                Double restaISS1 = sueldo1 * 0.0525;
                Double restaISS2 = sueldo2 * 0.0525;
                Double restaISS3 = sueldo3 * 0.0525;

                Double restaAFP1 = sueldo1 * 0.0688;
                Double restaAFP2 = sueldo2 * 0.0688;
                Double restaAFP3 = sueldo3 * 0.0688;

                Double restaRenta1 = sueldo1 * 0.10;
                Double restaRenta2 = sueldo2 * 0.10;
                Double restaRenta3 = sueldo3 * 0.10;

                //Calculo de sueldo liquido
                Double sueldoLiquido1 = sueldo1 - (restaISS1 + restaAFP1 + restaRenta1);
                Double sueldoLiquido2 = sueldo2 - (restaISS2 + restaAFP2 + restaRenta2);
                Double sueldoLiquido3 = sueldo3 - (restaISS3 + restaAFP3 + restaRenta3);

                //Mostrando resultados
                resultNombre1.setText("" + txtNombre1.getText().toString());
                resultNombre2.setText("" + txtNombre2.getText().toString());
                resultNombre3.setText("" + txtNombre3.getText().toString());

                resultDescISS1.setText(" $" + Math.round(restaISS1 * 100) / 100.0);
                resultDescISS2.setText(" $" + Math.round(restaISS2 * 100) / 100.0);
                resultDescISS3.setText(" $" + Math.round(restaISS3 * 100) / 100.0);

                resultDescAFP1.setText(" $" + Math.round(restaAFP1 * 100) / 100.0);
                resultDescAFP2.setText(" $" + Math.round(restaAFP2 * 100) / 100.0);
                resultDescAFP3.setText(" $" + Math.round(restaAFP3 * 100) / 100.0);

                resultDescRENTA1.setText(" $" + Math.round(restaRenta1 * 100) / 100.0);
                resultDescRENTA2.setText(" $" + Math.round(restaRenta2 * 100) / 100.0);
                resultDescRENTA3.setText(" $" + Math.round(restaRenta3 * 100) / 100.0);

                resultSueldo1.setText(" $" + Math.round(sueldoLiquido1 * 100) / 100.0);
                resultSueldo2.setText(" $" + Math.round(sueldoLiquido2 * 100) / 100.0);
                resultSueldo3.setText(" $" + Math.round(sueldoLiquido3 * 100) / 100.0);
            }

            //Solo las horas 1 son menores
            else if(horas1 <= 160 && horas2 >= 160 && horas3 >= 160){
                //Calculo de horas mayores
                //Double horasPagoEsp1 = horas1 - 160;
                Double horasPagoEsp2 = horas2 - 160;
                Double horasPagoEsp3 = horas3 - 160;

                //Suelda de horas mayores a 160
                //Double sueldoM1 = horasPagoEsp1 * 11.50;
                Double sueldoM2 = horasPagoEsp2 * 11.50;
                Double sueldoM3 = horasPagoEsp3 * 11.50;

                //Sueldo de horas normales
                //Double sueldoN1 = 160 * 9.75;
                Double sueldoN2 = 160 * 9.75;
                Double sueldoN3 = 160 * 9.75;

                //Sueldo base
                Double sueldo1 = horas1 * 9.75;
                Double sueldo2 = sueldoM2 + sueldoN2;
                Double sueldo3 = sueldoM3 + sueldoN3;

                //Calculo de descuentos
                Double restaISS1 = sueldo1 * 0.0525;
                Double restaISS2 = sueldo2 * 0.0525;
                Double restaISS3 = sueldo3 * 0.0525;

                Double restaAFP1 = sueldo1 * 0.0688;
                Double restaAFP2 = sueldo2 * 0.0688;
                Double restaAFP3 = sueldo3 * 0.0688;

                Double restaRenta1 = sueldo1 * 0.10;
                Double restaRenta2 = sueldo2 * 0.10;
                Double restaRenta3 = sueldo3 * 0.10;

                //Calculo de sueldo liquido
                Double sueldoLiquido1 = sueldo1 - (restaISS1 + restaAFP1 + restaRenta1);
                Double sueldoLiquido2 = sueldo2 - (restaISS2 + restaAFP2 + restaRenta2);
                Double sueldoLiquido3 = sueldo3 - (restaISS3 + restaAFP3 + restaRenta3);

                //Mostrando resultados
                resultNombre1.setText("" + txtNombre1.getText().toString());
                resultNombre2.setText("" + txtNombre2.getText().toString());
                resultNombre3.setText("" + txtNombre3.getText().toString());

                resultDescISS1.setText(" $" + Math.round(restaISS1 * 100) / 100.0);
                resultDescISS2.setText(" $" + Math.round(restaISS2 * 100) / 100.0);
                resultDescISS3.setText(" $" + Math.round(restaISS3 * 100) / 100.0);

                resultDescAFP1.setText(" $" + Math.round(restaAFP1 * 100) / 100.0);
                resultDescAFP2.setText(" $" + Math.round(restaAFP2 * 100) / 100.0);
                resultDescAFP3.setText(" $" + Math.round(restaAFP3 * 100) / 100.0);

                resultDescRENTA1.setText(" $" + Math.round(restaRenta1 * 100) / 100.0);
                resultDescRENTA2.setText(" $" + Math.round(restaRenta2 * 100) / 100.0);
                resultDescRENTA3.setText(" $" + Math.round(restaRenta3 * 100) / 100.0);

                resultSueldo1.setText(" $" + Math.round(sueldoLiquido1 * 100) / 100.0);
                resultSueldo2.setText(" $" + Math.round(sueldoLiquido2 * 100) / 100.0);
                resultSueldo3.setText(" $" + Math.round(sueldoLiquido3 * 100) / 100.0);
            }

            //Horas 1 y horas 2 son menores
            else if(horas1 <= 160 && horas2 <= 160 && horas3 >= 160){
                //Calculo de horas mayores
                //Double horasPagoEsp1 = horas1 - 160;
                //Double horasPagoEsp2 = horas2 - 160;
                Double horasPagoEsp3 = horas3 - 160;

                //Suelda de horas mayores a 160
                //Double sueldoM1 = horasPagoEsp1 * 11.50;
                //Double sueldoM2 = horasPagoEsp2 * 11.50;
                Double sueldoM3 = horasPagoEsp3 * 11.50;

                //Sueldo de horas normales
                //Double sueldoN1 = 160 * 9.75;
                //Double sueldoN2 = 160 * 9.75;
                Double sueldoN3 = 160 * 9.75;

                //Sueldo base
                Double sueldo1 = horas1 * 9.75;
                Double sueldo2 = horas2 * 9.75;
                Double sueldo3 = sueldoM3 + sueldoN3;

                //Calculo de descuentos
                Double restaISS1 = sueldo1 * 0.0525;
                Double restaISS2 = sueldo2 * 0.0525;
                Double restaISS3 = sueldo3 * 0.0525;

                Double restaAFP1 = sueldo1 * 0.0688;
                Double restaAFP2 = sueldo2 * 0.0688;
                Double restaAFP3 = sueldo3 * 0.0688;

                Double restaRenta1 = sueldo1 * 0.10;
                Double restaRenta2 = sueldo2 * 0.10;
                Double restaRenta3 = sueldo3 * 0.10;

                //Calculo de sueldo liquido
                Double sueldoLiquido1 = sueldo1 - (restaISS1 + restaAFP1 + restaRenta1);
                Double sueldoLiquido2 = sueldo2 - (restaISS2 + restaAFP2 + restaRenta2);
                Double sueldoLiquido3 = sueldo3 - (restaISS3 + restaAFP3 + restaRenta3);

                //Mostrando resultados
                resultNombre1.setText("" + txtNombre1.getText().toString());
                resultNombre2.setText("" + txtNombre2.getText().toString());
                resultNombre3.setText("" + txtNombre3.getText().toString());

                resultDescISS1.setText(" $" + Math.round(restaISS1 * 100) / 100.0);
                resultDescISS2.setText(" $" + Math.round(restaISS2 * 100) / 100.0);
                resultDescISS3.setText(" $" + Math.round(restaISS3 * 100) / 100.0);

                resultDescAFP1.setText(" $" + Math.round(restaAFP1 * 100) / 100.0);
                resultDescAFP2.setText(" $" + Math.round(restaAFP2 * 100) / 100.0);
                resultDescAFP3.setText(" $" + Math.round(restaAFP3 * 100) / 100.0);

                resultDescRENTA1.setText(" $" + Math.round(restaRenta1 * 100) / 100.0);
                resultDescRENTA2.setText(" $" + Math.round(restaRenta2 * 100) / 100.0);
                resultDescRENTA3.setText(" $" + Math.round(restaRenta3 * 100) / 100.0);

                resultSueldo1.setText(" $" + Math.round(sueldoLiquido1 * 100) / 100.0);
                resultSueldo2.setText(" $" + Math.round(sueldoLiquido2 * 100) / 100.0);
                resultSueldo3.setText(" $" + Math.round(sueldoLiquido3 * 100) / 100.0);
            }

            //Horas 1 y horas 3 son menores
            else if(horas1 <= 160 && horas2 >= 160 && horas3 <= 160){
                //Calculo de horas mayores
                //Double horasPagoEsp1 = horas1 - 160;
                Double horasPagoEsp2 = horas2 - 160;
                //Double horasPagoEsp3 = horas3 - 160;

                //Suelda de horas mayores a 160
                //Double sueldoM1 = horasPagoEsp1 * 11.50;
                Double sueldoM2 = horasPagoEsp2 * 11.50;
                //Double sueldoM3 = horasPagoEsp3 * 11.50;

                //Sueldo de horas normales
                //Double sueldoN1 = 160 * 9.75;
                Double sueldoN2 = 160 * 9.75;
                // Double sueldoN3 = 160 * 9.75;

                //Sueldo base
                Double sueldo1 = horas1 * 9.75;
                Double sueldo2 = sueldoM2 + sueldoN2;
                Double sueldo3 = horas3 * 9.75;

                //Calculo de descuentos
                Double restaISS1 = sueldo1 * 0.0525;
                Double restaISS2 = sueldo2 * 0.0525;
                Double restaISS3 = sueldo3 * 0.0525;

                Double restaAFP1 = sueldo1 * 0.0688;
                Double restaAFP2 = sueldo2 * 0.0688;
                Double restaAFP3 = sueldo3 * 0.0688;

                Double restaRenta1 = sueldo1 * 0.10;
                Double restaRenta2 = sueldo2 * 0.10;
                Double restaRenta3 = sueldo3 * 0.10;

                //Calculo de sueldo liquido
                Double sueldoLiquido1 = sueldo1 - (restaISS1 + restaAFP1 + restaRenta1);
                Double sueldoLiquido2 = sueldo2 - (restaISS2 + restaAFP2 + restaRenta2);
                Double sueldoLiquido3 = sueldo3 - (restaISS3 + restaAFP3 + restaRenta3);

                //Mostrando resultados
                resultNombre1.setText("" + txtNombre1.getText().toString());
                resultNombre2.setText("" + txtNombre2.getText().toString());
                resultNombre3.setText("" + txtNombre3.getText().toString());

                resultDescISS1.setText(" $" + Math.round(restaISS1 * 100) / 100.0);
                resultDescISS2.setText(" $" + Math.round(restaISS2 * 100) / 100.0);
                resultDescISS3.setText(" $" + Math.round(restaISS3 * 100) / 100.0);

                resultDescAFP1.setText(" $" + Math.round(restaAFP1 * 100) / 100.0);
                resultDescAFP2.setText(" $" + Math.round(restaAFP2 * 100) / 100.0);
                resultDescAFP3.setText(" $" + Math.round(restaAFP3 * 100) / 100.0);

                resultDescRENTA1.setText(" $" + Math.round(restaRenta1 * 100) / 100.0);
                resultDescRENTA2.setText(" $" + Math.round(restaRenta2 * 100) / 100.0);
                resultDescRENTA3.setText(" $" + Math.round(restaRenta3 * 100) / 100.0);

                resultSueldo1.setText(" $" + Math.round(sueldoLiquido1 * 100) / 100.0);
                resultSueldo2.setText(" $" + Math.round(sueldoLiquido2 * 100) / 100.0);
                resultSueldo3.setText(" $" + Math.round(sueldoLiquido3 * 100) / 100.0);
            }

            //Horas 2 y horas 3 son menores
            else if(horas1 >= 160 && horas2 <= 160 && horas3 <= 160){
                //Calculo de horas mayores
                Double horasPagoEsp1 = horas1 - 160;
                //Double horasPagoEsp2 = horas2 - 160;
                //Double horasPagoEsp3 = horas3 - 160;

                //Suelda de horas mayores a 160
                Double sueldoM1 = horasPagoEsp1 * 11.50;
                //Double sueldoM2 = horasPagoEsp2 * 11.50;
                //Double sueldoM3 = horasPagoEsp3 * 11.50;

                //Sueldo de horas normales
                Double sueldoN1 = 160 * 9.75;
                //Double sueldoN2 = 160 * 9.75;
                //Double sueldoN3 = 160 * 9.75;

                //Sueldo base
                Double sueldo1 = sueldoM1 + sueldoN1;
                Double sueldo2 = horas2 * 9.75;
                Double sueldo3 = horas3 * 9.75;

                //Calculo de descuentos
                Double restaISS1 = sueldo1 * 0.0525;
                Double restaISS2 = sueldo2 * 0.0525;
                Double restaISS3 = sueldo3 * 0.0525;

                Double restaAFP1 = sueldo1 * 0.0688;
                Double restaAFP2 = sueldo2 * 0.0688;
                Double restaAFP3 = sueldo3 * 0.0688;

                Double restaRenta1 = sueldo1 * 0.10;
                Double restaRenta2 = sueldo2 * 0.10;
                Double restaRenta3 = sueldo3 * 0.10;

                //Calculo de sueldo liquido
                Double sueldoLiquido1 = sueldo1 - (restaISS1 + restaAFP1 + restaRenta1);
                Double sueldoLiquido2 = sueldo2 - (restaISS2 + restaAFP2 + restaRenta2);
                Double sueldoLiquido3 = sueldo3 - (restaISS3 + restaAFP3 + restaRenta3);

                //Mostrando resultados
                resultNombre1.setText("" + txtNombre1.getText().toString());
                resultNombre2.setText("" + txtNombre2.getText().toString());
                resultNombre3.setText("" + txtNombre3.getText().toString());

                resultDescISS1.setText(" $" + Math.round(restaISS1 * 100) / 100.0);
                resultDescISS2.setText(" $" + Math.round(restaISS2 * 100) / 100.0);
                resultDescISS3.setText(" $" + Math.round(restaISS3 * 100) / 100.0);

                resultDescAFP1.setText(" $" + Math.round(restaAFP1 * 100) / 100.0);
                resultDescAFP2.setText(" $" + Math.round(restaAFP2 * 100) / 100.0);
                resultDescAFP3.setText(" $" + Math.round(restaAFP3 * 100) / 100.0);

                resultDescRENTA1.setText(" $" + Math.round(restaRenta1 * 100) / 100.0);
                resultDescRENTA2.setText(" $" + Math.round(restaRenta2 * 100) / 100.0);
                resultDescRENTA3.setText(" $" + Math.round(restaRenta3 * 100) / 100.0);

                resultSueldo1.setText(" $" + Math.round(sueldoLiquido1 * 100) / 100.0);
                resultSueldo2.setText(" $" + Math.round(sueldoLiquido2 * 100) / 100.0);
                resultSueldo3.setText(" $" + Math.round(sueldoLiquido3 * 100) / 100.0);
            }
            //Solo horas 2 es menor
            else if(horas1 >= 160 && horas2 <= 160 && horas3 >= 160){
                //Calculo de horas mayores
                Double horasPagoEsp1 = horas1 - 160;
                //Double horasPagoEsp2 = horas2 - 160;
                Double horasPagoEsp3 = horas3 - 160;

                //Suelda de horas mayores a 160
                Double sueldoM1 = horasPagoEsp1 * 11.50;
                //Double sueldoM2 = horasPagoEsp2 * 11.50;
                Double sueldoM3 = horasPagoEsp3 * 11.50;

                //Sueldo de horas normales
                Double sueldoN1 = 160 * 9.75;
                //Double sueldoN2 = 160 * 9.75;
                Double sueldoN3 = 160 * 9.75;

                //Sueldo base
                Double sueldo1 = sueldoM1 + sueldoN1;
                Double sueldo2 = horas2 * 9.75;
                Double sueldo3 = sueldoM3 + sueldoN3;

                //Calculo de descuentos
                Double restaISS1 = sueldo1 * 0.0525;
                Double restaISS2 = sueldo2 * 0.0525;
                Double restaISS3 = sueldo3 * 0.0525;

                Double restaAFP1 = sueldo1 * 0.0688;
                Double restaAFP2 = sueldo2 * 0.0688;
                Double restaAFP3 = sueldo3 * 0.0688;

                Double restaRenta1 = sueldo1 * 0.10;
                Double restaRenta2 = sueldo2 * 0.10;
                Double restaRenta3 = sueldo3 * 0.10;

                //Calculo de sueldo liquido
                Double sueldoLiquido1 = sueldo1 - (restaISS1 + restaAFP1 + restaRenta1);
                Double sueldoLiquido2 = sueldo2 - (restaISS2 + restaAFP2 + restaRenta2);
                Double sueldoLiquido3 = sueldo3 - (restaISS3 + restaAFP3 + restaRenta3);

                //Mostrando resultados
                resultNombre1.setText("" + txtNombre1.getText().toString());
                resultNombre2.setText("" + txtNombre2.getText().toString());
                resultNombre3.setText("" + txtNombre3.getText().toString());

                resultDescISS1.setText(" $" + Math.round(restaISS1 * 100) / 100.0);
                resultDescISS2.setText(" $" + Math.round(restaISS2 * 100) / 100.0);
                resultDescISS3.setText(" $" + Math.round(restaISS3 * 100) / 100.0);

                resultDescAFP1.setText(" $" + Math.round(restaAFP1 * 100) / 100.0);
                resultDescAFP2.setText(" $" + Math.round(restaAFP2 * 100) / 100.0);
                resultDescAFP3.setText(" $" + Math.round(restaAFP3 * 100) / 100.0);

                resultDescRENTA1.setText(" $" + Math.round(restaRenta1 * 100) / 100.0);
                resultDescRENTA2.setText(" $" + Math.round(restaRenta2 * 100) / 100.0);
                resultDescRENTA3.setText(" $" + Math.round(restaRenta3 * 100) / 100.0);

                resultSueldo1.setText(" $" + Math.round(sueldoLiquido1 * 100) / 100.0);
                resultSueldo2.setText(" $" + Math.round(sueldoLiquido2 * 100) / 100.0);
                resultSueldo3.setText(" $" + Math.round(sueldoLiquido3 * 100) / 100.0);
            }

            //Solo horas 3 es menor
            else if(horas1 >= 160 && horas2 >= 160 && horas3 <= 160){
                //Calculo de horas mayores
                Double horasPagoEsp1 = horas1 - 160;
                Double horasPagoEsp2 = horas2 - 160;
                //Double horasPagoEsp3 = horas3 - 160;

                //Suelda de horas mayores a 160
                Double sueldoM1 = horasPagoEsp1 * 11.50;
                Double sueldoM2 = horasPagoEsp2 * 11.50;
                //Double sueldoM3 = horasPagoEsp3 * 11.50;

                //Sueldo de horas normales
                Double sueldoN1 = 160 * 9.75;
                Double sueldoN2 = 160 * 9.75;
                //Double sueldoN3 = 160 * 9.75;

                //Sueldo base
                Double sueldo1 = sueldoM1 + sueldoN1;
                Double sueldo2 = sueldoM2 + sueldoN2;
                Double sueldo3 = horas3 * 9.75;

                //Calculo de descuentos
                Double restaISS1 = sueldo1 * 0.0525;
                Double restaISS2 = sueldo2 * 0.0525;
                Double restaISS3 = sueldo3 * 0.0525;

                Double restaAFP1 = sueldo1 * 0.0688;
                Double restaAFP2 = sueldo2 * 0.0688;
                Double restaAFP3 = sueldo3 * 0.0688;

                Double restaRenta1 = sueldo1 * 0.10;
                Double restaRenta2 = sueldo2 * 0.10;
                Double restaRenta3 = sueldo3 * 0.10;

                //Calculo de sueldo liquido
                Double sueldoLiquido1 = sueldo1 - (restaISS1 + restaAFP1 + restaRenta1);
                Double sueldoLiquido2 = sueldo2 - (restaISS2 + restaAFP2 + restaRenta2);
                Double sueldoLiquido3 = sueldo3 - (restaISS3 + restaAFP3 + restaRenta3);

                //Mostrando resultados
                resultNombre1.setText("" + txtNombre1.getText().toString());
                resultNombre2.setText("" + txtNombre2.getText().toString());
                resultNombre3.setText("" + txtNombre3.getText().toString());

                resultDescISS1.setText(" $" + Math.round(restaISS1 * 100) / 100.0);
                resultDescISS2.setText(" $" + Math.round(restaISS2 * 100) / 100.0);
                resultDescISS3.setText(" $" + Math.round(restaISS3 * 100) / 100.0);

                resultDescAFP1.setText(" $" + Math.round(restaAFP1 * 100) / 100.0);
                resultDescAFP2.setText(" $" + Math.round(restaAFP2 * 100) / 100.0);
                resultDescAFP3.setText(" $" + Math.round(restaAFP3 * 100) / 100.0);

                resultDescRENTA1.setText(" $" + Math.round(restaRenta1 * 100) / 100.0);
                resultDescRENTA2.setText(" $" + Math.round(restaRenta2 * 100) / 100.0);
                resultDescRENTA3.setText(" $" + Math.round(restaRenta3 * 100) / 100.0);

                resultSueldo1.setText(" $" + Math.round(sueldoLiquido1 * 100) / 100.0);
                resultSueldo2.setText(" $" + Math.round(sueldoLiquido2 * 100) / 100.0);
                resultSueldo3.setText(" $" + Math.round(sueldoLiquido3 * 100) / 100.0);
            }
        }
    }
}