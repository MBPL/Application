package mbpl.graphical.passwords.utils;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by benja135 on 26/04/16.
 * Classe utilitaires.
 */
public abstract class Tools {

    /**
     * Convertie une chaine de caractères en liste d'entier.
     * La chaine doit être de la forme ArrayList.toString().
     * http://stackoverflow.com/questions/7646392/convert-string-to-int-array-in-java
     *
     * @param pass chaine de forme ArrayList.toString().
     * @return liste d'entier
     */
    public static List<Integer> stringArrayToIntArray(String pass) {

        String[] items = pass.replaceAll("\\[", "").replaceAll(" ", "").replaceAll("\\]", "").split(",");

        List results = new ArrayList<>();

        for (int i = 0; i < items.length; i++) {
            try {
                results.add(Integer.parseInt(items[i]));
            } catch (NumberFormatException nfe) {
            }
        }
        return results;
    }


    public static String[] stringToStringTable(String pass) {
        return pass.replaceAll("\\[", "").replaceAll(" ", "").replaceAll("\\]", "").split(",");
    }

    /**
     * Retourne un nombre aléatoire entre min et max.
     *
     * @param min minimum de l'intervalle
     * @param max maximum de l'intervalle
     * @return entier aléatoire
     */
    public static int randomInto(int min, int max) {
        return (int) Math.round(Math.random() * (max - min)) + min;
    }


    /**
     * Retourne la hauteur de la barre de notification
     * @param resources getResources()
     * @return taille de la barre de notification
     */
    public static int getStatusBarHeight(Resources resources) {
        int result = 0;
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId);
        }
        return result;
    }

}
