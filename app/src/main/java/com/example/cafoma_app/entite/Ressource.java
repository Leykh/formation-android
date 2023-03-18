package com.example.cafoma_app.entite;

public class Ressource {
    private int idRessource;
    private int idFormation;
    private String ressource;
    private String description;

    public Ressource(int idRessource, int idFormation,  String description, String ressource) {
        this.idRessource = idRessource;
        this.idFormation = idFormation;
        this.ressource = ressource;
        this.description = description;

    }
    public Ressource() {
        this.idRessource = idRessource;
        this.idFormation = idFormation;
        this.ressource = ressource;
        this.description = description;
    }
    @Override
    public String toString() {
        return "Ressource{" +
                "idRessource='" + idRessource + '\'' +
                ", idFormation='" + idFormation + '\'' +
                ", ressource='" + ressource + '\'' +
                ", description='" + description +
                '}';
    }

    public int getIdRessource() {
        return idRessource;
    }

    public void setIdRessource(int idRessource) {
        this.idRessource = idRessource;
    }

    public int getIdFormation() {
        return idFormation;
    }

    public void setIdFormation(int idFormation) {
        this.idFormation = idFormation;
    }

    public String getRessource() {
        return ressource;
    }

    public void setRessource(String ressource) {
        this.ressource = ressource;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRessourceStr() {
        return idRessource +  " " + description + " " + ressource;
    }
}
