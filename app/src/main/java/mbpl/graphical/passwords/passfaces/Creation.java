package mbpl.graphical.passwords.passfaces;

import android.os.Bundle;

import mbpl.graphical.passwords.sqlite.Passfaces;


/**
 * Created by benja135 on 26/04/16.
 * Activité de création de la méthode "passfaces".
 */
public class Creation extends mbpl.graphical.passwords.genericDejaVu.Creation {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState, 20, new Passfaces());
    }

    @Override
    protected int getDrawableN(int n) {
        return getResources().getIdentifier("visage_" + n, "drawable", getPackageName());
    }

}
