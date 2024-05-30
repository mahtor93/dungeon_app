package com.example.creacindepersonaje.PJ;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.creacindepersonaje.Modelos.Personaje;
import com.example.creacindepersonaje.R;

import java.util.ArrayList;

public class Setear_Caracteristicas extends AppCompatActivity {

    private TextView tvCV1,tvCV2,tvCV3,tvCV4,tvCV5,tvCV6,tvSelectedValueSTR,tvSelectedValueINT,tvSelectedValueDES,tvSelectedValueSAB,tvSelectedValueCON,tvSelectedValueCAR;
    private Button btnLanzar,btnSiguiente;
    Personaje p = new Personaje();
    private Spinner sp_fuerza,sp_inteligencia,sp_destreza,sp_sabiduria,sp_constitucion,sp_carisma;
    private ArrayList<String> valores;
    private ArrayList<String> valoresReset;
    private ArrayAdapter adapterValoresFuerza;
    private ArrayAdapter adapterValoresIntelig;
    private ArrayAdapter adapterValoresDestre;
    private ArrayAdapter adapterValoresSabidu;
    private ArrayAdapter adapterValoresConstitu;
    private ArrayAdapter adapterValoresCarism;
    int lanzamientos =1;
    private Button btnReset;
    boolean dadoLanz = false;
    boolean fue = false;
    boolean des = false;
    boolean intel = false;
    boolean sabid = false;
    boolean carism = false;
    boolean consti = false;
    String error = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setear_caracteristicas);
        iniciarComponentes();
        iniciarEventos();
    }

    private void iniciarComponentes(){
        tvCV1 = findViewById(R.id.tvCV1);
        tvCV2 = findViewById(R.id.tvCV2);
        tvCV3 = findViewById(R.id.tvCV3);
        tvCV4 = findViewById(R.id.tvCV4);
        tvCV5 = findViewById(R.id.tvCV5);
        tvCV6 = findViewById(R.id.tvCV6);

        tvSelectedValueSTR = findViewById(R.id.tvSTR);
        tvSelectedValueINT = findViewById(R.id.tvINT);
        tvSelectedValueDES = findViewById(R.id.tvDES);
        tvSelectedValueSAB = findViewById(R.id.tvSAB);
        tvSelectedValueCON = findViewById(R.id.tvCON);
        tvSelectedValueCAR = findViewById(R.id.tvCAR);

        btnLanzar = findViewById(R.id.btnLanzar);

        sp_fuerza = findViewById(R.id.sp_fuerza);
        sp_inteligencia = findViewById(R.id.sp_intelig);
        sp_destreza = findViewById(R.id.sp_dest);
        sp_sabiduria = findViewById(R.id.sp_sabid);
        sp_constitucion = findViewById(R.id.sp_const);
        sp_carisma = findViewById(R.id.sp_carism);

        btnSiguiente = findViewById(R.id.btnSiguiente);
        btnReset = findViewById(R.id.btnReset);
        btnReset.setEnabled(false);
    }



    private void resetSpinners(){
        tvSelectedValueSTR.setText("");
        tvSelectedValueDES.setText("");
        tvSelectedValueCAR.setText("");
        tvSelectedValueCON.setText("");
        tvSelectedValueSAB.setText("");
        tvSelectedValueINT.setText("");
        sp_fuerza.setSelection(0);
        sp_fuerza.setEnabled(true);
        sp_carisma.setSelection(0);
        sp_carisma.setEnabled(true);
        sp_constitucion.setSelection(0);
        sp_constitucion.setEnabled(true);
        sp_inteligencia.setSelection(0);
        sp_inteligencia.setEnabled(true);
        sp_destreza.setSelection(0);
        sp_destreza.setEnabled(true);
        sp_sabiduria.setSelection(0);
        sp_sabiduria.setEnabled(true);
    }



    private void iniciarEventos(){

        Intent intent = this.getIntent();
        Bundle extra = intent.getExtras();

        String name = extra.getString("Nombre");
        String race = extra.getString("Raza");
        String variant = extra.getString("SubRaza");
        String cla = extra.getString("Clase");

        Context context = getApplicationContext();
        valores = new ArrayList<>();
        valoresReset = new ArrayList<>();

        btnLanzar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                resetSpinners();
                btnReset.setEnabled(true);
                dadoLanz = true;
                lanzamientos++;
                if(lanzamientos==4){
                    btnLanzar.setEnabled(false);
                    Toast.makeText(Setear_Caracteristicas.this, "No hay más intentos", Toast.LENGTH_LONG).show();
                }
                valores.clear();
                valoresReset.clear();
                int val1 = p.lanzarDadosCaracteristica();
                int val2 = p.lanzarDadosCaracteristica();
                int val3 = p.lanzarDadosCaracteristica();
                int val4 = p.lanzarDadosCaracteristica();
                int val5 = p.lanzarDadosCaracteristica();
                int val6 = p.lanzarDadosCaracteristica();
                tvCV1.setText(val1+" - ");
                tvCV2.setText(val2+" - ");
                tvCV3.setText(val3+" - ");
                tvCV4.setText(val4+" - ");
                tvCV5.setText(val5+" - ");
                tvCV6.setText(val6+"");

                valores.add(0,"Selecciona");
                valores.add(String.valueOf(val1));
                valores.add(String.valueOf(val2));
                valores.add(String.valueOf(val3));
                valores.add(String.valueOf(val4));
                valores.add(String.valueOf(val5));
                valores.add(String.valueOf(val6));

                for(int a =0;a<valores.size();a++){
                    valoresReset.add(valores.get(a));
                }



                adapterValoresFuerza = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, valores);
                adapterValoresFuerza.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp_fuerza.setAdapter(adapterValoresFuerza);
                adapterValoresIntelig = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, valores);
                adapterValoresIntelig.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp_inteligencia.setAdapter(adapterValoresIntelig);
                adapterValoresDestre = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, valores);
                adapterValoresDestre.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp_destreza.setAdapter(adapterValoresDestre);
                adapterValoresSabidu = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, valores);
                adapterValoresSabidu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp_sabiduria.setAdapter(adapterValoresSabidu);
                adapterValoresConstitu = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, valores);
                adapterValoresConstitu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp_constitucion.setAdapter(adapterValoresConstitu);
                adapterValoresCarism = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, valores);
                adapterValoresCarism.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp_carisma.setAdapter(adapterValoresCarism);


            }
        });

        sp_fuerza.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(sp_fuerza.getSelectedItemPosition()!=0) {
                    tvSelectedValueSTR.setText(sp_fuerza.getSelectedItem().toString());
                    valores.remove(sp_fuerza.getSelectedItem());
                    sp_fuerza.setEnabled(false);
                    fue = true;
                }
                else{

                    error+="\nAsigna un valor para Fuerza";
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sp_carisma.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(sp_carisma.getSelectedItemPosition()!=0) {
                    tvSelectedValueCAR.setText(sp_carisma.getSelectedItem().toString());
                    valores.remove(sp_carisma.getSelectedItem());
                    sp_carisma.setEnabled(false);
                    carism = true;
                }
                else{
                    error+="\nAsigna un valor para Carisma";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sp_constitucion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(sp_constitucion.getSelectedItemPosition()!=0) {
                    tvSelectedValueCON.setText(sp_constitucion.getSelectedItem().toString());
                    valores.remove(sp_constitucion.getSelectedItem());
                    sp_constitucion.setEnabled(false);
                    consti = true;
                }
                else{
                    error+="\nAsigna un valor para Constitución";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sp_inteligencia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(sp_inteligencia.getSelectedItemPosition()!=0) {
                    tvSelectedValueINT.setText(sp_inteligencia.getSelectedItem().toString());
                    valores.remove(sp_inteligencia.getSelectedItem());
                    sp_inteligencia.setEnabled(false);
                    intel = true;
                }
                else{
                    error+="\nAsigna un valor para Inteligencia";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sp_destreza.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(sp_destreza.getSelectedItemPosition()!=0) {
                    tvSelectedValueDES.setText(sp_destreza.getSelectedItem().toString());
                    valores.remove(sp_destreza.getSelectedItem());
                    sp_destreza.setEnabled(false);
                    des = true;
                }
                else{
                    error+="\nAsigna un valor para Destreza";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sp_sabiduria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(sp_sabiduria.getSelectedItemPosition()!=0) {
                    tvSelectedValueSAB.setText((sp_sabiduria.getSelectedItem().toString()));
                    valores.remove(sp_sabiduria.getSelectedItem());
                    sp_sabiduria.setEnabled(false);
                    sabid = true;
                }
                else{
                    error+="\nAsigna un valor para Sabiduría";
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valores.clear();
                for(int a =0;a<valoresReset.size();a++){
                    valores.add(valoresReset.get(a));
                }
                resetSpinners();
            }
        });

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(dadoLanz&&fue&&sabid&&intel&&des&&consti&&carism){
                    Intent intent = new Intent(Setear_Caracteristicas.this,CreatedPersonaje.class);
                    intent.putExtra("Nombre",name);
                    intent.putExtra("Raza",race);
                    intent.putExtra("Clase",cla);
                    intent.putExtra("Variante",variant);
                    intent.putExtra("STR",tvSelectedValueSTR.getText());
                    intent.putExtra("DES",tvSelectedValueDES.getText());
                    intent.putExtra("CON",tvSelectedValueCON.getText());
                    intent.putExtra("INT",tvSelectedValueINT.getText());
                    intent.putExtra("SAB",tvSelectedValueSAB.getText());
                    intent.putExtra("CAR",tvSelectedValueCAR.getText());
                    startActivity(intent);
                }else{
                    if(!dadoLanz){
                    error+="Lanza los dados";
                    }
                    Toast.makeText(Setear_Caracteristicas.this, error, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}