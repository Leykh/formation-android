package com.example.cafoma_app.controleur;


import com.example.cafoma_app.entite.Formation;
import com.example.cafoma_app.model.AccesDistant;

import java.util.List;

public final class ControleurServeur implements Controleur {
    private static String TAG = "ControleurServeur";
    private static ControleurServeur instance = null ;
    private static AccesDistant accesDistant;
    private List<Formation> formationList;
    private Formation formation;

    private ControleurServeur(){
        super();
    }
    public static final ControleurServeur getInstance(){
        if(ControleurServeur.instance == null){
            ControleurServeur.instance = new ControleurServeur();
            accesDistant = new AccesDistant();
            accesDistant.envoyerRequete("lister");
        }
        return ControleurServeur.instance;
    }
    public List<Formation> getFormationList() {
        return formationList;
    }
    public void setFormationList(List<Formation> FormationList) {
        this.formationList = FormationList;
    }
    public Formation getFormation(){ return formation; }
    public void setFormation(Formation formation){ this.formation = formation; }

}
