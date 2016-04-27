package mbpl.graphical.passwords.sqlite;

/**
 * Created by benja135 on 26/04/16.
 */
public abstract class Methode {

    protected int id, bruteForce, dictionaryAttack, shoulderSurfing, smudgeAttack, eyeTracking, spyWare, espaceMdp;
    protected int apprentissage, memorisation, temps, satisfaction, nb_tentative_reussie, nb_tentative_echouee;
    protected String nom, categorie, mdp;
    protected Float indiceSecurite, indiceUtilisabilite, temps_auth_moyen;
    protected int nbImage, doublon;

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

    public int getNbImage() {
        return nbImage;
    }

    public void setNbImage(int nbImage) {
        this.nbImage = nbImage;
    }

    public boolean getDoublon() {
        return (doublon == 1);
    }

    public void setDoublon(int doublon) {
        this.doublon = doublon;
    }
}
