package mbpl.graphical.passwords;

/**
 * Created by pierre on 09/04/16.
 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import mbpl.graphical.passwords.customList.CustomList;
import mbpl.graphical.passwords.passPoints.ChoixImage;
import mbpl.graphical.passwords.passPoints.Deverouillage;
import mbpl.graphical.passwords.sqlite.DejaVu;
import mbpl.graphical.passwords.sqlite.Methode;
import mbpl.graphical.passwords.sqlite.MethodeManager;
import mbpl.graphical.passwords.sqlite.Passfaces;
import mbpl.graphical.passwords.sqlite.Passpoint;

import java.util.ArrayList;

public class TabFragment1 extends Fragment {

    View rootView;

    ArrayList<TypeAuthentification> tab = new ArrayList<TypeAuthentification>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.menu_tab_fragment_1, container, false);

        String descPasspoints = "- Choisir une image.\n- Choisir au moins un point à repérer sur cette image.\n- Faire 'Suivant'.\n- Retrouver ces points.";
        String descDejaVu = "- Choisir une suite d'images.\n" +
                "- Appuyer sur 'Valider'.\n" +
                "- Cliquer sur les images dans l'ordre pour vous authentifier.";
        String descPassfaces = "- Choisir une suite d'images.\n" +
                "- Appuyer sur 'Valider'.\n" +
                "- Cliquer sur les images dans l'ordre pour vous authentifier.";

        // TODO AJOUTER VOTRE METHODE A CETTE LISTE (une ligne à ajouter + description au dessus)
        tab.add(new TypeAuthentification("Passpoints", descPasspoints, 1, ChoixImage.class, Deverouillage.class, new Passpoint()));
        tab.add(new TypeAuthentification("Déjà Vu", descDejaVu, 2, mbpl.graphical.passwords.dejaVu.Accueil.class, mbpl.graphical.passwords.dejaVu.Accueil.class, new DejaVu()));
        tab.add(new TypeAuthentification("Passfaces", descPassfaces, 3, mbpl.graphical.passwords.passfaces.Creation.class, mbpl.graphical.passwords.passfaces.Authentification.class, new Passfaces()));
        tab.add(new TypeAuthentification("Passpoints", descPasspoints, 1, ChoixImage.class, Deverouillage.class, new Passpoint()));
        tab.add(new TypeAuthentification("Déjà Vu", descDejaVu, 2, mbpl.graphical.passwords.dejaVu.Accueil.class, mbpl.graphical.passwords.dejaVu.Accueil.class, new DejaVu()));
        tab.add(new TypeAuthentification("Passfaces", descPassfaces, 3, mbpl.graphical.passwords.passfaces.Creation.class, mbpl.graphical.passwords.passfaces.Authentification.class, new Passfaces()));
        tab.add(new TypeAuthentification("Passpoints", descPasspoints, 1, ChoixImage.class, Deverouillage.class, new Passpoint()));
        tab.add(new TypeAuthentification("Déjà Vu", descDejaVu, 2, mbpl.graphical.passwords.dejaVu.Accueil.class, mbpl.graphical.passwords.dejaVu.Accueil.class, new DejaVu()));
        tab.add(new TypeAuthentification("Passfaces", descPassfaces, 3, mbpl.graphical.passwords.passfaces.Creation.class, mbpl.graphical.passwords.passfaces.Authentification.class, new Passfaces()));


        Point size = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(size);

        ListView list;
        final String[] listeNomsMdp = new String[tab.size()];
        for (int m = 0; m < tab.size(); m++) {
            listeNomsMdp[m] = tab.get(m).getNom();
        }
        int sec = 3;
        int usa = 2;
        CustomList adapter = new CustomList(getActivity(), listeNomsMdp, sec, usa);
        list = (ListView) rootView.findViewById(R.id.mdpListView);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showAlertDescription(view, position);
            }
        });

        return rootView;
    }


    public void showAlertDescription(View v, final int j) {

        AlertDialog.Builder myAlert = new AlertDialog.Builder(getActivity());
        myAlert.setMessage(tab.get(j).getDesc())
                .setPositiveButton("Essayer !", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent appel;
                        appel = new Intent(getActivity(), tab.get(j).getCreation());
                        startActivity(appel);
                    }
                })
                .setNegativeButton("Reprendre", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent appel;
                        Methode m;
                        MethodeManager mm = new MethodeManager(getContext());
                        mm.open();
                        m = mm.getMethode(tab.get(j).getMethode());
                        if (!mm.defaultPassword(m)) {
                            appel = new Intent(getActivity(), tab.get(j).getAuthentification());
                        } else {
                            appel = new Intent(getActivity(), tab.get(j).getCreation());
                        }
                        mm.close();
                        startActivity(appel);
                    }
                })
                .setTitle(tab.get(j).getNom()).create();

        myAlert.show();
    }
}