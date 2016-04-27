package mbpl.graphical.passwords.passPoints;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import mbpl.graphical.passwords.R;
import mbpl.graphical.passwords.sqlite.MethodeManager;
import mbpl.graphical.passwords.sqlite.Passpoint;

public class ChoixPoints extends AppCompatActivity {

    Integer number = 0;
    ImageView iv;
    Button b;
    Bitmap bmp;
    Bitmap bmp2;
    float touchX;
    float touchY;
    List<Float> tabX = new ArrayList<Float>();
    List<Float> tabY = new ArrayList<Float>();
    Bundle b22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passpoints__choix_points);

        iv = (ImageView) findViewById(R.id.imageviewMain);
        b = (Button) findViewById(R.id.button);
        b22 = getIntent().getExtras();


        //bouton
        b.setText("Valider");
        b.setVisibility(View.GONE);

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
        bmp = decodeSampledBitmapFromResource(getResources(), getResources().getIdentifier(b22.getString("nomImage"), "drawable", getPackageName()), screenWidth / 2, screenHeight / 2);

        bmp2 = Bitmap.createScaledBitmap(bmp, screenWidth, screenHeight, true);
        if (bmp != bmp2) {
            bmp.recycle();
        }

        Canvas c = new Canvas(bmp2);

        iv.setImageBitmap(bmp2);

        iv.setOnTouchListener(new View.OnTouchListener() {


            public boolean onTouch(View v, MotionEvent event) {
                //Toast.makeText(ChoixPoints.this, b22.getString("nomImage"), Toast.LENGTH_SHORT).show();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        touchX = event.getX();
                        touchY = event.getY();
                        tabX.add(touchX);
                        tabY.add(touchY);
                        iv.setImageBitmap(drawCircle());
                        break;
                    default:
                        break;
                }
                if (number == 1)
                    b.setVisibility(View.VISIBLE);
                return false;
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent appel = new Intent(ChoixPoints.this, Deverouillage.class);

                for (int i = 0; i < number; i++) {
                    tabX.add(tabY.get(i));
                }

                // On met les coordonnées X et Y et le nom de l'image à la suite dans un tableau.
                List<String> myMdp = new ArrayList<String>();
                for (int i = 0; i < number * 2; i++) {
                    myMdp.add(String.valueOf(tabX.get(i)));
                }
                myMdp.add(b22.getString("nomImage"));

                MethodeManager mm = new MethodeManager(getApplicationContext());
                mm.open();
                mm.setPassword(new Passpoint(), myMdp.toString());
                mm.close();

                startActivity(appel);
            }
        });
    }

    private Bitmap drawCircle() {
        Bitmap bitmap = bmp2.copy(Bitmap.Config.ARGB_4444, true);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        //paint.setStyle(Paint.Style.STROKE);

        paint.setColor(Color.RED);
        paint.setAlpha(125);
        paint.setStrokeWidth(50);
        canvas.drawCircle(touchX, touchY, 100, paint);

        paint.setColor(Color.WHITE);
        paint.setAlpha(255);
        paint.setStrokeWidth(0);
        paint.setTextSize(50);
        number++;
        canvas.drawText(number.toString(), touchX - 17, touchY + 20, paint);

        return bitmap;
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bmp.recycle();
        bmp = null;
        bmp2.recycle();
        bmp2 = null;
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
}
