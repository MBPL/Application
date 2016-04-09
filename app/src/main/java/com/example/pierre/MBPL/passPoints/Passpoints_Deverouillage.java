package com.example.pierre.MBPL.passPoints;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pierre.MBPL.Accueil;
import com.example.pierre.MBPL.R;

public class Passpoints_Deverouillage extends AppCompatActivity {

    Bundle b33;
    float[] tabX = new float[50];
    float[] tabY = new float[50];
    Integer number;
    String nomImage;

    ImageView iv;
    Bitmap bmp;
    Bitmap bmp2;
    float touchX;
    float touchY;
    Integer currentNumber = 0;
    Integer nbrEssais = 1;

    float startTime;
    float difference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passpoints__deverouillage);

        b33 = getIntent().getExtras();
        tabX = b33.getFloatArray("tabX");
        tabY = b33.getFloatArray("tabY");
        number = b33.getInt("number");
        nomImage = b33.getString("nomImage");

        iv = (ImageView) findViewById(R.id.imageviewMain);

        //cacher barres
        android.support.v7.app.ActionBar toolbar = getSupportActionBar();
        if (toolbar != null) {
            toolbar.hide();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //calculer tailles ecran
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int screenWidth = size.x;
        int screenHeight = size.y;

        //ajouter image
        bmp = decodeSampledBitmapFromResource(getResources(), getResources().getIdentifier(nomImage, "drawable", getPackageName()), screenWidth/2, screenHeight/2);

        bmp2 = Bitmap.createScaledBitmap(bmp, screenWidth, screenHeight, true);
        if(bmp!=bmp2){
            bmp.recycle();
        }

        Canvas c = new Canvas(bmp2);
        iv.setImageBitmap(bmp2);

        iv.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        touchX = event.getX();
                        touchY = event.getY();
                        if (currentNumber < number) {
                            if ((touchX - tabX[currentNumber]) * (touchX - tabX[currentNumber]) + (touchY - tabY[currentNumber]) * (touchY - tabY[currentNumber]) <= 100 * 100) {
                                currentNumber++;
                                if (currentNumber == number) {
                                    difference = System.nanoTime() - startTime;
                                    difference = difference/1000000000;
                                    Toast.makeText(Passpoints_Deverouillage.this, "Mot de passe validé !", Toast.LENGTH_SHORT).show();
                                    Intent appel = new Intent(Passpoints_Deverouillage.this, Accueil.class);
                                    Bundle bStats = new Bundle();
                                    bStats.putInt("nbrEssais", nbrEssais);
                                    bStats.putString("nomMDP", "Passpoints");
                                    bStats.putFloat("tempsDeverouillage", difference);
                                    appel.putExtras(bStats);
                                    startActivity(appel);
                                }
                            } else {
                                currentNumber = 0;
                                nbrEssais++;
                                Toast.makeText(Passpoints_Deverouillage.this, "Mot de passe incorrect\nVeuillez recommencer", Toast.LENGTH_SHORT).show();
                            }
                        }
                        break;
                    default:
                        break;
                }

                return false;
            }
        });

        startTime = System.nanoTime();



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


    @Override
    public void onDestroy(){
        super.onDestroy();
        bmp.recycle();
        bmp = null;
        bmp2.recycle();
        bmp2 = null;
    }

}
