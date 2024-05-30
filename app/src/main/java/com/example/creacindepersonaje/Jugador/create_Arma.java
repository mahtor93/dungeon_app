package com.example.creacindepersonaje.Jugador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.creacindepersonaje.Controller.ItemsController;
import com.example.creacindepersonaje.Modelos.Arma;
import com.example.creacindepersonaje.R;

import java.util.ArrayList;

public class create_Arma extends AppCompatActivity {

    private EditText etCreateWeapon_Nombre,etCreateWeapon_Damage,etDescriptionWeapon;
    private Spinner spCreateTipoWeapon,spCreateTipoDamage;
    private Button btnCreateWeapon,btnCancelCreateWeapon;

    private ArrayList<String> tipoWeapon;
    private ArrayAdapter<String> AdapterTipoWeapon;

    private ArrayList<String> tipoDamage;
    private ArrayAdapter<String> AdapterTipoDamage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_arma);
        iniciarComponentes();
        iniciarEventos();
    }

    private void iniciarComponentes(){
        etCreateWeapon_Damage = findViewById(R.id.etCreateWeapon_Damage);
        etCreateWeapon_Nombre = findViewById(R.id.etCreateWeapon_Nombre);
        etDescriptionWeapon = findViewById(R.id.etDescriptionWeapon);

        spCreateTipoDamage = findViewById(R.id.spCreateTipoDamage);
        spCreateTipoWeapon = findViewById(R.id.spCreateTipoWeapon);

        btnCancelCreateWeapon = findViewById(R.id.btnCancelCreateWeapon);
        btnCreateWeapon = findViewById(R.id.btnCreateWeapon);
    }

    private void iniciarEventos(){
        tipoDamage = new ArrayList<>();
        tipoDamage.add(0,"Selecciona");
        tipoWeapon = new ArrayList<>();
        tipoWeapon.add(0,"Selecciona");

        for(int i = 0; i< Arma.tipoArma.tipos.length;i++){
            tipoWeapon.add(Arma.tipoArma.tipos[i]);
        }
        for(int i = 0; i<Arma.tipoDamage.tiposDamage.length;i++){
            tipoDamage.add(Arma.tipoDamage.tiposDamage[i]);
        }

        AdapterTipoDamage = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,tipoDamage);
        AdapterTipoDamage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCreateTipoDamage.setAdapter(AdapterTipoDamage);

        AdapterTipoWeapon = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,tipoWeapon);
        AdapterTipoWeapon.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCreateTipoWeapon.setAdapter(AdapterTipoWeapon);

        btnCreateWeapon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Error = "";
                if(etCreateWeapon_Nombre.getText().length()==0){
                    Error += "Debes elegir un nombre\n";
                }
                if(etCreateWeapon_Damage.getText().length()==0){
                    Error+="Debes determinar el daño\n";
                }
                if(spCreateTipoDamage.getSelectedItemPosition()==0){
                    Error+="Debes determinar el tipo de daño\n";
                }
                if(spCreateTipoWeapon.getSelectedItemPosition()==0){
                    Error+="Debes determinar el tipo de arma\n";
                }
                if(etDescriptionWeapon.getText().length()==0){
                    Error+="Debes añadir una descripcion\n";
                }
                if(Error.length()==0){
                    try{
                        int id = ItemsController.getIdArmaByName(getApplicationContext(),etCreateWeapon_Damage.getText().toString());
                        if(id!=-1) {
                            Toast.makeText(create_Arma.this, "El nombre ya está en uso", Toast.LENGTH_SHORT).show();
                        }
                        else{
                        Arma w = new Arma();
                        w.setNombre(etCreateWeapon_Nombre.getText().toString());
                        w.setDamage(Integer.parseInt(etCreateWeapon_Damage.getText().toString()));
                        w.setTipoDamage(spCreateTipoDamage.getSelectedItem().toString());
                        w.setTipoArma(spCreateTipoWeapon.getSelectedItem().toString());
                        w.setDescripcion(etDescriptionWeapon.getText().toString());
                        ItemsController.createArma(getApplicationContext(),w);
                        }

                    }catch (Exception ex){
                        Toast.makeText(create_Arma.this, "Error "+ex, Toast.LENGTH_SHORT).show();
                    }
                    finally {
                        Intent i = new Intent(getApplicationContext(),Forja.class);
                        startActivity(i);
                    }

                }else{
                    Toast.makeText(create_Arma.this, "Errores :\n"+Error, Toast.LENGTH_SHORT).show();
                }
            }
        });



        btnCancelCreateWeapon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}