package mbpl.graphical.passwords.passfaces;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mbpl.graphical.passwords.sqlite.Passfaces;
import mbpl.graphical.passwords.sqlite.PassfacesManager;

import static mbpl.graphical.passwords.utils.MdpParser.stringArrayToIntArray;

/**
 * Created by benja135 on 26/04/16.
 * Activité d'authentification de la méthode "passfaces".
 */
public class Authentification extends AppCompatActivity {

    private final int nbImage = 20;
    private final int tailleImage = 256;
    private final int nbLigne = 3;
    private final int nbColonne = 3;

    private List<Integer> trueMotDePasse;
    private List<Integer> inputMotDePasse = new ArrayList<>();
    private long time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PassfacesManager passfacesBDD = new PassfacesManager(getApplicationContext());
        passfacesBDD.open();
        Passfaces passfaces = passfacesBDD.getPassfaces();
        trueMotDePasse = stringArrayToIntArray(passfaces.getMdp());
        passfacesBDD.close();
    }

    @Override
    protected void onStart() {
        super.onStart();
        drawAndSetListeners(trueMotDePasse.get(0));
        Toast.makeText(Authentification.this, "Phase d'authentification", Toast.LENGTH_LONG).show();
        time = System.currentTimeMillis();
    }

    /**
     * Affiche des images aléatoirement et ajoute un listener sur chacune d'entre eux.
     * imageToBeDisplayed sera forcément affiché (à une position aléatoire).
     *
     * @param imageToBeDisplayed image à afficher (pour nous le caractére du mdp courant)
     */
    private void drawAndSetListeners(int imageToBeDisplayed) {

        boolean[] imageAlreadyDisplayed = new boolean[nbImage];
        for (int i = 0; i < nbImage; i++) {
            imageAlreadyDisplayed[i] = false;
        }

        int positionImageToBeDisplayed = randomInto(0, nbLigne * nbColonne - 1);

        GridLayout gridLayout = new GridLayout(this);

        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int screenWidth = size.x;
        int screenHeight = size.y - getStatusBarHeight();

        gridLayout.setColumnCount(nbColonne);
        gridLayout.setRowCount(nbLigne);

        for (int l = 0; l < nbLigne; l++) {
            for (int c = 0; c < nbColonne; c++) {

                ImageView imageView;
                imageView = new ImageView(this);

                // Crée un bitmap d'une image piochée aléatoirement (ou pas)
                int numImage;
                if ((l * nbColonne + c) == positionImageToBeDisplayed) {
                    numImage = trueMotDePasse.get(inputMotDePasse.size());
                } else {
                    do {
                        numImage = randomInto(1, nbImage);
                    } while (imageAlreadyDisplayed[numImage - 1] || numImage == imageToBeDisplayed);
                }
                imageAlreadyDisplayed[numImage - 1] = true;

                Bitmap bmp;
                bmp = BitmapFactory.decodeResource(getResources(), getDrawableN(numImage));
                bmp = Bitmap.createScaledBitmap(bmp, tailleImage, tailleImage, true);

                // On ajoute l'image à l'ImageView
                imageView.setImageBitmap(bmp);

                // Ajoute un listener sur l'image
                final int finalNumImage = numImage;
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        changePhase(finalNumImage);
                    }
                });

                // On ajoute l'ImageView au GridLayout en mettant les bon paramétres
                GridLayout.LayoutParams param = new GridLayout.LayoutParams();
                param.height = screenHeight / nbLigne;
                param.width = screenWidth / nbColonne;
                param.setMargins(0, 0, 0, 0);
                param.setGravity(Gravity.CENTER);
                param.columnSpec = GridLayout.spec(c);
                param.rowSpec = GridLayout.spec(l);
                imageView.setLayoutParams(param);
                gridLayout.addView(imageView);
            }
        }

        setContentView(gridLayout);
    }


    /**
     * A chaque appui sur une image, cette méthode est appelé.
     * Elle permet de changer de phase, c'est à dire de rappeler la méthode d'affichage
     * avec la prochaine iamge du mot de passe ou de détecter la fin de la saisie et donc
     * de retourner au menu de l'application.
     *
     * @param selectedImage numéro de l'image selectionné lors de la phase
     */
    private void changePhase(int selectedImage) {
        inputMotDePasse.add(selectedImage);

        if (inputMotDePasse.size() == trueMotDePasse.size()) {
            PassfacesManager passfacesManager = new PassfacesManager(getApplicationContext());
            passfacesManager.open();
            Passfaces passfaces = passfacesManager.getPassfaces();
            if (inputMotDePasse.equals(trueMotDePasse)) {
                time = System.currentTimeMillis() - time; // temps de l'authentification
                inputMotDePasse.clear();
                Toast.makeText(Authentification.this, "Authentification OK !", Toast.LENGTH_LONG).show();
                Intent accueil = new Intent(Authentification.this, mbpl.graphical.passwords.Accueil.class);
                passfacesManager.addTentativeReussie(passfaces, (float) time/1000);
                passfacesManager.close();
                startActivity(accueil);
            } else {
                passfacesManager.addTentativeEchouee(passfaces);
                passfacesManager.close();
                Toast.makeText(Authentification.this, "Authentification échoué", Toast.LENGTH_LONG).show();
                inputMotDePasse.clear();
                drawAndSetListeners(trueMotDePasse.get(inputMotDePasse.size()));
            }
        } else {
            drawAndSetListeners(trueMotDePasse.get(inputMotDePasse.size()));
        }
    }

    /**
     * Retourne un nombre aléatoire entre min et max.
     *
     * @param min minimum de l'intervalle
     * @param max maximum de l'intervalle
     * @return entier aléatoire
     */
    private int randomInto(int min, int max) {
        return (int) Math.round(Math.random() * (max - min)) + min;
    }

    /**
     * Retourne l'image de res/drawable.
     *
     * @param n numéro de l'image à récupérer
     * @return id identifiant de l'icône
     */
    private int getDrawableN(int n) {
        return getResources().getIdentifier("visage_" + n, "drawable", getPackageName());
    }


    /**
     * Retourne la hauteur de la barre de notification
     *
     * @return taille de la barre de notification
     */
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

}
