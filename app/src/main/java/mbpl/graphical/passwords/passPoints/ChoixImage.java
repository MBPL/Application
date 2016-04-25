package mbpl.graphical.passwords.passPoints;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import mbpl.graphical.passwords.R;

import java.util.ArrayList;

public class ChoixImage extends AppCompatActivity {

    ScrollView myScrollView;
    GridLayout myLayout;
    ArrayList<String> tabImages = new ArrayList<String>();
    Point size;
    ImageView iv;
    Bitmap bmp;
    Bitmap bmp2;
    GridLayout.LayoutParams param;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passpoints__choix_image);

        myScrollView = (ScrollView) findViewById(R.id.container_scroll_view);
        myLayout = (GridLayout) findViewById(R.id.container);


        tabImages.add("passpoints1920x1080_1");
        tabImages.add("passpoints1920x1080_2");
        tabImages.add("passpoints1920x1080_3");
        tabImages.add("passpoints1920x1080_4");
        tabImages.add("passpoints1920x1080_5");
        tabImages.add("passpoints1920x1080_6");
        tabImages.add("passpoints1920x1080_7");

        size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int screenWidth = size.x;
        int screenHeight = size.y - getStatusBarHeight();


        int l = 0;
        int c = 0;
        for (int i = 0; i < tabImages.size(); i++) {

            final int j = i;

            //afficher l'image
            iv = new ImageView(this);

            bmp = decodeSampledBitmapFromResource(getResources(), getResources().getIdentifier(tabImages.get(i), "drawable", getPackageName()), 640 / 2, 360 / 2);

            bmp2 = Bitmap.createScaledBitmap(bmp, 640, 360, true);
            if (bmp != bmp2) {
                bmp.recycle();
            }

            iv.setImageBitmap(bmp2);

            param = new GridLayout.LayoutParams();
            param.height = 360;
            param.width = screenWidth / myLayout.getColumnCount();
            param.setMargins(0, 20, 0, 20);
            param.setGravity(Gravity.CENTER);
            param.columnSpec = GridLayout.spec(c);
            param.rowSpec = GridLayout.spec(l);
            iv.setLayoutParams(param);

            iv.setOnClickListener(new ImageView.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(ChoixImage.this, "" + tabImages.get(j), Toast.LENGTH_SHORT).show();
                    //((ImageView)v).setImageBitmap(bmp2);
                    Intent appel = new Intent(ChoixImage.this, ChoixPoints.class);

                    Bundle b2 = new Bundle();
                    b2.putString("nomImage", tabImages.get(j));
                    appel.putExtras(b2);

                    startActivity(appel);
                }
            });

            myLayout.addView(iv);

            l++;

        }
        Toast.makeText(ChoixImage.this, "Veuillez choisir votre image", Toast.LENGTH_SHORT).show();
    }


    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
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

