package mbpl.graphical.passwords.sqlite;

/**
 * Created by Matteo on 14/04/2016.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import mbpl.graphical.passwords.R;

/**
 * Tester d'inserer une méthode DejaVu dans la base, supprimer etc...
 */
public class TestDejaVuBDD extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_accueil);

        this.deleteDatabase("methode.db");

        final MethodeManager dejaVuManager = new MethodeManager(this);

        // On ouvre la base de données pour écrire dedans
        dejaVuManager.open();

        // Création et insertion de la méthode
        DejaVu methodeDejaVu = new DejaVu();

        //test si exist dans BD
        boolean flagBD = dejaVuManager.exist(new DejaVu());
        if (!flagBD) {
            Log.v("exist", "TEST OK => NEXISTE PAS");
        }


        long numeroEnregistrement = dejaVuManager.addMethode(methodeDejaVu);


        if (numeroEnregistrement != -1) {

            // Récupération de la méthode DejaVu
            DejaVu dejaVuFromBdd1 = (DejaVu) dejaVuManager.getMethode(new DejaVu());

            //test si exist dans BD
            flagBD = dejaVuManager.exist(new DejaVu());
            if (flagBD) {
                Log.v("exist", "TEST OK => EXISTE BIEN");
            }

            //test si mot de passe par défault
            boolean flagPassword = dejaVuManager.defaultPassword(dejaVuFromBdd1);
            if (flagPassword) {
                Log.v("defaultPassword", "TEST OK => MDP par défaut");
            }


            if (dejaVuFromBdd1 != null) {
                Toast.makeText(TestDejaVuBDD.this, "methode 1 bien recuperé", Toast.LENGTH_SHORT).show();

                Log.v("mdp avant", "=>" + dejaVuFromBdd1.getMdp());

                //Test modification
                dejaVuManager.setStats(dejaVuFromBdd1, 2, 2, 3f);

                //Re - Récupération
                dejaVuFromBdd1 = (DejaVu) dejaVuManager.getMethode(new DejaVu());

                //vérif de la modification

                Toast.makeText(TestDejaVuBDD.this, "2,2,3,vide : " + dejaVuFromBdd1.getNb_tentative_echouee()
                        + "$" + dejaVuFromBdd1.getNb_tentative_reussie() + "$"
                        + dejaVuFromBdd1.getTemps_auth_moyen()
                        + "$" + dejaVuFromBdd1.getMdp()
                        , Toast.LENGTH_LONG).show();


                //mise a jour mot de passe
                dejaVuManager.setPassword(dejaVuFromBdd1, "coepDeLespace");

                //Re - Récupération
                dejaVuFromBdd1 = (DejaVu) dejaVuManager.getMethode(new DejaVu());

                //test si mot de passe par défault
                flagPassword = dejaVuManager.defaultPassword(dejaVuFromBdd1);
                if (!flagPassword) {
                    Log.v("defaultPassword", "TEST OK => MDP MODIFIE");
                }

                Log.v("mdp apres", "=>" + dejaVuFromBdd1.getMdp());


            }

            //Suppression méthode 1
            dejaVuManager.removeMethode(dejaVuFromBdd1);
        }


        dejaVuManager.close();

    }

}
