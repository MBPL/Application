package mbpl.graphical.passwords.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


/**
 * Created by Matteo on 08/04/2016.
 *
 */

/**
 * Permet d'intéragir avec la base de donnée  (manipuler les données).
 */
public class DejaVuManager {

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

    public DejaVuManager(Context context) {
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
     * Ajout d'une méthode DejaVu dans la base.
     *
     * @param djv
     * @return retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
     */
    public long addDejaVu(DejaVu djv) {

        ContentValues values = new ContentValues();
        values.put(COL_ID, djv.getId());
        values.put(COL_NOM, djv.getNom());
        values.put(COL_CATEGORIE, djv.getCategorie());
        values.put(COL_BRUTEFORCE, djv.getBruteForce());
        values.put(COL_DICTIONARYATTACK, djv.getDictionaryAttack());
        values.put(COL_SHOULDERSURFING, djv.getShoulderSurfing());
        values.put(COL_SMUDGEATTACK, djv.getSmudgeAttack());
        values.put(COL_EYETRACKING, djv.getEyeTracking());
        values.put(COL_SPYWARE, djv.getSpyWare());
        values.put(COL_INDICESECURITE, djv.getIndiceSecurite());
        values.put(COL_APPRENTISSAGE, djv.getApprentissage());
        values.put(COL_MEMORISATION, djv.getMemorisation());
        values.put(COL_TEMPS, djv.getTemps());
        values.put(COL_SATISFACTION, djv.getSatisfaction());
        values.put(COL_INDICEUTILISABILITE, djv.getIndiceUtilisabilite());
        values.put(COL_TENTATIVEREUSSIE, djv.getNb_tentative_reussie());
        values.put(COL_TENTATIVEECHOUEE, djv.getNb_tentative_echouee());
        values.put(COL_TEMPSMOYEN, djv.getTemps_auth_moyen());
        values.put(COL_ESPACE_MDP, djv.getEspaceMdp());
        values.put(COL_MDP, djv.getMdp());
        values.put(COL_ICONE, djv.getNbIcone());
        values.put(COL_DOUBLON, djv.getDoublon());

        return db.insertWithOnConflict(TABLE_NAME, null,
                values, SQLiteDatabase.CONFLICT_FAIL);

    }

    /**
     * Supprime la méthode de la base de donnée
     * @param djv
     * @return le nombre de lignes supprimées
     */
    public int removeDejaVu(DejaVu djv){
        long id = djv.getId();
        return db.delete(TABLE_NAME, COL_ID + " = " + id, null);
    }

    /**
     * Utiliser de préférence la méthode prenant la méthode DejaVu
     *
     * @param id
     * @return le nombre de lignes supprimées
     */
    public int removeDejaVu(int id){

        return db.delete(TABLE_NAME, COL_ID + " = " + id, null);
    }

    /**
     * Retourne la méthode DejaVu depuis la bdd.
     * @return la méthode
     */
    public DejaVu getDejaVu() {

        DejaVu djv = new DejaVu();
        int id = djv.getId();

        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_ID + "=" + id, null);

        if (c.moveToFirst()) {
            djv.setNb_tentative_echouee(c.getInt(NUM_COL_TENTATIVEECHOUEE));
            djv.setNb_tentative_reussie(c.getInt(NUM_COL_TENTATIVEREUSSIE));
            djv.setTemps_auth_moyen(c.getFloat(NUM_COL_TEMPSMOYEN));
            djv.setMdp(c.getString(NUM_COL_MDP));
            djv.setNbIcone(c.getInt(NUM_COL_ICONE));
            djv.setDoublon(c.getInt(NUM_COL_DOUBLON));
            c.close();
        }

        return djv;
    }


    /**
     * Met à jour la Methode DejaVu passé en paramètre pour les tentatives et l'authentification moyen
     * dans la bdd.
     * @param djv
     * @param tentative_echouee
     * @param tentative_reussi
     * @param auth_moyen
     * @return le nombre de lignes updated
     */
    public int updateDejaVu(DejaVu djv, int tentative_echouee, int tentative_reussi, float auth_moyen) {
        int id = djv.getId();
        ContentValues values = new ContentValues();
        values.put(COL_TENTATIVEECHOUEE, tentative_echouee);
        values.put(COL_TENTATIVEREUSSIE, tentative_reussi);
        values.put(COL_TEMPSMOYEN, auth_moyen);
        return db.update(TABLE_NAME, values, COL_ID + " = " + id, null);

    }

    /**
     * Met à jour la Methode DejaVu passé en paramètre pour les configurations dans la bdd.
     * @param djv
     * @param nbIcone
     * @param doublon
     * @return
     */
    public int updateConfiguration(DejaVu djv, int nbIcone, boolean doublon){
        int id = djv.getId();
        ContentValues values = new ContentValues();
        values.put(COL_ICONE, nbIcone);
        values.put(COL_DOUBLON, doublon);
        return db.update(TABLE_NAME, values, COL_ID + " = " + id, null);
    }

    /**
     * Met a jour le mot de passe dans la bdd
     * @param djv
     * @param str
     * @return
     */
    public int setPassword(DejaVu djv, String str){
        int id = djv.getId();
        ContentValues values = new ContentValues();
        values.put(COL_MDP, str);
        return db.update(TABLE_NAME, values, COL_ID + " = " + id, null);
    }

    /**
     * Retourne vrai si une méthode DejaVu est dans la base de donnée, faux le contraire
     * @return boolean.
     */
    public boolean exist(){
        DejaVu djv = new DejaVu();
        int id = djv.getId();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_ID + "=" + id, null);

        if(c.getCount() < 1){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * Retourne vrai si le mot de passe n'a pas été changé.
     * @param djv
     * @return boolean
     */
    public boolean defaultPassword(DejaVu djv){
        if(djv.getMdp().compareTo("") != 0){
            return false;
        }
        else return true;
    }

    /**
     * Retourne true si la méthode contient des doublons.
     * @param djv
     * @return
     */
    public boolean doublon(DejaVu djv){
        return djv.getDoublon();
    }


    public Cursor getMethode() {
        // sélection de tous les enregistrements de la table
        return db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
    }



}
