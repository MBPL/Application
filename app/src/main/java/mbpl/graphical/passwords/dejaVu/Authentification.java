package mbpl.graphical.passwords.dejaVu;

import android.os.Bundle;

import mbpl.graphical.passwords.sqlite.DejaVu;


/**
 * Created by benja135 on 05/03/16.
 * Activité d'authentification de la méthode "Déjà Vu".
 */
public class Authentification extends mbpl.graphical.passwords.genericDejaVu.Authentification {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState, 258, new DejaVu());
    }

}
