package com.example.cafoma_app.controleur;

import android.content.Context;

import com.example.cafoma_app.entite.Formation;
import com.example.cafoma_app.model.AccesBd;

import java.util.List;

public final class ControleurBdd implements Controleur {
    private static String TAG = "ControleurBdd";
    private static ControleurBdd instance = null ;
    private static AccesBd accesBd;
    private Formation formation;
    private static List<Formation> formationList;

    private ControleurBdd(){
        super();
    }

    public static final ControleurBdd getInstance(Context context){
        if(ControleurBdd.instance == null){
            ControleurBdd.instance = new ControleurBdd();
            accesBd = new AccesBd(context);
            formationList = accesBd.getListFacture();
        }
        return ControleurBdd.instance;
    }
    public void persisterFacture(Formation formation){
        accesBd.persister(formation);
        formationList.add(formation);
    }
    public List<Formation> getFactureList(){
        return formationList;
    }
    public void setFacture(Formation formation){
        this.formation = formation;
    }

    public Formation getFacture() {
        return formation;
    }
}
