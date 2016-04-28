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

import mbpl.graphical.passwords.utils.CustomList;
import mbpl.graphical.passwords.sqlite.Methode;
import mbpl.graphical.passwords.sqlite.MethodeManager;

import static mbpl.graphical.passwords.ImplementedMethods.implementedMethods;

public class TabFragment1 extends Fragment {

    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.menu_tab_fragment_1, container, false);

        Point size = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(size);

        ListView list;
        final String[] listeNomsMdp = new String[implementedMethods.size()];
        for (int m = 0; m < implementedMethods.size(); m++) {
            listeNomsMdp[m] = implementedMethods.get(m).getNom();
        }

        CustomList adapter = new CustomList(getActivity(), listeNomsMdp);
        list = (ListView) rootView.findViewById(R.id.mdpListView);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showAlertDescription(position);
            }
        });

        return rootView;
    }


    public void showAlertDescription(final int j) {

        AlertDialog.Builder myAlert = new AlertDialog.Builder(getActivity());
        myAlert.setMessage(implementedMethods.get(j).getDescription())
                .setPositiveButton("Essayer !", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent appel;
                        appel = new Intent(getActivity(), implementedMethods.get(j).getCreation());
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
                        m = mm.getMethode(implementedMethods.get(j));
                        if (!mm.defaultPassword(m)) {
                            appel = new Intent(getActivity(), implementedMethods.get(j).getAuthentification());
                        } else {
                            appel = new Intent(getActivity(), implementedMethods.get(j).getCreation());
                        }
                        mm.close();
                        startActivity(appel);
                    }
                })
                .setTitle(implementedMethods.get(j).getNom()).create();

        myAlert.show();
    }
}