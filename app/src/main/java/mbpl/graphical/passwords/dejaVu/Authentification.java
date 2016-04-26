package mbpl.graphical.passwords.dejaVu;

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

import mbpl.graphical.passwords.sqlite.DejaVu;
import mbpl.graphical.passwords.sqlite.DejaVuManager;

import static mbpl.graphical.passwords.utils.MdpParser.stringArrayToIntArray;

/**
 * Created by benja135 on 05/03/16.
 * Activité d'authentification de la méthode "Déjà Vu".
 */
public class Authentification extends AppCompatActivity {

    private final int nbIcone = 258;
    private int tailleIcone = 256;
    private int nbLigne = 6;
    private int nbColonne = 4;

    private List<Integer> trueMotDePasse;
    private List<Integer> inputMotDePasse = new ArrayList<>();
    private long time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DejaVuManager dejaVuBDD = new DejaVuManager(getApplicationContext());
        dejaVuBDD.open();
        DejaVu dejaVu = dejaVuBDD.getDejaVu();
        trueMotDePasse = stringArrayToIntArray(dejaVu.getMdp());
        int nbIconeParPhase = dejaVu.getNbIcone();
        if (nbIconeParPhase == 6) {
            nbLigne = 3;
            nbColonne = 2;
        } else if (nbIconeParPhase == 96) {
            nbLigne = 12;
            nbColonne = 8;
            tailleIcone = 96;
        }
        dejaVuBDD.close();
    }

    @Override
    protected void onStart() {
        super.onStart();
        drawAndSetListeners(trueMotDePasse.get(0));
        Toast.makeText(Authentification.this, "Phase d'authentification", Toast.LENGTH_LONG).show();
        time = System.currentTimeMillis();
    }

    /**
     * Affiche des icônes aléatoirement et ajoute un listener sur chacun d'entre eux.
     * iconToBeDisplayed sera forcément affiché (à une position aléatoire).
     *
     * @param iconToBeDisplayed icône à afficher (pour nous le caractére du mdp courant)
     */
    private void drawAndSetListeners(int iconToBeDisplayed) {

        boolean[] iconAlreadyDisplayed = new boolean[nbIcone];
        for (int i = 0; i < nbIcone; i++) {
            iconAlreadyDisplayed[i] = false;
        }

        int positionIconToBeDisplayed = randomInto(0, nbLigne * nbColonne - 1);

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

                // Crée un bitmap d'une icone piochée aléatoirement (ou pas)
                int numIcon;
                if ((l * nbColonne + c) == positionIconToBeDisplayed) {
                    numIcon = trueMotDePasse.get(inputMotDePasse.size());
                } else {
                    do {
                        numIcon = randomInto(1, nbIcone);
                    } while (iconAlreadyDisplayed[numIcon - 1] || numIcon == iconToBeDisplayed);
                }
                iconAlreadyDisplayed[numIcon - 1] = true;

                Bitmap bmp;
                bmp = BitmapFactory.decodeResource(getResources(), getDrawableN(numIcon));
                bmp = Bitmap.createScaledBitmap(bmp, tailleIcone, tailleIcone, true);

                // On ajoute l'icone à l'ImageView
                imageView.setImageBitmap(bmp);

                // Ajoute un listener sur l'icon
                final int finalNumIcon = numIcon;
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        changePhase(finalNumIcon);
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
     * A chaque appui sur un icone, cette méthode est appelé.
     * Elle permet de changer de phase, c'est à dire de rappeler la méthode d'affichage
     * avec le prochain icone du mot de passe ou de détecter la fin de la saisie et donc
     * de retourner au menu de l'application.
     *
     * @param selectedIcon numéro de l'icône selectionné lors de la phase
     */
    private void changePhase(int selectedIcon) {
        inputMotDePasse.add(selectedIcon);

        if (inputMotDePasse.size() == trueMotDePasse.size()) {
            DejaVuManager dejaVuBDD = new DejaVuManager(getApplicationContext());
            dejaVuBDD.open();
            DejaVu dejaVu = dejaVuBDD.getDejaVu();
            if (inputMotDePasse.equals(trueMotDePasse)) {
                time = System.currentTimeMillis() - time; // temps de l'authentification
                inputMotDePasse.clear();
                Toast.makeText(Authentification.this, "Authentification OK !", Toast.LENGTH_LONG).show();
                Intent accueil = new Intent(Authentification.this, mbpl.graphical.passwords.Accueil.class);
                dejaVuBDD.addTentativeReussie(dejaVu, (float) time/1000);
                dejaVuBDD.close();
                startActivity(accueil);
            } else {
                dejaVuBDD.addTentativeEchouee(dejaVu);
                dejaVuBDD.close();
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
     * Retourne l'icon n de res/drawable.
     *
     * @param n numéro de l'icône à récupérer
     * @return id identifiant de l'icône
     */
    private int getDrawableN(int n) {
        return getResources().getIdentifier("icon"
                + tailleIcone + "x" + tailleIcone + "_" + n, "drawable", getPackageName());
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
