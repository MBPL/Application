package mbpl.graphical.passwords;

/**
 * Created by pierre on 09/04/16.
 */

import android.graphics.LinearGradient;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import static mbpl.graphical.passwords.ImplementedMethods.implementedMethods;


public class TabFragment3 extends Fragment {

    View rootView;
    LinearLayout rlf3;
    GridLayout glf3;
    TextView tv;
    GridLayout.LayoutParams p;
    TextView tvLeg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.menu_tab_fragment_3, container, false);
        rlf3 = (LinearLayout) rootView.findViewById(R.id.rlf3);
        glf3 = (GridLayout) rootView.findViewById(R.id.glf3);
        tvLeg = (TextView) rootView.findViewById(R.id.textViewLegende);

        String legende = "Légende :" +
                "\n Sécurité" +
                "\n - 0 = Le mot de passe est très vulnérable à l'attaque" +
                "\n - 2 = Le mot de passe est moyennement résistant à l'attaque" +
                "\n - 3 = Le mot de passe est très résistant à l'attaque" +
                "\n - 5 = Le mot de passe est invulnérable à l'attaque" +
                "\n Espace de mot de passe" +
                "\n - 0 = Entre 1 et 1000." +
                "\n - 1 = Entre 1000 et 1e6." +
                "\n - 2 = Entre 1e6 et 1e15" +
                "\n - 3 = Entre 1e15 et plus" +
                "\n Apprentissage" +
                "\n - 0 = Il est très difficile et très lent à apprendre" +
                "\n - 2 = Il sera appris apès un certain temps" +
                "\n - 3 = Il est facilement assimilable, il suffit de le faire une à deux fois" +
                "\n - 5 = L'apprentissage est immédiat" +
                "\n Mémorisation" +
                "\n - 0 = L'utilisateur oublie son mdp rapidement" +
                "\n - 2 = L'utilisateur peut oublier son mdp au bout de quelques temps" +
                "\n - 3 = Il devient difficile pour l'utilisateur d'oublier son mdp" +
                "\n - 5 = L'utilisateur se souvient toujours de son mdp" +
                "\n Temps déverouillage" +
                "\n - 0 = Très lent, met 1min ou plus" +
                "\n - 2 = Assez lent, met entre 30 sec et 1 min" +
                "\n - 3 = Rapide, met moins de 10 secondes" +
                "\n - 5 = Très rapide, met moins de 2 secondes" +
                "\n Satisfaction" +
                "\n - 0 = Pas du tout pratique" +
                "\n - 2 = Assez pratique" +
                "\n - 3 = Pratique" +
                "\n - 5 = Très pratique";
        tvLeg.setText(legende);
        //rlf3.addView(tvLeg);

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
                tv.setTextSize(20);
                glf3.addView(tv);
            }
        }

        return rootView;
    }

}