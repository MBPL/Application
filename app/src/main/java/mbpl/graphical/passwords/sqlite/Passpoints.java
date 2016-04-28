package mbpl.graphical.passwords.sqlite;

import mbpl.graphical.passwords.passPoints.ChoixImage;
import mbpl.graphical.passwords.passPoints.Deverouillage;

/**
 * Created by Matteo on 26/04/2016.
 * Methode Passpoints.
 */
public class Passpoints extends Methode {

    public Passpoints() {
        this.id = 2;
        this.creation = ChoixImage.class;
        this.authentification = Deverouillage.class;
        this.description = "- Choisir une image.\n- Choisir au moins un point à repérer sur cette image.\n- Faire 'Suivant'.\n- Retrouver ces points.";
        this.nom = "Passpoints";
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

        this.param1 = 0;
        this.param2 = 0;
    }

}
