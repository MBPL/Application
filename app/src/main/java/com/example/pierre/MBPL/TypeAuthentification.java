package com.example.pierre.MBPL;

/**
 * Created by pierre on 01/03/16.
 */
public class TypeAuthentification {
    String nom;
    String image;
    int numeroID;
    int security;
    int utilisability;

    public TypeAuthentification(String nom, String image, int numeroID, int utilisability, int security) {
        this.nom = nom;
        this.image = image;
        this.numeroID = numeroID;
        this.utilisability = utilisability;
        this.security = security;
    }

    public int getSecurity() {
        return security;
    }

    public void setSecurity(int security) {
        this.security = security;
    }

    public int getUtilisability() {
        return utilisability;
    }

    public void setUtilisability(int utilisability) {
        this.utilisability = utilisability;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getNumeroID() {
        return numeroID;
    }

    public void setNumeroID(int numeroID) {
        this.numeroID = numeroID;
    }


}
