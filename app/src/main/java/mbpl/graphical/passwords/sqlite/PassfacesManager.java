package mbpl.graphical.passwords.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Matteo on 26/04/2016.
 */

/**
 * Permet d'intéragir avec la base de donnée  (manipuler les données).
 */
public class PassfacesManager {

    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "methode.db";

    private static final String TABLE_NAME = "table_methode";
    private static final String COL_ID = "methode_id";
    private static final int NUM_COL_ID = 0;
    private static final String COL_NOM = "methode_name";
    private static final int NUM_COL_NOM = 1;
    private static final String COL_CATEGORIE = "categorie";
    private static final int NUM_COL_CATEGORIE = 2;
    private static final String COL_BRUTEFORCE = "bruteForce";
    private static final int NUM_COL_BRUTEFORCE = 3;
    private static final String COL_DICTIONARYATTACK = "dictionaryAttack";
    private static final int NUM_COL_DICTIONARYATTACK = 4;
    private static final String COL_SHOULDERSURFING = "shoulderSurfing";
    private static final int NUM_COL_SHOULDERSURFING = 5;
    private static final String COL_SMUDGEATTACK = "smudgeAttack";
    private static final int NUM_COL_SMUDGEATTACK = 6;
    private static final String COL_EYETRACKING = "eyeTracking";
    private static final int NUM_COL_EYETRACKING = 7;
    private static final String COL_SPYWARE = "spyWare";
    private static final int NUM_COL_SPYWARE = 8;
    private static final String COL_INDICESECURITE = "indiceSecurite";
    private static final int NUM_COL_INDICESECURITE = 9;
    private static final String COL_APPRENTISSAGE = "apprentissage";
    private static final int NUM_COL_APPRENTISSAGE = 10;
    private static final String COL_MEMORISATION = "memorisation";
    private static final int NUM_COL_MEMORISATION = 11;
    private static final String COL_TEMPS = "temps";
    private static final int NUM_COL_TEMPS = 12;
    private static final String COL_SATISFACTION = "satisfaction";
    private static final int NUM_COL_SATISFACION = 13;
    private static final String COL_INDICEUTILISABILITE = "indiceUtilisabilite";
    private static final int NUM_COL_INDICEUTILISABILITE = 14;
    private static final String COL_TENTATIVEREUSSIE = "nb_tentative_reussie";
    private static final int NUM_COL_TENTATIVEREUSSIE = 15;
    private static final String COL_TENTATIVEECHOUEE = "nb_tentative_echouee";
    private static final int NUM_COL_TENTATIVEECHOUEE = 16;
    private static final String COL_TEMPSMOYEN = "temps_auth_moyen";
    private static final int NUM_COL_TEMPSMOYEN = 17;
    private static final String COL_ESPACE_MDP = "espaceMdp";
    private static final int NUM_COL_ESPACE_MDP = 18;
    private static final String COL_MDP = "mdp";
    private static final int NUM_COL_MDP = 19;
    private static final String COL_ICONE = "icone";
    private static final int NUM_COL_ICONE = 20;
    private static final String COL_DOUBLON = "doublon";
    private static final int NUM_COL_DOUBLON = 21;

    private SQLiteDatabase db;
    private MySQLiteDatabase maBaseSQLite;

    public PassfacesManager(Context context) {
        maBaseSQLite = new MySQLiteDatabase(context, NOM_BDD, null, VERSION_BDD);
    }

    /**
     * On ouvre la table en lecture/écriture
     */
    public void open() {
        db = maBaseSQLite.getWritableDatabase();
    }

    /**
     * On ferme l'accès à la BDD.
     */
    public void close() {
        db.close();
    }

    /**
     * Ajout d'une méthode Passfaces dans la base.
     *
     * @param pf
     * @return retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
     */
    public long addPassfaces(Passfaces pf) {

        ContentValues values = new ContentValues();
        values.put(COL_ID, pf.getId());
        values.put(COL_NOM, pf.getNom());
        values.put(COL_CATEGORIE, pf.getCategorie());
        values.put(COL_BRUTEFORCE, pf.getBruteForce());
        values.put(COL_DICTIONARYATTACK, pf.getDictionaryAttack());
        values.put(COL_SHOULDERSURFING, pf.getShoulderSurfing());
        values.put(COL_SMUDGEATTACK, pf.getSmudgeAttack());
        values.put(COL_EYETRACKING, pf.getEyeTracking());
        values.put(COL_SPYWARE, pf.getSpyWare());
        values.put(COL_INDICESECURITE, pf.getIndiceSecurite());
        values.put(COL_APPRENTISSAGE, pf.getApprentissage());
        values.put(COL_MEMORISATION, pf.getMemorisation());
        values.put(COL_TEMPS, pf.getTemps());
        values.put(COL_SATISFACTION, pf.getSatisfaction());
        values.put(COL_INDICEUTILISABILITE, pf.getIndiceUtilisabilite());
        values.put(COL_TENTATIVEREUSSIE, pf.getNb_tentative_reussie());
        values.put(COL_TENTATIVEECHOUEE, pf.getNb_tentative_echouee());
        values.put(COL_TEMPSMOYEN, pf.getTemps_auth_moyen());
        values.put(COL_ESPACE_MDP, pf.getEspaceMdp());
        values.put(COL_MDP, pf.getMdp());
        values.put(COL_ICONE, pf.getNbIcone());
        values.put(COL_DOUBLON, pf.getDoublon());

        return db.insertWithOnConflict(TABLE_NAME, null,
                values, SQLiteDatabase.CONFLICT_FAIL);

    }

