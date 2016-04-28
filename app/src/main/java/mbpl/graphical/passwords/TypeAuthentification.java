package mbpl.graphical.passwords;

import mbpl.graphical.passwords.sqlite.Methode;

/**
 * Created by pierre on 01/03/16.
 */
public class TypeAuthentification {

    private String nom;
    private String desc;
    private int numeroID;
    private Class creation;
    private Class authentification;
    private Methode methode;

    public TypeAuthentification(String nom, String desc, int numeroID, Class creation, Class authentification, Methode m) {
        this.nom = nom;
        this.desc = desc;
        this.numeroID = numeroID;
        this.creation = creation;
        this.authentification = authentification;
        this.methode = m;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNumeroID() {
        return numeroID;
    }

    public void setNumeroID(int numeroID) {
        this.numeroID = numeroID;
    }

    public Class getCreation() {
        return creation;
    }

    public Class getAuthentification() {
        return authentification;
    }

    public Methode getMethode() {
        return methode;
    }
}
