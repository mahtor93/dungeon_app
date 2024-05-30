package com.example.creacindepersonaje.Jugador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.creacindepersonaje.Controller.SpellController;
import com.example.creacindepersonaje.Modelos.Hechizo;
import com.example.creacindepersonaje.R;

import java.util.ArrayList;

public class createSpell extends AppCompatActivity {

    private EditText etDescripSpell, etNombreSpell,etLevelSpell,etDamageSpell,etCuraSpell,etShieldSpell;
    private Spinner spKindSpell;
    private ArrayList<String> kindSpell;
    private ArrayAdapter<String> kindSpellAdapter;
    private Button btnInscribir, btnCancelarSpell;
    private RadioButton rbDamage, rbShield, rbHeal;
    private RadioGroup rgOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_spell);
        iniciarComponentes();
        iniciarEventos();
    }

    private void iniciarComponentes(){
        etDescripSpell = findViewById(R.id.etDescripSpell);
        etNombreSpell = findViewById(R.id.etNombreSpell);
        etLevelSpell = findViewById(R.id.etLevelSpell);
        etLevelSpell.setText("0");
        etDamageSpell = findViewById(R.id.etDamageSpell);

        etCuraSpell = findViewById(R.id.etCuraSpell);

        etShieldSpell = findViewById(R.id.etShieldSpell);

        spKindSpell = findViewById(R.id.spKindSpell);

        btnInscribir = findViewById(R.id.btnInscribir);
        btnCancelarSpell =findViewById(R.id.btnCancelarSpell);
        rgOptions = findViewById(R.id.rgOptions);
        rbDamage = findViewById(R.id.rbDamage);
        rbShield = findViewById(R.id.rbShield);
        rbHeal = findViewById(R.id.rbHeal);
    }

    private void iniciarEventos(){

        kindSpell = new ArrayList<>();
        kindSpell.add(0,"Selecciona");
        for(int i = 0;i< Hechizo.tipoHechizo.tipoHechizo.length;i++){
            kindSpell.add(Hechizo.tipoHechizo.tipoHechizo[i]);
        }
        kindSpellAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, kindSpell);
        kindSpellAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spKindSpell.setAdapter(kindSpellAdapter);

        rgOptions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int wi = radioGroup.getCheckedRadioButtonId();
                if (wi == R.id.rbDamage) {

                    etDamageSpell.setEnabled(true);
                    etCuraSpell.setEnabled(false);
                    etCuraSpell.setText("0");
                    etShieldSpell.setEnabled(false);
                    etShieldSpell.setText("0");
                }
                if(wi == R.id.rbShield) {
                    etShieldSpell.setEnabled(true);
                    etCuraSpell.setEnabled(false);
                    etCuraSpell.setText("0");
                    etDamageSpell.setEnabled(false);
                    etDamageSpell.setText("0");
                }
                if(wi==R.id.rbHeal) {
                    etCuraSpell.setEnabled(true);
                    etDamageSpell.setEnabled(false);
                    etDamageSpell.setText("0");
                    etShieldSpell.setEnabled(false);
                    etShieldSpell.setText("0");
                }
            }
        });

        btnInscribir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String warning ="";
                int Code_Error =0;
                try {
                    if (etNombreSpell.getText().length() == 0) {
                        warning += "Debes poner un nombre al hechizo\n";
                        Code_Error += 1;

                    }else{
                        if (etNombreSpell.getText().length() > 30) {
                            warning += "El nombre del hechizo es demasiado largo\n";
                            Code_Error += 1;
                        }

                    }

                    if(spKindSpell.getSelectedItemPosition()==0){
                        warning+="Selecciona el tipo de hechizo";
                        Code_Error+=1;
                    }

                    if (etDescripSpell.getText().length() == 0) {
                        warning += "Debes poner una descripción\n";
                        Code_Error += 1;
                    }

                    if (Code_Error != 0) {
                        Toast.makeText(createSpell.this, "Error: \n" + warning+" Cod.Error"+Code_Error, Toast.LENGTH_SHORT).show();
                    }
                    if(SpellController.findHechizoByName(getApplicationContext(),etNombreSpell.getText().toString())!=-1){
                        Toast.makeText(createSpell.this, "El nombre ya está en uso\n", Toast.LENGTH_SHORT).show();
                    }else{
                        if (Code_Error == 0) {
                            Hechizo sp = new Hechizo();
                            sp.setNombre(etNombreSpell.getText().toString());

                            sp.setTipoHechizo(spKindSpell.getSelectedItem().toString());
                            sp.setNivelHechizo(Integer.parseInt(etLevelSpell.getText().toString()));


                            sp.setDamage(Integer.parseInt(etDamageSpell.getText().toString()));
                            sp.setShield(Integer.parseInt(etShieldSpell.getText().toString()));
                            sp.setHeal(Integer.parseInt(etCuraSpell.getText().toString()));

                            sp.setDescripcion(etDescripSpell.getText().toString());

                            if (SpellController.createHechizo(getApplicationContext(), sp)) {
                                Intent intent = new Intent(getApplicationContext(), Spellbook.class);
                                startActivity(intent);
                            }
                        }
                    }


                }catch (Exception ex) {
                    Toast.makeText(createSpell.this, "ERROR Exception:" + ex, Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancelarSpell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Spellbook.class);
                startActivity(intent);
            }
        });

    }
}