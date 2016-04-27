package mbpl.graphical.passwords.dejaVu;

import android.os.Bundle;

import mbpl.graphical.passwords.genericDejaVu.GenericAuthentification;
import mbpl.graphical.passwords.sqlite.DejaVu;


/**
 * Created by benja135 on 05/03/16.
 * Activité d'authentification de la méthode "Déjà Vu".
 */
public class Authentification extends GenericAuthentification {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.here = Authentification.this;
        this.nextClass = mbpl.graphical.passwords.Accueil.class;
        this.nbImage = 258;
        this.methode = new DejaVu();
        super.onCreate(savedInstanceState);
    }

    protected int getDrawableN(int n) {
        return getResources().getIdentifier("icon"
                + tailleImage + "x" + tailleImage + "_" + n, "drawable", getPackageName());
    }
}
