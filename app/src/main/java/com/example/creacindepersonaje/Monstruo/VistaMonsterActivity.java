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

public class VistaMonsterActivity extends AppCompatActivity {

    private TextView tvnombre, tvdescr, tvCA, tvArma, tvAccion, tvHP, tvVEL, tvDES, tvCON, tvFUE, tvCAR;
    private Button bt_eliminar, bt_editar;
    int id_mns;
    Monstruo m = new Monstruo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_monster);
        iniciarComponentes();
        iniciarEventos();
    }

    public void iniciarComponentes() {
        tvnombre = findViewById(R.id.tv_nombre);
        tvdescr = findViewById(R.id.tv_descr);
        tvCA = findViewById(R.id.tv_capoints);
        tvArma = findViewById(R.id.tv_arma);
        tvAccion = findViewById(R.id.tv_accion);
        tvHP = findViewById(R.id.tv_hpview);
        tvVEL = findViewById(R.id.tv_velview);
        tvDES = findViewById(R.id.tv_desview);
        tvCON = findViewById(R.id.tv_Conview);
        tvFUE = findViewById(R.id.tv_fueView);
        tvCAR = findViewById(R.id.tv_carismaView);

        bt_eliminar = findViewById(R.id.bt_eliminarMonster);
        bt_editar = findViewById(R.id.bt_editarMonster);
    }

    public void iniciarEventos() {
        try {
            Intent intent = this.getIntent();
            Bundle extra = intent.getExtras();
            int ID = extra.getInt("ID");
            m = MonsterController.findMonster(getApplicationContext(), ID);
            id_mns = m.getIdMonstruo();
            tvnombre.setText(m.getNameMonstruo());
            tvdescr.setText(m.getDescripcion());
            tvCA.setText("Armadura: " + m.getCA() + "");
            tvArma.setText("Arma: "+ m.getIdArma() + "");
            tvAccion.setText("Acción: "+m.getAction() + "");
            tvHP.setText("HP "+m.getHP() + "");
            tvVEL.setText("VEL "+m.getVEL()+" pies");
            tvDES.setText("DES "+m.getDES()+"");
            tvCON.setText("CON "+m.getCON()+"");
            tvFUE.setText("FUE "+m.getFUE()+"");
            tvCAR.setText("CAR "+m.getCAR()+"");

        } catch (Exception ex) {
            Toast.makeText(this, "Error " + ex, Toast.LENGTH_LONG).show();

        }

        bt_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id_mns = m.getIdMonstruo();
                Intent intent = new Intent(getApplicationContext(), DeleteMonster.class);
                intent.putExtra("IDm", id_mns);
                //Toast.makeText(getApplicationContext(), "acá pasa Id del monster a borrar" + id_mns, Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });

        bt_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id_mns = m.getIdMonstruo();
                Intent intent2 = new Intent(getApplicationContext(), EditMonster.class);
                intent2.putExtra("IDm", id_mns);
                startActivity(intent2);
            }
        });

    }


}