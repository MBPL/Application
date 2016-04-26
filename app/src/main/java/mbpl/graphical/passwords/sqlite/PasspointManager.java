package mbpl.graphical.passwords.sqlite;

/**
 * Created by Matteo on 26/04/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Permet d'intéragir avec la base de donnée  (manipuler les données).
 */
public class PasspointManager {

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

    public PasspointManager(Context context) {
        maBaseSQLite = new MySQLiteDatabase(context, NOM_BDD, null, VERSION_BDD);
    }

    /**
     * On ouvre la table en lecture/écriture
     */
    public void open() {
        //o
        db = maBaseSQLite.getWritableDatabase();
    }

    /**
     * On ferme l'accès à la BDD.
     */
    public void close() {
        db.close();
    }

    /**
     * Ajout d'une méthode Passpoint dans la base.
     *
     * @param pt
     * @return retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
     */
    public long addPasspoint(Passpoint pt) {

        ContentValues values = new ContentValues();
        values.put(COL_ID, pt.getId());
        values.put(COL_NOM, pt.getNom());
        values.put(COL_CATEGORIE, pt.getCategorie());
        values.put(COL_BRUTEFORCE, pt.getBruteForce());
        values.put(COL_DICTIONARYATTACK, pt.getDictionaryAttack());
        values.put(COL_SHOULDERSURFING, pt.getShoulderSurfing());
        values.put(COL_SMUDGEATTACK, pt.getSmudgeAttack());
        values.put(COL_EYETRACKING, pt.getEyeTracking());
        values.put(COL_SPYWARE, pt.getSpyWare());
        values.put(COL_INDICESECURITE, pt.getIndiceSecurite());
        values.put(COL_APPRENTISSAGE, pt.getApprentissage());
        values.put(COL_MEMORISATION, pt.getMemorisation());
        values.put(COL_TEMPS, pt.getTemps());
        values.put(COL_SATISFACTION, pt.getSatisfaction());
        values.put(COL_INDICEUTILISABILITE, pt.getIndiceUtilisabilite());
        values.put(COL_TENTATIVEREUSSIE, pt.getNb_tentative_reussie());
        values.put(COL_TENTATIVEECHOUEE, pt.getNb_tentative_echouee());
        values.put(COL_TEMPSMOYEN, pt.getTemps_auth_moyen());
        values.put(COL_ESPACE_MDP, pt.getEspaceMdp());
        values.put(COL_MDP, pt.getMdp());


        return db.insertWithOnConflict(TABLE_NAME, null,
                values, SQLiteDatabase.CONFLICT_FAIL);

    }

    /**
     * Supprime la méthode de la base de donnée
     * @param pt
     * @return le nombre de lignes supprimées
     */
    public int removePasspoint(Passpoint pt){
        long id = pt.getId();
        return db.delete(TABLE_NAME, COL_ID + " = " + id, null);
    }



    /**
     * Retourne la méthode Passpoint depuis la bdd.
     * @return la méthode
     */
    public Passpoint getPasspoint() {

        Passpoint pt = new Passpoint();
        int id = pt.getId();


        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_ID + "=" + id, null);

        if (c.moveToFirst()) {
            pt.setNb_tentative_echouee(c.getInt(NUM_COL_TENTATIVEECHOUEE));
            pt.setNb_tentative_reussie(c.getInt(NUM_COL_TENTATIVEREUSSIE));
            pt.setTemps_auth_moyen(c.getFloat(NUM_COL_TEMPSMOYEN));
            pt.setMdp(c.getString(NUM_COL_MDP));
            c.close();
        }

        return pt;
    }


    /**
     * Met à jour la Methode Passpoint passé en paramètre pour les tentatives et l'authentification moyen
     * dans la bdd.
     * @param pt
     * @param tentative_echouee
     * @param tentative_reussi
     * @param auth_moyen
     * @return le nombre de lignes updated
     */
    public int updatePasspoint(Passpoint pt, int tentative_echouee, int tentative_reussi, float auth_moyen) {
        int id = pt.getId();
        ContentValues values = new ContentValues();
        values.put(COL_TENTATIVEECHOUEE, tentative_echouee);
        values.put(COL_TENTATIVEREUSSIE, tentative_reussi);
        values.put(COL_TEMPSMOYEN, auth_moyen);
        return db.update(TABLE_NAME, values, COL_ID + " = " + id, null);

    }

    /**
     * Met à jour la Methode Passpoint passé en paramètre pour les configurations dans la bdd.
     * @param pt
     * @param nbIcone
     * @param doublon
     * @return
     */
    public int updateConfiguration(Passpoint pt, int nbIcone, int doublon){
        int id = pt.getId();
        ContentValues values = new ContentValues();
        values.put(COL_ICONE, nbIcone);
        values.put(COL_DOUBLON, doublon);
        return db.update(TABLE_NAME, values, COL_ID + " = " + id, null);
    }

    /**
     * Met a jour le mot de passe dans la bdd
     * @param pt
     * @param str
     * @return
     */
    public int setPassword(Passpoint pt, String str){
        int id = pt.getId();
        ContentValues values = new ContentValues();
        values.put(COL_MDP, str);
        return db.update(TABLE_NAME, values, COL_ID + " = " + id, null);
    }

    /**
     * Retourne vrai si une méthode Passpoint est dans la base de donnée, faux le contraire
     * @return boolean.
     */
    public boolean exist(){
        Passpoint pt = new Passpoint();
        int id = pt.getId();
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
     * @param pt
     * @return boolean
     */
    public boolean defaultPassword(Passpoint pt){
        if(pt.getMdp().compareTo("") != 0){
            return false;
        }
        else return true;
    }





    public Cursor getMethode() {
        // sélection de tous les enregistrements de la table
        return db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
    }



}
