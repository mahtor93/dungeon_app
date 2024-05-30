package com.example.creacindepersonaje.Monstruo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.creacindepersonaje.BDConnection.DBConnection;
import com.example.creacindepersonaje.Controller.ItemsController;
import com.example.creacindepersonaje.Controller.MonsterController;
import com.example.creacindepersonaje.Modelos.Arma;
import com.example.creacindepersonaje.Modelos.Monstruo;
import com.example.creacindepersonaje.Monstruo.VistaAllMonsters;
import com.example.creacindepersonaje.R;

import java.util.ArrayList;

public class CreateAMonster extends AppCompatActivity {

    private EditText txb_nameMonster, txb_descMonster, txb_CA, txb_hp, txb_vel, txb_fue, txb_des, txb_con, txb_int, txb_sab, txb_car;
    private Button bt_crearMonstruo;
    private Spinner spArmaMonstruo, spAccionMonstruo;

    private ArrayList<Arma> weapons;
    private ArrayList<String> nameWeapons;
    private ArrayAdapter<String> adapterWeapons;

    private ArrayList<String> nameActions;
    private ArrayAdapter<String> adapterActions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_create_amonster);
            iniciarComponentes();
            iniciarEventos();
        } catch (Exception ex) {
            Toast.makeText(this, "Error " + ex, Toast.LENGTH_SHORT).show();
        }
    }

    private void iniciarComponentes() {
        txb_nameMonster = findViewById(R.id.txb_nameMonster);
        txb_descMonster = findViewById(R.id.txb_descMonster);
        spArmaMonstruo = findViewById(R.id.spArmaMonstruo);
        spAccionMonstruo = findViewById(R.id.spAccionMonstruo);
        txb_CA = findViewById(R.id.txb_CA);
        txb_hp = findViewById(R.id.txb_hp);
        txb_vel = findViewById(R.id.txb_vel);
        txb_fue = findViewById(R.id.txb_fue);
        txb_des = findViewById(R.id.txb_des);
        txb_con = findViewById(R.id.txb_con);
        txb_int = findViewById(R.id.txb_int);
        txb_sab = findViewById(R.id.txb_sab);
        txb_car = findViewById(R.id.txb_car);
        bt_crearMonstruo = findViewById(R.id.bt_crearMonstruo);
    }

    private void iniciarEventos() {
        nameActions = new ArrayList<>();
        nameWeapons = new ArrayList<>();
        weapons = new ArrayList<>();
        nameWeapons.add(0, "Selecciona Arma");
        nameActions.add(0, "Selecciona Accion");
        try {
            weapons = ItemsController.getArmas(getApplicationContext());
            for (Arma item : weapons) {
                nameWeapons.add(item.getNombre());
            }
        } catch (Exception ex) {
            Toast.makeText(this, "Error " + ex, Toast.LENGTH_SHORT).show();
        }

        for (String accion : Monstruo.accionesMonstruos.acciones) {
            nameActions.add(accion);
        }

        adapterWeapons = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, nameWeapons);
        adapterWeapons.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spArmaMonstruo.setAdapter(adapterWeapons);

        adapterActions = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, nameActions);
        adapterActions.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spAccionMonstruo.setAdapter(adapterActions);

        bt_crearMonstruo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (txb_nameMonster.getText().toString().length() == 0) {
                    Toast.makeText(CreateAMonster.this, "Ingrese un nombre", Toast.LENGTH_SHORT).show();
                } else {
                    if (spArmaMonstruo.getSelectedItem().toString() == "Selecciona Arma") {
                        Toast.makeText(CreateAMonster.this, "Seleccionar arma", Toast.LENGTH_SHORT).show();
                    } else {
                        if (spAccionMonstruo.getSelectedItem().toString() == "Selecciona Accion") {
                            Toast.makeText(CreateAMonster.this, "Seleccionar Accion", Toast.LENGTH_SHORT).show();
                        } else {
                            if (txb_CA.getText().toString().length() < 1 || txb_vel.getText().toString().length() < 1
                                    || txb_hp.getText().toString().length() < 1 || txb_fue.getText().toString().length() < 1
                                    || txb_des.getText().toString().length() < 1 || txb_con.getText().toString().length() < 1
                                    || txb_int.getText().toString().length() < 1 || txb_sab.getText().toString().length() < 1
                                    || txb_descMonster.getText().toString().length() < 1 || txb_car.getText().toString().length() < 1) {
                                Toast.makeText(CreateAMonster.this, "Completar campos", Toast.LENGTH_SHORT).show();
                            } else {

                                int armaU = -1;
                                String nombreMon = txb_nameMonster.getText().toString();
                                String descMon = txb_descMonster.getText().toString();
                                String accionU = spAccionMonstruo.getSelectedItem().toString();
                                try {
                                    armaU = ItemsController.getIdArmaByName(getApplicationContext(), spArmaMonstruo.getSelectedItem().toString());
                                } catch (Exception ex) {
                                    Toast.makeText(CreateAMonster.this, "Error getIdArmaByName" + ex, Toast.LENGTH_SHORT).show();
                                }

                                int CA = Integer.parseInt(txb_CA.getText().toString());
                                int HP = Integer.parseInt(txb_hp.getText().toString());
                                int VEL = Integer.parseInt(txb_vel.getText().toString());
                                int FUE = Integer.parseInt(txb_fue.getText().toString());
                                int DES = Integer.parseInt(txb_des.getText().toString());
                                int CON = Integer.parseInt(txb_con.getText().toString());
                                int INT = Integer.parseInt(txb_int.getText().toString());
                                int SAB = Integer.parseInt(txb_sab.getText().toString());
                                int CAR = Integer.parseInt(txb_car.getText().toString());
                                int EXPFINAL = 500;

                                try {

                                    Monstruo m = new Monstruo();
                                    m.setNameMonstruo(nombreMon);
                                    m.setAction(accionU);
                                    m.setDescripcion(descMon);
                                    m.setCA(CA);
                                    m.setVEL(VEL);
                                    m.setCAR(CAR);
                                    m.setCON(CON);
                                    m.setDES(DES);
                                    m.setFUE(FUE);
                                    m.setINT(INT);
                                    m.setSAB(SAB);
                                    m.setHP(HP);
                                    m.setIdArma(armaU);
                                    m.setExpFinal(EXPFINAL);


                                    MonsterController.createMonster(getApplicationContext(), m);
                                    Intent i = new Intent(getApplicationContext(), VistaAllMonsters.class);
                                    startActivity(i);


                                } catch (Exception ex) {
                                    Toast.makeText(CreateAMonster.this, "ERROR" + ex, Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                }
            }
        });
    }
}