package com.example.creacindepersonaje.Monstruo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.creacindepersonaje.Controller.MonsterController;
import com.example.creacindepersonaje.Modelos.Monstruo;
import com.example.creacindepersonaje.R;

public class DeleteMonster extends AppCompatActivity {
    private Button bt_confirm, bt_cancel;
    private TextView tv_nameMons;

    int idM;
    Monstruo m = new Monstruo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_monster);
        iniciarComponentes();
        iniciarEventos();
    }

    private void iniciarActividad(Class clase) {
        Intent intent = new Intent(this, clase);
        startActivity(intent);
    }

    private void iniciarComponentes() {
        bt_confirm = findViewById(R.id.bt_removeConfirmar);
        bt_cancel = findViewById(R.id.bt_removeCancel);
        tv_nameMons = findViewById(R.id.tv_nombreMns);
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


        bt_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MonsterController.RemoveMonster(getApplicationContext(), m.getIdMonstruo())) {
                    Toast.makeText(getApplicationContext(), "Eliminado con éxito", Toast.LENGTH_LONG).show();
                    iniciarActividad(VistaAllMonsters.class);
                } else {
                    Toast.makeText(getApplicationContext(), "No se eliminó", Toast.LENGTH_LONG).show();
                }
            }
        });

        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarActividad(VistaAllMonsters.class);
            }
        });

    }
}