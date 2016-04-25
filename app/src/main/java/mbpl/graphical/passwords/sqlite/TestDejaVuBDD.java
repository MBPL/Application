package mbpl.graphical.passwords.sqlite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import mbpl.graphical.passwords.R;

/**
 * TODO à supprimer à la fin
 * Created by Matteo on 07/04/2016.
 * Tester d'inserer une méthode DejaVu dans la base, supprimer etc..
 */
public class TestDejaVuBDD extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_accueil);

        this.deleteDatabase("methode.db");

        final DejaVuManager dejaVuManager = new DejaVuManager(this);

        // On ouvre la base de données pour écrire dedans
        dejaVuManager.open();

        // Création et insertion de la méthode
        DejaVu methodeDejaVu = new DejaVu();


        long numeroEnregistrement = dejaVuManager.addDejaVu(methodeDejaVu);


        if (numeroEnregistrement != -1) {

            // Récupération de la méthode DejaVu
            DejaVu dejaVuFromBdd1 = dejaVuManager.getDejaVu(methodeDejaVu);


            if (dejaVuFromBdd1 != null) {
                Toast.makeText(TestDejaVuBDD.this, "methode 1 bien recuperé", Toast.LENGTH_SHORT).show();

                Log.v("mdp avant", "=>" + dejaVuFromBdd1.getMdp());

                //Test modification
                dejaVuManager.updateDejaVu(dejaVuFromBdd1, 2, 2, 3f);

                //Re - Récupération
                dejaVuFromBdd1 = dejaVuManager.getDejaVu(methodeDejaVu);

                //vérif de la modification

                Toast.makeText(TestDejaVuBDD.this, "2,2,3,vide : " + dejaVuFromBdd1.getNb_tentative_echouee()
                        + "$" + dejaVuFromBdd1.getNb_tentative_reussie() + "$"
                        + dejaVuFromBdd1.getTemps_auth_moyen()
                        + "$" + dejaVuFromBdd1.getMdp()
                        , Toast.LENGTH_LONG).show();


                //mise a jour mot de passe
                dejaVuManager.setPassword(dejaVuFromBdd1, "coepDeLespace");

                //Re - Récupération
                dejaVuFromBdd1 = dejaVuManager.getDejaVu(methodeDejaVu);

                Log.v("mdp apres", "=>" + dejaVuFromBdd1.getMdp());


            }

            //Suppression méthode 1
            dejaVuManager.removeDejaVu(dejaVuFromBdd1);
        }

        dejaVuManager.close();

    }

}
