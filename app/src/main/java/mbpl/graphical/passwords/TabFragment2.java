package mbpl.graphical.passwords;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mbpl.graphical.passwords.sqlite.DejaVu;
import mbpl.graphical.passwords.sqlite.Methode;
import mbpl.graphical.passwords.sqlite.MethodeManager;
import mbpl.graphical.passwords.sqlite.Passfaces;
import mbpl.graphical.passwords.sqlite.Passpoint;

public class TabFragment2 extends Fragment {

    View rootView;
    RelativeLayout rlf2;
    ScrollView svf2;
    HorizontalScrollView hsvf2;
    GridLayout glf2;
    TextView tv;
    GridLayout.LayoutParams p;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.menu_tab_fragment_2, container, false);
        rlf2 = (RelativeLayout) rootView.findViewById(R.id.rlf2);
        svf2 = (ScrollView) rootView.findViewById(R.id.svf2);
        hsvf2 = (HorizontalScrollView) rootView.findViewById(R.id.hsvf2);
        glf2 = (GridLayout) rootView.findViewById(R.id.glf2);

        glf2.setRowCount(15);
        glf2.setColumnCount(4);

        Point size = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(size);

        MethodeManager methodeManager = new MethodeManager(getContext());
        methodeManager.open();

        List<Methode> methodeList = new ArrayList<Methode>();

        if (methodeManager.exist(new Passpoint())) {
            methodeList.add(methodeManager.getMethode(new Passpoint()));
        }
        if (methodeManager.exist(new DejaVu())) {
            methodeList.add(methodeManager.getMethode(new DejaVu()));
        }
        if (methodeManager.exist(new Passfaces())) {
            methodeList.add(methodeManager.getMethode(new Passfaces()));
        }
        methodeManager.close();


        for (int c = 0; c < methodeList.size() + 1; c++) {
            for (int l = 0; l < 4; l++) {

                tv = new TextView(getActivity());

                switch (l) {
                    case 0:
                        if (c == 0) {
                            tv.setText("Nom");
                        } else {
                            tv.setText(methodeList.get(c - 1).getNom());
                        }
                        break;
                    case 1:
                        if (c == 0) {
                            tv.setText("Temps moyen");
                        } else {
                            tv.setText(String.valueOf((float)((int)( methodeList.get(c - 1).getTemps_auth_moyen() *100f ))/100f));
                        }
                        break;
                    case 2:
                        if (c == 0) {
                            tv.setText("Echecs");
                        } else {
                            tv.setText(String.valueOf(methodeList.get(c - 1).getNb_tentative_echouee()));
                        }
                        break;
                    case 3:
                        if (c == 0) {
                            tv.setText("Réussites");
                        } else {
                            tv.setText(String.valueOf(methodeList.get(c - 1).getNb_tentative_reussie()));
                        }
                        break;
                }

                p = new GridLayout.LayoutParams();
                p.setMargins(10, 5, 10, 5);
                p.setGravity(Gravity.CENTER);
                p.columnSpec = GridLayout.spec(c);
                p.rowSpec = GridLayout.spec(l);
                tv.setLayoutParams(p);
                glf2.addView(tv);

            }
        }

        return rootView;
    }

}