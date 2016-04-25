package com.example.pierre.MBPL.DejaVu;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import com.example.pierre.MBPL.R;

/**
 * Created by benja135 on 06/03/16.
 * Configuration avancé de la méthode Déjà Vu.
 * TODO :
 * - nombre d'icônes afficher à l'écran (pour faciliter la localisation, ou augmenter la sécurité) -> phase d'authentification
 * - permettre de mettre plusieurs fois un même icône ou pas
 */
public class Configuration extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configurations_deja_vu);

        // Gestion du spinner

        Spinner spinner = (Spinner) findViewById(R.id.spinnerIconNumber);

        List<Integer> listeNombre = new ArrayList<>();
        listeNombre.add(6);
        listeNombre.add(24);
        listeNombre.add(96);

        ArrayAdapter<Integer> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listeNombre);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

}