    /**
     * Supprime la méthode de la base de donnée
     *
     * @param pf
     * @return le nombre de lignes supprimées
     */
    public int removePassfaces(Passfaces pf) {
        long id = pf.getId();
        return db.delete(TABLE_NAME, COL_ID + " = " + id, null);
    }


    /**
     * Retourne la méthode Passfaces depuis la bdd.
     *
     * @return la méthode
     */
    public Passfaces getPassfaces() {

        Passfaces pf = new Passfaces();
        int id = pf.getId();


        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_ID + "=" + id, null);

        if (c.moveToFirst()) {
            pf.setNb_tentative_echouee(c.getInt(NUM_COL_TENTATIVEECHOUEE));
            pf.setNb_tentative_reussie(c.getInt(NUM_COL_TENTATIVEREUSSIE));
            pf.setTemps_auth_moyen(c.getFloat(NUM_COL_TEMPSMOYEN));
            pf.setMdp(c.getString(NUM_COL_MDP));
            pf.setNbIcone(c.getInt(NUM_COL_ICONE));
            pf.setDoublon(c.getInt(NUM_COL_DOUBLON));
            c.close();
        }

        return pf;
    }


    /**
     * Met à jour la Methode Passfaces passé en paramètre pour les tentatives et l'authentification moyen
     * dans la bdd.
     *
     * @param pf
     * @param tentative_echouee
     * @param tentative_reussi
     * @param auth_moyen
     * @return le nombre de lignes updated
     */
    public int setStatsPassfaces(Passfaces pf, int tentative_echouee, int tentative_reussi, float auth_moyen) {
        int id = pf.getId();
        ContentValues values = new ContentValues();
        values.put(COL_TENTATIVEECHOUEE, tentative_echouee);
        values.put(COL_TENTATIVEREUSSIE, tentative_reussi);
        values.put(COL_TEMPSMOYEN, auth_moyen);
        return db.update(TABLE_NAME, values, COL_ID + " = " + id, null);
    }


    /**
     * Met à jour le nombre de tentatives réussies en additionnant avec ce qu'il y avait avant.
     * Met à jour le temps moyen.
     *
     * @param pf
     * @param auth_moyen
     * @return le nombre de lignes updated
     */
    public int addTentativeReussie(Passfaces pf, float auth_moyen){
        int id = pf.getId();
        int newTentativeReussi = pf.getNb_tentative_reussie() + 1;
        float newAuthenMoyen = (pf.getTemps_auth_moyen() * (float) pf.getNb_tentative_reussie()
                + auth_moyen) / (float) newTentativeReussi;

        ContentValues values = new ContentValues();
        values.put(COL_TENTATIVEREUSSIE, newTentativeReussi);
        values.put(COL_TEMPSMOYEN, newAuthenMoyen);
        return db.update(TABLE_NAME, values, COL_ID + " = " + id, null);
    }

    /**
     * Met à jour le nombre de tentatives échouée en additionnant avec ce qu'il y avait avant.
     *
     * @param pf
     * @return le nombre de lignes updated
     */
    public int addTentativeEchouee(Passfaces pf) {
        int id = pf.getId();
        int newTentativeEchouee = pf.getNb_tentative_echouee() + 1;

        ContentValues values = new ContentValues();
        values.put(COL_TENTATIVEECHOUEE, newTentativeEchouee);
        return db.update(TABLE_NAME, values, COL_ID + " = " + id, null);
    }

    /**
     * Met à jour la Methode Passfaces passé en paramètre pour les configurations dans la bdd.
     *
     * @param pf
     * @param nbIcone
     * @param doublon
     * @return
     */
    public int updateConfiguration(Passfaces pf, int nbIcone, boolean doublon) {
        int id = pf.getId();
        ContentValues values = new ContentValues();
        values.put(COL_ICONE, nbIcone);
        values.put(COL_DOUBLON, doublon);
        return db.update(TABLE_NAME, values, COL_ID + " = " + id, null);
    }

    /**
     * Met a jour le mot de passe dans la bdd
     *
     * @param pf
     * @param str
     * @return
     */
    public int setPassword(Passfaces pf, String str) {
        int id = pf.getId();
        ContentValues values = new ContentValues();
        values.put(COL_MDP, str);
        return db.update(TABLE_NAME, values, COL_ID + " = " + id, null);
    }

    /**
     * Retourne vrai si une méthode Passfaces est dans la base de donnée, faux le contraire
     *
     * @return boolean.
     */
    public boolean exist() {
        Passfaces pf = new Passfaces();
        int id = pf.getId();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_ID + "=" + id, null);

        if (c.getCount() < 1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Retourne vrai si le mot de passe n'a pas été changé.
     *
     * @param pf
     * @return boolean
     */
    public boolean defaultPassword(Passfaces pf) {
        if (pf.getMdp().compareTo("") != 0) {
            return false;
        } else return true;
    }

    /**
     * Retourne true si la méthode contient des doublons.
     *
     * @param pf
     * @return
     */
    public boolean doublon(Passfaces pf) {
        return pf.getDoublon();
    }


    public Cursor getMethode() {
        // sélection de tous les enregistrements de la table
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }


}

