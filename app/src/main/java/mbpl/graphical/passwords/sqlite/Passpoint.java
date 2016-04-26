package mbpl.graphical.passwords.sqlite;

/**
 * Created by Matteo on 26/04/2016.
 * Methode Passpoint.
 */
public class Passpoint extends Methode {

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
        this.mdp = "";

        this.nbImage = 0;
        this.doublon = 0;
    }

}
