package com.example.cafoma_app.controleur;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.cafoma_app.entite.Formation;
import com.example.cafoma_app.entite.Ressource;
import com.example.cafoma_app.entite.User;
import com.example.cafoma_app.model.AccesDistant;
import com.example.cafoma_app.vue.ListeActivity;
import com.example.cafoma_app.vue.MainActivity;

import java.util.List;

public final class ControleurServeur implements Controleur {
    private static String TAG = "ControleurServeur";
    private static ControleurServeur instance = null ;
    private static AccesDistant accesDistant;
    private List<Formation> formationList;
    private Formation formation;
    private List<Ressource> ressourceList;
    private Ressource ressource;
    private User user;

    private ControleurServeur(){
        super();
    }
    public static final ControleurServeur getInstance(){
        if(ControleurServeur.instance == null){
            ControleurServeur.instance = new ControleurServeur();
            accesDistant = new AccesDistant();
            accesDistant.envoyerRequete("lister");
            accesDistant.envoyerRequete("ressource");
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

    public List<Ressource> getRessourceList() {
        return ressourceList;
    }
    public void setRessourceList(List<Ressource> RessourceList) {
        this.ressourceList = RessourceList;
    }
    public Ressource getRessource(){ return ressource; }
    public void setRessource(Ressource ressource){ this.ressource = ressource; }

    public void initUser(String username, String password, View view, Intent intent, Activity activity){
        accesDistant = new AccesDistant(intent, activity);
        accesDistant.envoyerRequeteView("mdp" +"&login=" + username + "&password=" + password ,view);
    }
    public User getUser(){ return user; }

    public void setUser(User user){ this.user = user; }
}

