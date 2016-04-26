package mbpl.graphical.passwords.sqlite;

/**
 * Created by Matteo on 26/04/2016.
 */

/**
 * Methode Passpoint.
 */
public class Passpoint {


    private int id, bruteForce, dictionaryAttack, shoulderSurfing, smudgeAttack, eyeTracking, spyWare, espaceMdp;
    private int apprentissage, memorisation, temps, satisfaction, nb_tentative_reussie, nb_tentative_echouee;
    private String nom, categorie,mdp;
    private Float indiceSecurite, indiceUtilisabilite, temps_auth_moyen;


    public Passpoint() {
        this.id = 2;
        this.nom = "Passpoint";
        this.categorie = "rappel indicé";
        this.bruteForce = 5;
        this.dictionaryAttack = 3;
        this.shoulderSurfing = 0;
        this.smudgeAttack = 0;
        this.eyeTracking = 3;
        this.spyWare = 2;
        this.espaceMdp = 3;
        this.indiceSecurite = 2.29f;
        this.apprentissage = 3;
        this.memorisation = 5;
        this.temps = 3;
        this.satisfaction = 3;
        this.indiceUtilisabilite = 3.5f;

        this.nb_tentative_echouee = 0;
        this.nb_tentative_reussie = 0;
        this.temps_auth_moyen = 0f;
        this.mdp= "";
    }

    public int getId() {
        return id;
    }

    public int getBruteForce() {
        return bruteForce;
    }

    public int getDictionaryAttack() {
        return dictionaryAttack;
    }

    public int getSmudgeAttack() {
        return smudgeAttack;
    }

    public int getEspaceMdp() {
        return espaceMdp;
    }

    public int getTemps() {
        return temps;
    }

    public int getNb_tentative_echouee() {
        return nb_tentative_echouee;
    }

    public String getNom() {
        return nom;
    }

    public String getCategorie() {
        return categorie;
    }

    public Float getIndiceSecurite() {
        return indiceSecurite;
    }

    public Float getIndiceUtilisabilite() {
        return indiceUtilisabilite;
    }

    public Float getTemps_auth_moyen() {
        return temps_auth_moyen;
    }

    public int getNb_tentative_reussie() {
        return nb_tentative_reussie;
    }

    public int getSatisfaction() {
        return satisfaction;
    }

    public int getMemorisation() {
        return memorisation;
    }

    public int getApprentissage() {
        return apprentissage;
    }

    public int getSpyWare() {
        return spyWare;
    }

    public int getEyeTracking() {
        return eyeTracking;
    }

    public int getShoulderSurfing() {
        return shoulderSurfing;
    }

    public String getMdp() {
        return mdp;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setNb_tentative_reussie(int nb_tentative_reussie) {
        this.nb_tentative_reussie = nb_tentative_reussie;
    }

    public void setNb_tentative_echouee(int nb_tentative_echouee) {
        this.nb_tentative_echouee = nb_tentative_echouee;
    }

    public void setTemps_auth_moyen(Float temps_auth_moyen) {
        this.temps_auth_moyen = temps_auth_moyen;
    }

    public void setMdp(String motDePasse) {
        this.mdp = motDePasse;
    }
}

