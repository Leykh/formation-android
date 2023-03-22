package com.example.cafoma_app.controleur;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.cafoma_app.entite.Formation;
import com.example.cafoma_app.entite.Ressource;
import com.example.cafoma_app.entite.User;
import com.example.cafoma_app.model.AccesBd;

import java.util.List;

public final class ControleurBdd implements Controleur {
    private static String TAG = "ControleurBdd";
    private static ControleurBdd instance = null ;
    private static AccesBd accesBd;
    private Formation formation;
    private static List<Formation> formationList;
    private static List<Ressource> ressourceList;
    private Ressource ressource;
    private User user;

    private ControleurBdd(){
        super();
    }

    public static final ControleurBdd getInstance(Context context){
        if(ControleurBdd.instance == null){
            ControleurBdd.instance = new ControleurBdd();
            accesBd = new AccesBd(context);
            formationList = accesBd.getListFormation();
        }
        return ControleurBdd.instance;
    }
    public void persisterFormation(Formation formation){
        accesBd.persister(formation);
        formationList.add(formation);
    }
    public List<Formation> getFormationList(){
        return formationList;
    }
    public void setFormation(Formation formation){
        this.formation = formation;
    }

    public Formation getFormation() {
        return formation;
    }
    public List<Ressource> getRessourceList(){
        return ressourceList;
    }
    public void setRessource(Ressource ressource){
        this.ressource = ressource;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void initUser(String username, String password, View view, Intent intent, Activity activity) {

    }

    public Ressource getRessource() {
        return ressource;
    }

    @Override
    public User getUser() {
        return null;
    }


}
