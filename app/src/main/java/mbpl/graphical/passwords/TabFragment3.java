package mbpl.graphical.passwords;

/**
 * Created by pierre on 09/04/16.
 */

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

import static mbpl.graphical.passwords.ImplementedMethods.implementedMethods;


public class TabFragment3 extends Fragment {

    View rootView;
    RelativeLayout rlf3;
    ScrollView svf3;
    HorizontalScrollView hsvf3;
    GridLayout glf3;
    TextView tv;
    GridLayout.LayoutParams p;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.menu_tab_fragment_3, container, false);
        rlf3 = (RelativeLayout) rootView.findViewById(R.id.rlf3);
        svf3 = (ScrollView) rootView.findViewById(R.id.svf3);
        hsvf3 = (HorizontalScrollView) rootView.findViewById(R.id.hsvf3);
        glf3 = (GridLayout) rootView.findViewById(R.id.glf3);

        Point size = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(size);

        int columnCount = implementedMethods.size() + 1;
        glf3.setRowCount(15);
        glf3.setColumnCount(columnCount);

        for (int c = 0; c < columnCount; c++) {
            for (int l = 0; l < 15; l++) {

                tv = new TextView(getActivity());

                switch (l) {
                    case 0:
                        if (c == 0) {
                            tv.setText("Nom");
                        } else {
                            tv.setText(implementedMethods.get(c - 1).getNom());
                        }
                        break;
                    case 1:
                        if (c == 0) {
                            tv.setText("Categorie");
                        } else {
                            tv.setText(implementedMethods.get(c - 1).getCategorie());
                        }
                        break;
                    case 2:
                        if (c == 0) {
                            tv.setText("BruteForceAttack");
                        } else {
                            tv.setText(String.valueOf(implementedMethods.get(c - 1).getBruteForce()));
                        }
                        break;
                    case 3:
                        if (c == 0) {
                            tv.setText("DictionnaryAttack");
                        } else {
                            tv.setText(String.valueOf(implementedMethods.get(c - 1).getDictionaryAttack()));
                        }
                        break;
                    case 4:
                        if (c == 0) {
                            tv.setText("ShoulderSurfingAttack");
                        } else {
                            tv.setText(String.valueOf(implementedMethods.get(c - 1).getShoulderSurfing()));
                        }
                        break;
                    case 5:
                        if (c == 0) {
                            tv.setText("SmudgeAttack");
                        } else {
                            tv.setText(String.valueOf(implementedMethods.get(c - 1).getSmudgeAttack()));
                        }
                        break;
                    case 6:
                        if (c == 0) {
                            tv.setText("EyeTrackingAttack");
                        } else {
                            tv.setText(String.valueOf(implementedMethods.get(c - 1).getEyeTracking()));
                        }
                        break;
                    case 7:
                        if (c == 0) {
                            tv.setText("SpyWareAttack");
                        } else {
                            tv.setText(String.valueOf(implementedMethods.get(c - 1).getSpyWare()));
                        }
                        break;
                    case 8:
                        if (c == 0) {
                            tv.setText("EspaceMDP");
                        } else {
                            tv.setText(String.valueOf(implementedMethods.get(c - 1).getEspaceMdp()));
                        }
                        break;
                    case 9:
                        if (c == 0) {
                            tv.setText("IndiceSecurite");
                        } else {
                            tv.setText(String.valueOf(implementedMethods.get(c - 1).getIndiceSecurite()));
                        }
                        break;
                    case 10:
                        if (c == 0) {
                            tv.setText("Apprentissage");
                        } else {
                            tv.setText(String.valueOf(implementedMethods.get(c - 1).getApprentissage()));
                        }
                        break;
                    case 11:
                        if (c == 0) {
                            tv.setText("Memorisation");
                        } else {
                            tv.setText(String.valueOf(implementedMethods.get(c - 1).getMemorisation()));
                        }
                        break;
                    case 12:
                        if (c == 0) {
                            tv.setText("Temps");
                        } else {
                            tv.setText(String.valueOf(implementedMethods.get(c - 1).getTemps()));
                        }
                        break;
                    case 13:
                        if (c == 0) {
                            tv.setText("Satisfaction");
                        } else {
                            tv.setText(String.valueOf(implementedMethods.get(c - 1).getSatisfaction()));
                        }
                        break;
                    case 14:
                        if (c == 0) {
                            tv.setText("IndiceUtilisabilite");
                        } else {
                            tv.setText(String.valueOf(implementedMethods.get(c - 1).getIndiceUtilisabilite()));
                        }
                        break;
                }

                p = new GridLayout.LayoutParams();
                p.setMargins(10, 5, 10, 5);
                p.setGravity(Gravity.CENTER);
                p.columnSpec = GridLayout.spec(c);
                p.rowSpec = GridLayout.spec(l);
                tv.setLayoutParams(p);
                glf3.addView(tv);
            }
        }

        return rootView;
    }

}