package com.example.cafoma_app.entite;

public class Formation {
    private int id;
    private String nom;
    private int cout;
    private String description;
    private String image;
    public Formation(int id, int cout,  String image, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.cout = cout;
        this.description = description;
        this.image = image;

    }
    public Formation() {
        this.id = id;
        this.cout = cout;
        this.image = image;
        this.nom = nom;
        this.description = description;
    }
    public String getFormationStr(){
        return "NUMERO FORMATION  :" + id + " NOM : " + nom + " MONTANT :" + cout;
    }
    @Override
    public String toString() {
        return "Formation{" +
                "id='" + id + '\'' +
                ", cout='" + cout + '\'' +
                ", image='" + image + '\'' +
                ", nom=" + nom +
                ", description='" + description +
                '}';
    }

    public int getNumFormation() {
        return id;
    }
    public void setNumFormation(int id) {
        this.id = id;
    }

    public int getMontant() {
        return cout;
    }
    public void setMontant(int cout) {
        this.cout = cout;
    }

    public String getImage() {
        return image;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nomClient) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
