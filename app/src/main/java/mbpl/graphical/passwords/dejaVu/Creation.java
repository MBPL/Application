package mbpl.graphical.passwords.dejaVu;

import android.os.Bundle;

import mbpl.graphical.passwords.genericDejaVu.GenericCreation;
import mbpl.graphical.passwords.sqlite.DejaVu;

/**
 * Created by benja135 on 05/03/16.
 */
public class Creation extends GenericCreation {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.here = Creation.this;
        this.nextClass = mbpl.graphical.passwords.dejaVu.Authentification.class;
        this.nbImage = 258;
        this.methode = new DejaVu();
        super.onCreate(savedInstanceState);
    }

    protected int getDrawableN(int n) {
        return getResources().getIdentifier("icon"
                + tailleImage + "x" + tailleImage + "_" + n, "drawable", getPackageName());
    }
}
