package mbpl.graphical.passwords;

import java.util.ArrayList;
import java.util.List;

import mbpl.graphical.passwords.sqlite.DejaVu;
import mbpl.graphical.passwords.sqlite.Methode;
import mbpl.graphical.passwords.sqlite.Passfaces;
import mbpl.graphical.passwords.sqlite.Passpoints;

/**
 * Created by benja135 on 28/04/16.
 * Facilite l'intégration d'une nouvelle méthode.
 */
public class ImplementedMethods {

    public static List<Methode> implementedMethods = new ArrayList<Methode>();

    static {
        // TODO AJOUTER VOTRE METHODE A CETTE LISTE
        implementedMethods.add(new Passpoints());
        implementedMethods.add(new DejaVu());
        implementedMethods.add(new Passfaces());
    }

    private ImplementedMethods() {}

}
