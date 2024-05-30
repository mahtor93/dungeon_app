package com.example.creacindepersonaje.Monstruo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.creacindepersonaje.Controller.MonsterController;
import com.example.creacindepersonaje.Modelos.Monstruo;
import com.example.creacindepersonaje.R;

public class EditMonster extends AppCompatActivity {

    private Button bt_editOK, bt_editCancel;
    private TextView tv_nameMons;
    private EditText txb_nameEditMons, txb_descEditMons;

    int idM;
    Monstruo m = new Monstruo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_monster);
        iniciarComponentes();
        iniciarEventos();
    }
    private void iniciarActividad(Class clase) {
        Intent intent = new Intent(this, clase);
        startActivity(intent);
    }
    private void iniciarComponentes() {
        bt_editOK = findViewById(R.id.bt_EditMonsOK);
        bt_editCancel = findViewById(R.id.bt_CancelEditMons);
        tv_nameMons = findViewById(R.id.tv_nameMons_editview);
        txb_descEditMons = findViewById(R.id.txb_newDescMons);
        txb_nameEditMons = findViewById(R.id.txb_newNameMons);
    }
    private void iniciarEventos() {
        try {
            Intent intent = this.getIntent();
            Bundle datos = intent.getExtras();
            idM = datos.getInt("IDm");
            m = MonsterController.findMonster(getApplicationContext(), idM);
            tv_nameMons.setText(m.getNameMonstruo());
        } catch (Exception ex) {
            Toast.makeText(this, "Error " + ex, Toast.LENGTH_SHORT).show();
        }
        bt_editOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    boolean name = true;
                    if (txb_descEditMons.getText().toString().length() < 1 || txb_nameEditMons.getText().toString().length() < 1) {
                        Toast.makeText(EditMonster.this, "Ingresar campos", Toast.LENGTH_SHORT).show();
                    }
                    if (MonsterController.getIdPorNombre(getApplicationContext(),txb_nameEditMons.getText().toString())!=-1){
                        Toast.makeText(EditMonster.this, "El nombre ya está en uso", Toast.LENGTH_SHORT).show();
                        name = false;
                    }
                    if(name){
                        try {
                            String nameMons = txb_nameEditMons.getText().toString();
                            String descMons = txb_descEditMons.getText().toString();
                            m = MonsterController.findMonster(getApplicationContext(), idM);
                            MonsterController.editMonster(getApplicationContext(), m, nameMons, descMons);
                            Toast.makeText(getApplicationContext(), "Editado con éxito", Toast.LENGTH_LONG).show();
                            iniciarActividad(VistaAllMonsters.class);
                        } catch (Exception ex) {
                            Toast.makeText(EditMonster.this, "ERROR" + ex, Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (Exception ex){
                    Toast.makeText(EditMonster.this, "Error "+ex, Toast.LENGTH_SHORT).show();
                }
            }
        });
        bt_editCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "No editaste nada", Toast.LENGTH_LONG).show();
                iniciarActividad(VistaAllMonsters.class);
            }
        });
    }
}