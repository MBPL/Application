package mbpl.graphical.passwords.dejaVu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import mbpl.graphical.passwords.R;
import mbpl.graphical.passwords.sqlite.DejaVu;
import mbpl.graphical.passwords.sqlite.DejaVuManager;

/**
 * Created by benja135 on 06/03/16.
 * Configuration avancé de la méthode Déjà Vu.
 */
public class Configuration extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deja_vu_configurations);

        // On récupére les informations dans la BDD

        final DejaVuManager dejaVuBDD = new DejaVuManager(getApplicationContext());
        dejaVuBDD.open();
        final DejaVu dejaVu = dejaVuBDD.getDejaVu();
        dejaVuBDD.close();


        // Gestion du spinner

        final Spinner spinner = (Spinner) findViewById(R.id.spinnerIconNumber);

        List<Integer> listeNombre = new ArrayList<>();
        listeNombre.add(6);
        listeNombre.add(24);
        listeNombre.add(96);

        ArrayAdapter<Integer> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listeNombre);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        int myValue = dejaVu.getNbIcone();
        final int spinnerPosition = adapter.getPosition(myValue);
        spinner.setSelection(spinnerPosition);


        // Gestion de la checkbox

        final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBoxDoublons);
        checkBox.setChecked(dejaVu.getDoublon());


        // Gestion des boutons

        // Listener sur le bouton "reset"
        Button btnReset = (Button) findViewById(R.id.buttonReset);

        btnReset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                spinner.setSelection(1);
                checkBox.setChecked(false);
            }
        });

        // Listener sur le bouton "Appliquer"
        Button btnAppliquer = (Button) findViewById(R.id.buttonAppliquer);

        btnAppliquer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dejaVuBDD.open();
                dejaVuBDD.updateConfiguration(dejaVu, Integer.parseInt(spinner.getSelectedItem().toString()), checkBox.isChecked());
                dejaVuBDD.close();
                Intent creation = new Intent(Configuration.this, Accueil.class);
                startActivity(creation);
            }
        });
    }

}
