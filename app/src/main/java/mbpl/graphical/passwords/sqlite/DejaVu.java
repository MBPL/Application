package mbpl.graphical.passwords.sqlite;

import mbpl.graphical.passwords.dejaVu.Accueil;
import mbpl.graphical.passwords.dejaVu.Authentification;

/**
 * Created by Matteo on 08/04/2016.
 * Methode DejaVu.
 */
public class DejaVu extends Methode {

    public DejaVu() {
        this.id = 1;
        this.creation = Accueil.class;
        this.authentification = Authentification.class;
        this.description = "L’utilisateur fera tout d’abord une sélection d’un nombre quelconque " +
                "d’images. Pour s’identifier, il devra alors retrouver les images qu’il a choisies " +
                "précédemment dans une plus large sélection d’images disposées aléatoirement.";
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

        this.param1 = 24;
        this.param2 = 0;
    }

}
