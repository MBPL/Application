package mbpl.graphical.passwords.sqlite;

import mbpl.graphical.passwords.passfaces.Authentification;
import mbpl.graphical.passwords.passfaces.Creation;

/**
 * Created by Matteo on 26/04/2016.
 * Methode Passfaces.
 */
public class Passfaces extends Methode {

    public Passfaces() {
        this.id = 3;
        this.creation = Creation.class;
        this.authentification = Authentification.class;
        this.description = "- Choisir une suite d'images.\n" +
                "- Appuyer sur 'Valider'.\n" +
                "- Cliquer sur les images dans l'ordre pour vous authentifier.";
        this.nom = "Passfaces";
        this.categorie = "reconnaissance";
        this.bruteForce = 0;
        this.dictionaryAttack = 2;
        this.shoulderSurfing = 2;
        this.smudgeAttack = 5;
        this.eyeTracking = 3;
        this.spyWare = 3;
        this.espaceMdp = 1;
        this.indiceSecurite = 2.29f;
        this.apprentissage = 5;
        this.memorisation = 3;
        this.temps = 3;
        this.satisfaction = 3;
        this.indiceUtilisabilite = 3.5f;

        this.nb_tentative_echouee = 0;
        this.nb_tentative_reussie = 0;
        this.temps_auth_moyen = 0f;
        this.mdp = "";

        this.param1 = 9;
        this.param2 = 0;
    }

}
