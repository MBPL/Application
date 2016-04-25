package com.example.pierre.MBPL;

/**
 * Created by pierre on 09/04/16.
 */
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.example.pierre.MBPL.DejaVu.*;
import com.example.pierre.MBPL.passPoints.Passpoints_ChoixImage;

import java.util.ArrayList;

public class TabFragment1 extends Fragment {

    View rootView;
    ScrollView myScrollView_tab1;
    GridLayout myLayout_tab1;
    ArrayList<TypeAuthentification> tab = new ArrayList<TypeAuthentification>();
    Bitmap bmp;
    Bitmap bmp2;
    ImageView iv;
    GridLayout.LayoutParams param;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.tab_fragment_1, container, false);

        myScrollView_tab1 = (ScrollView) rootView.findViewById(R.id.container_scroll_view_tab1);
        myLayout_tab1 = (GridLayout) rootView.findViewById(R.id.container_tab1);

        String descPasspoints = "- Choisir une image.\n- Choisir au moins un point à repérer sur cette image.\n- Faire 'Suivant'.\n- Retrouver ces points.";

        tab.add(new TypeAuthentification("Passpoints", "passpoints96x96", descPasspoints, 1, 0, 0));
        tab.add(new TypeAuthentification("Déjà Vu", "icon96x96_2", "desc deja vu", 2, 0, 0));
        tab.add(new TypeAuthentification("mdp3", "icon96x96_3", "d", 3, 0, 0));
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


        int l=0;
        int c=0;
        for(int i=0; i<tab.size(); i++){
            final int j = i;

            //Toast.makeText(Accueil.this, "" + myLayout_tab1.getColumnCount(), Toast.LENGTH_SHORT).show();

            iv = new ImageView(getActivity());
            bmp = decodeSampledBitmapFromResource(getResources(), getID_MDP(i, tab.get(i)), 400, 400);
            bmp2 = Bitmap.createScaledBitmap(bmp, 400, 400, true);
            if(bmp!=bmp2){
                bmp.recycle();
            }
            iv.setImageBitmap(bmp2);

            param = new GridLayout.LayoutParams();
            param.height = 250;
            param.width = screenWidth / 2;
            param.setMargins(0, 0, 0, 0);
            param.setGravity(Gravity.CENTER);
            param.columnSpec = GridLayout.spec(c);
            param.rowSpec = GridLayout.spec(l);
            iv.setLayoutParams(param);



            iv.setOnClickListener(new ImageView.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showAlertDescription(v,j);

                }
            });

            myLayout_tab1.addView(iv);



            c++;
            if(c>1) {
                c = 0;
                l++;
            }
            System.out.println("c = "+c+" l = "+l);
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
                        switch (tab.get(j).getNom()){
                            case "Passpoints":
                                appel = new Intent(getActivity(), Passpoints_ChoixImage.class);
                                break;
                            case "Déjà Vu":
                                appel = new Intent(getActivity(), com.example.pierre.MBPL.DejaVu.Accueil.class);
                                break;
                            default:
                                appel = new Intent(getActivity(), Passpoints_ChoixImage.class);
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