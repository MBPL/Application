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

import java.util.zip.Deflater;

import mbpl.graphical.passwords.R;
import mbpl.graphical.passwords.sqlite.DejaVu;
import mbpl.graphical.passwords.sqlite.Passpoint;

public class TabFragment3 extends Fragment {

    View rootView;
    RelativeLayout rlf3;
    ScrollView svf3;
    HorizontalScrollView hsvf3;
    TextView tv;
    GridLayout.LayoutParams p;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.menu_tab_fragment_3, container, false);
        rlf3 = (RelativeLayout) rootView.findViewById(R.id.rlf3);
        svf3 = (ScrollView) rootView.findViewById(R.id.svf3);
        hsvf3 = (HorizontalScrollView) rootView.findViewById(R.id.hsvf3);


        Point size = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(size);
        int screenWidth = size.x;
        int screenHeight = size.y - getStatusBarHeight();

        Passpoint pp = new Passpoint();
        DejaVu dv = new DejaVu();
        Passfaces pf = new Passfaces();

        int i = 0;
        for (int c = 0; c < 4; c++) {
            for (int l = 0; l < 15; l++) {
                final int j = i;

                tv = new TextView(getActivity());

                switch(l){
                    case 0:
                        switch(c) {
                            case 0:
                                tv.setText(pp.getNom());
                                break;
                            case 1:
                                tv.setText(dv.getNom());
                                break;
                            case 2:
                                tv.setText(pf.getNom());
                                break;
                        }
                        break;
                    case 1:
                        switch(c) {
                            case 0:
                                tv.setText(pp.getCategorie());
                                break;
                            case 1:
                                tv.setText(dv.getCategorie());
                                break;
                            case 2:
                                tv.setText(pf.getCategorie());
                                break;
                        }
                        break;
                    case 2:
                        switch(c) {
                            case 0:
                                tv.setText(pp.getBruteForce());
                                break;
                            case 1:
                                tv.setText(dv.getBruteForce());
                                break;
                            case 2:
                                tv.setText(pf.getBruteForce());
                                break;
                        }
                        break;
                    case 3:
                        switch(c) {
                            case 0:
                                tv.setText(pp.getDictionaryAttack());
                                break;
                            case 1:
                                tv.setText(dv.getDictionaryAttack());
                                break;
                            case 2:
                                tv.setText(pf.getDictionaryAttack());
                                break;
                        }
                        break;
                    case 4:
                        switch(c) {
                            case 0:
                                tv.setText(pp.getShoulderSurfing());
                                break;
                            case 1:
                                tv.setText(dv.getShoulderSurfing());
                                break;
                            case 2:
                                tv.setText(pf.getShoulderSurfing());
                                break;
                        }
                        break;
                    case 5:
                        switch(c) {
                            case 0:
                                tv.setText(pp.getSmudgeAttack());
                                break;
                            case 1:
                                tv.setText(dv.getSmudgeAttack());
                                break;
                            case 2:
                                tv.setText(pf.getSmudgeAttack());
                                break;
                        }
                        break;
                    case 6:
                        switch(c) {
                            case 0:
                                tv.setText(pp.getEyeTracking());
                                break;
                            case 1:
                                tv.setText(dv.getEyeTracking());
                                break;
                            case 2:
                                tv.setText(pf.getEyeTracking());
                                break;
                        }
                        break;
                    case 7:
                        switch(c) {
                            case 0:
                                tv.setText(pp.getSpyWare());
                                break;
                            case 1:
                                tv.setText(dv.getSpyWare());
                                break;
                            case 2:
                                tv.setText(pf.getSpyWare());
                                break;
                        }
                        break;
                    case 8:
                        switch(c) {
                            case 0:
                                tv.setText(pp.getEspaceMdp());
                                break;
                            case 1:
                                tv.setText(dv.getEspaceMdp());
                                break;
                            case 2:
                                tv.setText(pf.getEspaceMdp());
                                break;
                        }
                        break;
                    case 9:
                        switch(c) {
                            case 0:
                                tv.setText(""+pp.getIndiceSecurite());
                                break;
                            case 1:
                                tv.setText(""+dv.getIndiceSecurite());
                                break;
                            case 2:
                                tv.setText(""+pf.getIndiceSecurite());
                                break;
                        }
                        break;
                    case 10:
                        switch(c) {
                            case 0:
                                tv.setText(pp.getApprentissage());
                                break;
                            case 1:
                                tv.setText(dv.getApprentissage());
                                break;
                            case 2:
                                tv.setText(pf.getApprentissage());
                                break;
                        }
                        break;
                    case 11:
                        switch(c) {
                            case 0:
                                tv.setText(pp.getMemorisation());
                                break;
                            case 1:
                                tv.setText(dv.getMemorisation());
                                break;
                            case 2:
                                tv.setText(pf.getMemorisation());
                                break;
                        }
                        break;
                    case 12:
                        switch(c) {
                            case 0:
                                tv.setText(pp.getTemps());
                                break;
                            case 1:
                                tv.setText(dv.getTemps());
                                break;
                            case 2:
                                tv.setText(pf.getTemps());
                                break;
                        }
                        break;
                    case 13:
                        switch(c) {
                            case 0:
                                tv.setText(pp.getSatisfaction());
                                break;
                            case 1:
                                tv.setText(dv.getSatisfaction());
                                break;
                            case 2:
                                tv.setText(pf.getSatisfaction());
                                break;
                        }
                        break;
                    case 14:
                        switch(c) {
                            case 0:
                                tv.setText("" + pp.getIndiceUtilisabilite());
                                break;
                            case 1:
                                tv.setText(""+dv.getIndiceUtilisabilite());
                                break;
                            case 2:
                                tv.setText(""+pf.getIndiceUtilisabilite());
                                break;
                        }
                        break;
                }



                p = new GridLayout.LayoutParams();
                p.height = screenHeight / 6;
                p.width = screenWidth;
                p.setMargins(0, 0, 0, 0);
                p.setGravity(Gravity.CENTER);
                p.columnSpec = GridLayout.spec(c);
                p.rowSpec = GridLayout.spec(l);
                tv.setLayoutParams(p);
                rlf3.addView(tv);
            }
        }

        return rootView;
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}