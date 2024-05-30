package com.example.creacindepersonaje.Battle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.creacindepersonaje.Controller.BattleController;
import com.example.creacindepersonaje.Controller.GuildController;
import com.example.creacindepersonaje.Controller.ItemsController;
import com.example.creacindepersonaje.Controller.MonsterController;
import com.example.creacindepersonaje.Controller.PersonajeController;
import com.example.creacindepersonaje.Controller.SpellController;
import com.example.creacindepersonaje.Modelos.Arma;
import com.example.creacindepersonaje.Modelos.Battle;
import com.example.creacindepersonaje.Modelos.Dado;
import com.example.creacindepersonaje.Modelos.Hechizo;
import com.example.creacindepersonaje.Modelos.LogBattle;
import com.example.creacindepersonaje.Modelos.Monstruo;
import com.example.creacindepersonaje.Modelos.Personaje;
import com.example.creacindepersonaje.R;

import java.util.ArrayList;

public class JugadaAtaque extends AppCompatActivity {
    private TextView txtNameBattle,txtNamePj,txtHP,txtShield,txtDamage,txtMonstruo;
    private Button btnAtaque;

    private ListView lvBattlePersonajes;
    private ArrayList<String> battleNames;
    private ArrayList<Personaje> listaGuild;
    private ArrayAdapter<String> adapterBattleNames;
    private int itemPosicion;

    private Spinner spAtaques;
    private ArrayList<String> listAtaques;
    private ArrayList<Hechizo> spellsAtaque;
    private ArrayList<Arma> armasAtaque;
    private ArrayAdapter<String> adapterAtaques;
    private String name;
    private String name2 = "NN";
    Battle b = new Battle();
    Personaje p = new Personaje();
    Arma w = new Arma();
    Hechizo h = new Hechizo();
    Monstruo m = new Monstruo();
    LogBattle log = new LogBattle();
    Dado d = new Dado();
    Dado d20 = new Dado();
    int resultDado;
    int damaged;
    private int idBattle;
    private int idGuild;
    private static int OriginalHP;
    private int MonsterHP;
    private Arma wMonster;

    private boolean PJSEL = true;
    private boolean ATQSEL = true;
    private boolean MonsterTurno = true;

