package mbpl.graphical.passwords;

/**
 * Created by pierre on 09/04/16.
 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import mbpl.graphical.passwords.customList.CustomList;
import mbpl.graphical.passwords.customProgressBar.CustomProgress;
import mbpl.graphical.passwords.passPoints.ChoixImage;

import java.util.ArrayList;
import java.util.List;

public class TabFragment1 extends Fragment {

    View rootView;
    //ScrollView myScrollView_tab1;
    //GridLayout myLayout_tab1;
    ArrayList<TypeAuthentification> tab = new ArrayList<TypeAuthentification>();
    Bitmap bmp;
    Bitmap bmp2;
    //ImageView iv;
    TextView tv;
    private ListView mdpLV;
    private ArrayAdapter arrayAdapter;
    //GridLayout.LayoutParams param;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.menu_tab_fragment_1, container, false);

        //myScrollView_tab1 = (ScrollView) rootView.findViewById(R.id.container_scroll_view_tab1);
        //myLayout_tab1 = (GridLayout) rootView.findViewById(R.id.container_tab1);
        mdpLV = (ListView) rootView.findViewById(R.id.mdpListView);




        String descPasspoints = "- Choisir une image.\n- Choisir au moins un point à repérer sur cette image.\n- Faire 'Suivant'.\n- Retrouver ces points.";
        String descDejaVu = "- Choisir une suite d'images.\n" +
                "- Appuyer sur 'Valider'.\n" +
                "- Cliquer sur les images dans l'ordre pour vous authentifier";
        String descPassfaces = "- Choisir une suite d'images.\n" +
                "- Appuyer sur 'Valider'.\n" +
                "- Cliquer sur les images dans l'ordre pour vous authentifier";

        // TODO faire les images en bandeau
        tab.add(new TypeAuthentification("Passpoints", "passpoints96x96", descPasspoints, 1, 0, 0));
        tab.add(new TypeAuthentification("Déjà Vu", "icon96x96_2", descDejaVu, 2, 0, 0));
        tab.add(new TypeAuthentification("Passfaces", "icon96x96_3", descPassfaces, 3, 0, 0));
        tab.add(new TypeAuthentification("mdp4", "icon96x96_4", "d", 4, 0, 0));
        tab.add(new TypeAuthentification("mdp5", "icon96x96_5", "d", 5, 0, 0));
        tab.add(new TypeAuthentification("mdp5", "icon96x96_5", "d", 5, 0, 0));
        tab.add(new TypeAuthentification("mdp5", "icon96x96_5", "d", 5, 0, 0));
        tab.add(new TypeAuthentification("mdp5", "icon96x96_5", "d", 5, 0, 0));
        tab.add(new TypeAuthentification("mdp5", "icon96x96_5", "d", 5, 0, 0));
        tab.add(new TypeAuthentification("mdp5", "icon96x96_5", "d", 5, 0, 0));
        tab.add(new TypeAuthentification("mdp5", "icon96x96_5", "d", 5, 0, 0));
        tab.add(new TypeAuthentification("mdp5", "icon96x96_5", "d", 5, 0, 0));
        tab.add(new TypeAuthentification("mdp5", "icon96x96_5", "d", 5, 0, 0));
        tab.add(new TypeAuthentification("mdp5", "icon96x96_5", "d", 5, 0, 0));


        Point size = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(size);
        int screenWidth = size.x;
        int screenHeight = size.y - getStatusBarHeight();


        /*int l = 0;
        int c = 0;
        for (int i = 0; i < tab.size(); i++) {
            final int j = i;*/

            //Toast.makeText(Accueil.this, "" + myLayout_tab1.getColumnCount(), Toast.LENGTH_SHORT).show();


            /*bmp = decodeSampledBitmapFromResource(getResources(), getID_MDP(i, tab.get(i)), 400, 400);
            bmp2 = Bitmap.createScaledBitmap(bmp, 400, 400, true);
            if (bmp != bmp2) {
                bmp.recycle();
            }
            iv.setImageBitmap(bmp2);*/

            /*iv = new ImageView(getActivity());
            iv.setBackgroundColor(tab.get(j).getColor());
            param = new GridLayout.LayoutParams();
            param.height = screenHeight/6;
            param.width = screenWidth;
            param.setMargins(0, 0, 0, 0);
            param.setGravity(Gravity.CENTER);
            param.columnSpec = GridLayout.spec(c);
            param.rowSpec = GridLayout.spec(l);
            iv.setLayoutParams(param);

            iv.setOnClickListener(new ImageView.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showAlertDescription(v, j);

                }
            });
            myLayout_tab1.addView(iv);*/

            /*myLayout_tab1.setBackgroundColor(tab.get(j).getColor());


            tv = new TextView(getActivity());
            tv.setText(tab.get(j).getNom());
            tv.setTextColor(Color.BLACK);
            tv.setGravity(Gravity.LEFT);
            tv.setTextSize(20);

            myLayout_tab1.addView(tv);*/




            /*String[] mdpNameArray = new String[tab.size()];
            for(int m=0; m<tab.size(); m++) {
                mdpNameArray[m] = tab.get(m).getNom();
            }
            arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, mdpNameArray);
            mdpLV.setAdapter(arrayAdapter);
            CustomProgress cp = new CustomProgress(getContext());
            mdpLV.addView(cp);*/

        ListView list;
        final String[] listeNomsMdp = new String[tab.size()];
        for(int m=0; m<tab.size(); m++) {
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





            //l++;
        //}

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

    private int getID_MDP(int i, TypeAuthentification mdp) {
        int id = getResources().getIdentifier(mdp.getImage(), "drawable", getActivity().getPackageName());
        return id;
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;

    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public void showAlertDescription(View v, final int j) {
        AlertDialog.Builder myAlert = new AlertDialog.Builder(getActivity());
        myAlert.setMessage(tab.get(j).getDesc())
                .setPositiveButton("Essayer !", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent appel;
                        switch (tab.get(j).getNom()) {
                            case "Passpoints":
                                appel = new Intent(getActivity(), ChoixImage.class);
                                break;
                            case "Déjà Vu":
                                appel = new Intent(getActivity(), mbpl.graphical.passwords.dejaVu.Accueil.class);
                                break;
                            case "Passfaces":
                                appel = new Intent(getActivity(), mbpl.graphical.passwords.passfaces.Authentification.class);
                                break;
                            default:
                                appel = new Intent(getActivity(), ChoixImage.class);
                                break;
                        }
                        startActivity(appel);
                    }
                })
                .setTitle(tab.get(j).getNom())
                .create();
        myAlert.show();
    }


}