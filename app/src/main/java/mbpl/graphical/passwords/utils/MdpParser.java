package mbpl.graphical.passwords.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by benja135 on 26/04/16.
 */
public class MdpParser {

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

}
