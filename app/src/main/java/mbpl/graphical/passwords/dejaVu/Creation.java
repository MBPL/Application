package mbpl.graphical.passwords.dejaVu;

import android.os.Bundle;

import mbpl.graphical.passwords.sqlite.DejaVu;

/**
 * Created by benja135 on 05/03/16.
 */
public class Creation extends mbpl.graphical.passwords.genericDejaVu.Creation {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState, 258, new DejaVu());
    }

}
