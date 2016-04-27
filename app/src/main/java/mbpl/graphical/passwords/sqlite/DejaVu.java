package mbpl.graphical.passwords.sqlite;

/**
 * Created by Matteo on 08/04/2016.
 * Methode DejaVu.
 */
public class DejaVu extends Methode {

    public DejaVu() {
        this.id = 1;
        this.nom = "Déjà Vu";
        this.categorie = "reconnaissance";
        this.bruteForce = 0;
        this.dictionaryAttack = 2;
        this.shoulderSurfing = 2;
        this.smudgeAttack = 5;
        this.eyeTracking = 5;
        this.spyWare = 3;
        this.espaceMdp = 1;
        this.indiceSecurite = 2.57f;
        this.apprentissage = 2;
        this.memorisation = 3;
        this.temps = 3;
        this.satisfaction = 3;
        this.indiceUtilisabilite = 2.75f;

        this.nb_tentative_echouee = 0;
        this.nb_tentative_reussie = 0;
        this.temps_auth_moyen = 0f;
        this.mdp = "";

        this.nbImage = 24;
        this.doublon = 0;
    }

}
