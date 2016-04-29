package mbpl.graphical.passwords.passPoints;

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

import mbpl.graphical.passwords.Accueil;
import mbpl.graphical.passwords.R;
import mbpl.graphical.passwords.sqlite.Methode;
import mbpl.graphical.passwords.sqlite.MethodeManager;
import mbpl.graphical.passwords.sqlite.Passpoints;
import mbpl.graphical.passwords.utils.Tools;

public class Deverouillage extends AppCompatActivity {

    Float[] tabX = new Float[50];
    Float[] tabY = new Float[50];
    Integer number;
    String nomImage;

    ImageView iv;
    Bitmap bmp;
    Bitmap bmp2;
    float touchX;
    float touchY;
    Integer currentNumber = 0;

    float startTime;
    float difference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passpoints__deverouillage);
        final Toast mToast = Toast.makeText(Deverouillage.this, "", Toast.LENGTH_SHORT);

        /**** Récupére les infos dans la BDD ****/
        MethodeManager methodeManager = new MethodeManager(getApplicationContext());
        methodeManager.open();
        Methode methode = methodeManager.getMethode(new Passpoints());
        methodeManager.close();

        String[] motDePasse = Tools.stringToStringTable(methode.getMdp());
        System.out.println("Avant : " + methode.getMdp());

        number = (motDePasse.length - 1) / 2;
        for (int i = 0; i < number; i++) {
            tabX[i] = Float.parseFloat(motDePasse[i]);
            tabY[i] = Float.parseFloat(motDePasse[i+number]);
        }
        nomImage = motDePasse[number * 2];




        /**** ***************************** ****/

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
        bmp = decodeSampledBitmapFromResource(getResources(), getResources().getIdentifier(nomImage, "drawable", getPackageName()), screenWidth / 2, screenHeight / 2);

        bmp2 = Bitmap.createScaledBitmap(bmp, screenWidth, screenHeight, true);
        if (bmp != bmp2) {
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
                                    difference = difference / 1000000000;

                                    MethodeManager methodeManager = new MethodeManager(getApplicationContext());
                                    methodeManager.open();
                                    Methode methode = methodeManager.getMethode(new Passpoints());
                                    methodeManager.addTentativeReussie(methode, difference);
                                    methodeManager.close();

                                    mToast.setText("Mot de passe validé !");
                                    mToast.show();
                                    Intent appel = new Intent(Deverouillage.this, Accueil.class);
                                    startActivity(appel);
                                }
                            } else {
                                currentNumber = 0;

                                MethodeManager methodeManager = new MethodeManager(getApplicationContext());
                                methodeManager.open();
                                Methode methode = methodeManager.getMethode(new Passpoints());
                                methodeManager.addTentativeEchouee(methode);
                                methodeManager.close();

                                mToast.setText("Mot de passe incorrect\nVeuillez recommencer");
                                mToast.show();
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
    public void onDestroy() {
        super.onDestroy();
        bmp.recycle();
        bmp = null;
        bmp2.recycle();
        bmp2 = null;
    }



}