    private ListView lvLog;
    private ArrayList<String> logHistory;
    private ArrayAdapter<String> adapterLog;
    private ArrayList<LogBattle> logList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugada_ataque);
        iniciarComponentes();
        iniciarEventos();
    }

    public void iniciarComponentes() {
        txtNameBattle = findViewById(R.id.txtNameBattle);
        txtNamePj = findViewById(R.id.txtNamePj);
        txtHP = findViewById(R.id.txtHP);
        txtShield = findViewById(R.id.txtShield);
        txtDamage = findViewById(R.id.txtDamage);
        txtMonstruo = findViewById(R.id.txtMonstruo);
        btnAtaque = findViewById(R.id.btnAtaque);
        lvBattlePersonajes = findViewById(R.id.lvBattlePersonajes);
        lvLog = findViewById(R.id.lvLog);
        spAtaques = findViewById(R.id.spAtques);
    }

    public void iniciarEventos() {
        Intent intent = this.getIntent();
        Bundle extra = intent.getExtras();
        idBattle = extra.getInt("IDBATTLE");
        idGuild = extra.getInt("IDGUILD");
        battleNames = new ArrayList<>();
        listAtaques = new ArrayList<>();
        logHistory = new ArrayList<>();
        listAtaques.add(0,"Selecciona");
        log.setIdBattle(idBattle);
        log.setIdGuild(idGuild);


        try{
            listaGuild = GuildController.listarIntegrantes(getApplicationContext(),idGuild);
            for(Personaje p : listaGuild){
                battleNames.add(p.getNombre());
            }
        }catch (Exception ex){
            Toast.makeText(this, "Error sorting List"+ex, Toast.LENGTH_SHORT).show();
        }

        try{
            b = BattleController.getBattleById(getApplicationContext(),idBattle);
        }catch (Exception ex){
            Toast.makeText(JugadaAtaque.this, "Error getBattleByID "+ex, Toast.LENGTH_SHORT).show();
        }
        try{
            m = MonsterController.findMonster(getApplicationContext(),b.getIdMonstruo());
            MonsterHP = m.getHP();
            log.setIdMonstruo(m.getIdMonstruo());
        }catch (Exception ex){
            Toast.makeText(this, "Error findMonster "+ex, Toast.LENGTH_SHORT).show();
        }


        txtNameBattle.setText(b.getNameBattle());

        txtMonstruo.setText(m.getNameMonstruo());
        try{
            wMonster = ItemsController.findArma(getApplicationContext(),m.getIdArma());

        }catch (Exception ex){
            Toast.makeText(this, "Error getting Monsters "+ex, Toast.LENGTH_SHORT).show();
        }


        adapterBattleNames = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,battleNames);
        lvBattlePersonajes.setAdapter(adapterBattleNames);

        adapterLog = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,logHistory);
        lvLog.setAdapter(adapterLog);

        adapterAtaques = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,listAtaques);
        adapterAtaques.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spAtaques.setAdapter(adapterAtaques);



        lvBattlePersonajes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                name = lvBattlePersonajes.getItemAtPosition(i).toString();
                name2 = lvBattlePersonajes.getItemAtPosition(i).toString();
                txtNamePj.setText(name);
                listAtaques.clear();
                listAtaques.add(0,"Selecciona");
                adapterAtaques.notifyDataSetChanged();
                itemPosicion = i;
                try {
                    p.setIdPersonaje(PersonajeController.getIdPorNombre(getApplicationContext(), name));
                    p = PersonajeController.findPj(getApplicationContext(),PersonajeController.getIdPorNombre(getApplicationContext(), name));
                    w = ItemsController.findArma(getApplicationContext(),p.getIdArma());
                    listAtaques.add(w.getNombre());
                    try {
                        if(p.getAptitudMagica()==1){
                            h = SpellController.findHechizo(getApplicationContext(),p.getIdHechizo());
                            listAtaques.add(h.getNombre());
                        }
                    }catch (Exception ex1){
                        Toast.makeText(JugadaAtaque.this, "txtHP error "+ex1, Toast.LENGTH_SHORT).show();
                    }
                    txtHP.setText(p.getPG()+"");
                    txtShield.setText(p.getCA()+"");
                    txtDamage.setText(p.getModStr()+"");
                    OriginalHP = p.getPG();

                }catch (Exception ex){
                    Toast.makeText(JugadaAtaque.this, "Error getIdPorNombre "+ex, Toast.LENGTH_SHORT).show();
                }

            }
        });

        spAtaques.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int idItem = -1;
                switch (i){
                    case 0:
                        break;
                    case 1:
                        try {
                            idItem = ItemsController.getIdArmaByName(getApplicationContext(), spAtaques.getItemAtPosition(i).toString());
                            w = ItemsController.findArma(getApplicationContext(),idItem);
                            txtDamage.setText("");
                            txtDamage.setText("Dado "+w.getDamage());
                        }catch (Exception ex){
                            Toast.makeText(JugadaAtaque.this, "getIdArmaByName "+ex, Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 2:
                        try {
                            idItem = SpellController.findHechizoByName(getApplicationContext(), spAtaques.getItemAtPosition(i).toString());
                            h = SpellController.findHechizo(getApplicationContext(),idItem);
                            if(h.getDamage()!=0) {
                                txtDamage.setText("Dado "+h.getDamage());
                                txtShield.setText(p.getCA()+"");
                                txtHP.setText(p.getPG()+"");
                            }
                            if(h.getHeal()!=0){
                                String HP = txtHP.getText().toString();
                                d.setValorDado(h.getHeal());
                                txtHP.setText(d.lanzarDado()+" + "+HP);
                                txtDamage.setText(p.getModStr()+"");
                                txtShield.setText(p.getCA()+"");
                            }
                            if(h.getShield()!=0){
                                txtShield.setText(h.getShield()+"");
                                txtDamage.setText(p.getModStr()+"");
                                txtHP.setText(p.getPG()+"");
                            }
                        }catch (Exception ex){
                            Toast.makeText(JugadaAtaque.this, "getIdArmaByName "+ex, Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btnAtaque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(name2=="NN"){
                    Toast.makeText(JugadaAtaque.this, "Selecciona un personaje", Toast.LENGTH_SHORT).show();
                    PJSEL = false;
                }else{
                    PJSEL=true;
                }

                if(spAtaques.getSelectedItemPosition()==0){
                    Toast.makeText(JugadaAtaque.this, "Selecciona un ataque", Toast.LENGTH_SHORT).show();
                    ATQSEL=false;
                }else{
                    ATQSEL=true;
                }

                if(ATQSEL&&PJSEL) {
                    //Ataque del personaje
                    try {
                        damaged = 0;
                        String relato = "";
                        d20.setValorDado(20);
                        resultDado = d20.lanzarDado();
                        log.setDadoResult(resultDado);
                        relato += p.getNombre() + " obtiene un " + resultDado + ". \n";


                        if (resultDado <= m.getCA()) {
                            log.setIdPersonaje(p.getIdPersonaje());
                            relato += "Golpe fallido.";
                            logHistory.add(relato);
                            adapterLog.notifyDataSetChanged();
                            lvLog.setAdapter(adapterLog);
                            log.setDescripcion(relato);
                            log.setDamageMonstruo(0);
                            log.setDamagePersonaje(0);
                            try{
                                BattleController.writeLog(getApplicationContext(),log);
                            }catch (Exception ex){
                                Toast.makeText(JugadaAtaque.this, "Error writeLog fail attack "+ex, Toast.LENGTH_SHORT).show();
                            }

                        }
                        if (resultDado > m.getCA()) {

                            Dado damage = new Dado();
                            damage.setValorDado(w.getDamage());
                            damaged = damage.lanzarDado();

                            relato += p.getNombre() + " traspasa la armadura del monstruo.\n";
                            relato += p.getNombre() + " Ataca con " + spAtaques.getSelectedItem().toString() + "\n";
                            relato += "Provoca " + damaged + " de daño a " + m.getNameMonstruo();

                            log.setIdPersonaje(p.getIdPersonaje());
                            log.setDescripcion(relato);
                            log.setDamageMonstruo(0);
                            log.setDamagePersonaje(damaged);

                            logHistory.add(relato);
                            adapterLog.notifyDataSetChanged();
                            lvLog.setAdapter(adapterLog);

                            MonsterHP = MonsterHP-damaged;
                            if(MonsterHP<=0){
                                relato="El monstruo fue destruido";
                                logHistory.add(relato);
                                log.setDescripcion(relato);
                                try{
                                    BattleController.removeGuild(getApplicationContext(),idBattle);
                                }catch (Exception ex){
                                    Toast.makeText(JugadaAtaque.this, "Error al finalizar "+ex, Toast.LENGTH_SHORT).show();
                                }
                                adapterLog.notifyDataSetChanged();
                                lvLog.setAdapter(adapterLog);
                                MonsterTurno = false;
                                btnAtaque.setText("Triunfo!");
                                btnAtaque.setEnabled(false);
                                Intent intent = new Intent(getApplicationContext(), battleHistory.class);
                                intent.putExtra("IDBATTLE",idBattle);
                                startActivity(intent);
                            }

                            try {
                                BattleController.writeLog(getApplicationContext(), log);
                            } catch (Exception ex) {
                                Toast.makeText(JugadaAtaque.this, "Error writeLog " + ex, Toast.LENGTH_SHORT).show();
                            }
                        }
                        if(MonsterTurno) {
                            try {
                                String relatoMonster = "";
                                resultDado = d20.lanzarDado();
                                log.setDadoResult(resultDado);
                                log.setDamagePersonaje(0);
                                log.setIdPersonaje(p.getIdPersonaje());
                                if (resultDado <= p.getCA()) {
                                    relatoMonster += "El monstruo ataca, pero falla";
                                    logHistory.add(relatoMonster);
                                    adapterLog.notifyDataSetChanged();
                                    lvLog.setAdapter(adapterLog);
                                    log.setDescripcion(relatoMonster);
                                    log.setDamageMonstruo(0);
                                }
                                if (resultDado > p.getCA()) {
                                    wMonster = ItemsController.findArma(getApplicationContext(), m.getIdArma());
                                    relatoMonster += m.getNameMonstruo() + " traspasa la armadura de " + p.getNombre() + "\n";
                                    relatoMonster += m.getNameMonstruo() + " ataca con " + wMonster.getNombre() + "\n";
                                    d.setValorDado(wMonster.getDamage());
                                    damaged = d.lanzarDado();
                                    relatoMonster += m.getNameMonstruo() + " hace " + damaged + " de daño a " + p.getNombre() + "\n";
                                    log.setDescripcion(relatoMonster);
                                    log.setDamageMonstruo(damaged);
                                    logHistory.add(relatoMonster);
                                    adapterLog.notifyDataSetChanged();
                                    lvLog.setAdapter(adapterLog);
                                    txtHP.setText(OriginalHP - damaged + "");
                                    PersonajeController.setVida(getApplicationContext(),p.getIdPersonaje(),OriginalHP-damaged);
                                    if (Integer.parseInt(txtHP.getText().toString()) <= 0) {
                                        try {
                                            relatoMonster = p.getNombre() + " ha perdido el conocimiento";
                                            adapterBattleNames.notifyDataSetChanged();
                                            adapterBattleNames.remove(p.getNombre());
                                            lvBattlePersonajes.setAdapter(adapterBattleNames);
                                            logHistory.add(relatoMonster);
                                            log.setDescripcion(relatoMonster);
                                            BattleController.writeLog(getApplicationContext(),log);
                                        } catch (Exception ex) {
                                            Toast.makeText(JugadaAtaque.this, "error borrando al moribundo " + ex, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    try {
                                        BattleController.writeLog(getApplicationContext(), log);
                                    } catch (Exception ex) {
                                        Toast.makeText(JugadaAtaque.this, "Error saving Log Monster " + ex, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            } catch (Exception ex) {
                                Toast.makeText(JugadaAtaque.this, "Error Monster Attack " + ex, Toast.LENGTH_SHORT).show();
                            }
                        }
                    } catch (Exception ex) {
                        Toast.makeText(JugadaAtaque.this, "Error Ataque PJ " + ex, Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
}