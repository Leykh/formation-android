package com.example.cafoma_app.vue;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cafoma_app.R;
import com.example.cafoma_app.controleur.Controleur;
import com.example.cafoma_app.controleur.ControleurBdd;
import com.example.cafoma_app.controleur.ControleurServeur;
import com.example.cafoma_app.entite.Formation;

import java.util.ArrayList;
import java.util.List;

public class ListeActivity extends AppCompatActivity {
    private static String TAG = "ListeActivity";
    private ListView liste;
    private ArrayAdapter<String> adapter;
    private List<Formation> formationList;
    private Controleur controleur;
    private TextView titreView;
    private int mode;
    private String titre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste);
        getFormationListByMode();
        setTitle("Bonjour " + controleur.getUser().getLogin());
        Log.i(TAG,"***************************** : " + controleur.getUser());
        if(formationList != null){
            titreView.setText(titre);
            afficherListe();
        }
        else {
            titreView.setText("Aucune formation");
        }
    }
    private void getFormationListByMode(){
        mode = getIntent().getIntExtra("mode", -1);
        titreView = (TextView) findViewById(R.id.titreId);
        if(mode == 1) {
            controleur = ControleurServeur.getInstance();
            titre = "Mes formations";
            formationList = controleur.getUser().getFormationList();
        }
        else {
            controleur = ControleurServeur.getInstance();
            titre = "Liste des formations";
            formationList = controleur.getFormationList();
        }
    }
    private void afficherListe(){
        List<String> strFormationList = formaterFormationList(formationList);
        liste = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                strFormationList);
        liste.setAdapter(adapter);
        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "position=" + position);
                controleur.setFormation(formationList.get(position));
                Intent intent = new Intent(ListeActivity.this, DetailActivity.class);
                intent.putExtra("mode",mode);
                startActivity(intent);
            }
        });
    }
    private List<String> formaterFormationList(List<Formation> formations){
        List<String> strFormationList = new ArrayList<>();
        for (Formation formation : formations) {
            strFormationList.add(formation.getFormationStr());
        }
        return strFormationList;
    }
}